package sanchez.sanchez.sergio.androidpokeapi.ui.features.list

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.pokemon_fragment_content_layout.view.*
import kotlinx.android.synthetic.main.pokemons_fragment.*
import sanchez.sanchez.sergio.androidpokeapi.R
import sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon.PokemonListFragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.factory.DaggerComponentFactory
import sanchez.sanchez.sergio.androidpokeapi.domain.models.Pokemon
import sanchez.sanchez.sergio.androidpokeapi.ui.core.SupportFragment
import sanchez.sanchez.sergio.androidpokeapi.ui.core.ext.navigate
import timber.log.Timber

/**
 * Pokemon List Fragment
 */

class PokemonListFragment: SupportFragment<PokemonListViewModel>(PokemonListViewModel::class.java),
    PokemonListAdapter.OnPokemonClickListener, IPaginationCallBack {

    private val fragmentComponent: PokemonListFragmentComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerComponentFactory.getPokemonListFragmentComponent(requireActivity() as AppCompatActivity)
    }

    lateinit var recyclerViewAdapter: PokemonListAdapter

    override fun layoutId(): Int = R.layout.pokemons_fragment

    override fun onInject() {
        fragmentComponent.inject(this)
    }

    /**
     * On Observer Live Data
     */
    override fun onObserverLiveData(viewModel: PokemonListViewModel) {
        super.onObserverLiveData(viewModel)
        // Observe operation result
        viewModel.pokemonListState.observe(this, {
            when(it) {
                is PokemonListState.OnSuccess -> onPokemonListLoaded(it.pokemonList)
                is PokemonListState.OnError -> onErrorOccurred(it.ex)
                is PokemonListState.OnNotFound -> {

                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Recycler View Adapter
        recyclerViewAdapter = PokemonListAdapter(requireContext(), mutableListOf(), this, this)
        // Configure Recycler View
        contentView.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(object: RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {

                    outRect.apply {
                        left = resources.getDimension(R.dimen.item_vertical_separation).toInt()
                        right = resources.getDimension(R.dimen.item_vertical_separation).toInt()
                        bottom = resources.getDimension(R.dimen.item_horizontal_separation).toInt()
                    }
                }
            })
            setHasFixedSize(true)
            isNestedScrollingEnabled = true
            adapter = recyclerViewAdapter
        }

        swipeRefreshLayout.setOnRefreshListener { loadPokemonList() }

        viewModel.pokemonListState.value?.let {
            if(it is PokemonListState.OnSuccess)
                onPokemonListLoaded(it.pokemonList)
            else
                loadPokemonList()
        } ?: loadPokemonList()
    }

    /**
     * On Pokemon Clicked
     * @param pokemon
     */
    override fun onPokemonClicked(pokemon: Pokemon) {
        navigate(PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(pokemon.name))
    }

    /**
     * Private Methods
     */

    /**
     * On Pokemon List Loaded
     * @param pokemonList
     */
    private fun onPokemonListLoaded(pokemonList: List<Pokemon>) {
        Timber.d("onPokemonListLoaded CALLED, pokemon -> %d", pokemonList.size)
        loadingView.visibility = View.GONE
        errorView.visibility = View.GONE
        contentView.visibility = View.VISIBLE
        swipeRefreshLayout.isRefreshing = false

        val currentSize = recyclerViewAdapter.itemCount
        recyclerViewAdapter.addData(pokemonList)
        contentView.recyclerView.scrollToPosition(currentSize)
    }

    /**
     * On Error Occurred
     * @param ex
     */
    private fun onErrorOccurred(ex: Exception) {
        Timber.d("onErrorOccurred CALLED, message -> %s", ex.message)
        swipeRefreshLayout.isRefreshing = false
        loadingView.visibility = View.GONE
        errorView.visibility = View.VISIBLE
        contentView.visibility = View.GONE
        Snackbar.make(requireView(), getString(R.string.error_occurred_message), Snackbar.LENGTH_LONG).show()
    }

    /**
     * Load Pokemon List
     */
    private fun loadPokemonList() {
        loadingView.visibility = View.VISIBLE
        errorView.visibility = View.GONE
        contentView.visibility= View.GONE
        recyclerViewAdapter.clearData()
        viewModel.load()
    }

    /**
     * On Load Next Page
     */
    override fun onLoadNextPage() {
        swipeRefreshLayout.isRefreshing = true
        viewModel.loadNextPage()
    }

    /**
     * Is Pagination Enabled
     */
    override fun isPaginationEnabled(): Boolean = viewModel.pokemonListState.value?.let {
        it !is PokemonListState.OnNotFound
    } ?: true


}