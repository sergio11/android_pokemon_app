package sanchez.sanchez.sergio.androidpokeapi.ui.features.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.pokemon_detail.*
import sanchez.sanchez.sergio.androidpokeapi.R
import sanchez.sanchez.sergio.androidpokeapi.di.component.pokemon.PokemonDetailFragmentComponent
import sanchez.sanchez.sergio.androidpokeapi.di.factory.DaggerComponentFactory
import sanchez.sanchez.sergio.androidpokeapi.domain.models.PokemonDetail
import sanchez.sanchez.sergio.androidpokeapi.ui.core.SupportFragment
import sanchez.sanchez.sergio.androidpokeapi.ui.core.ext.loadFromCacheIfExists
import sanchez.sanchez.sergio.androidpokeapi.ui.core.ext.popBackStack
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * Pokemon Detail Fragment
 */
class PokemonDetailFragment: SupportFragment<PokemonDetailViewModel>(PokemonDetailViewModel::class.java) {

    private val fragmentComponent: PokemonDetailFragmentComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerComponentFactory.getPokemonDetailFragmentComponent(requireActivity() as AppCompatActivity)
    }

    private val modifiedAtDateFormat by lazy {
        SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.getDefault())
    }

    private val args by navArgs<PokemonDetailFragmentArgs>()

    override fun layoutId(): Int = R.layout.pokemon_detail

    override fun onInject() {
        fragmentComponent.inject(this)
    }

    override fun onObserverLiveData(viewModel: PokemonDetailViewModel) {
        super.onObserverLiveData(viewModel)
        // Observe operation result
        viewModel.pokemonDetailState.observe(this, {
            when(it) {
                is PokemonDetailState.OnSuccess -> onLoadSuccessfully(it.pokemon)
                is PokemonDetailState.OnError -> onErrorOccurred(it.ex)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        placeholderImageView.resume()
    }

    override fun onStop() {
        super.onStop()
        placeholderImageView.pause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load Character Detail
        viewModel.loadByName(args.name)
    }

    /**
     * Private Methods
     */

    /**
     * on Load Successfully
     * @param pokemon
     */
    private fun onLoadSuccessfully(pokemon: PokemonDetail) {

        pokemonThumbnailImageView.loadFromCacheIfExists(pokemon.imageUrl)
        pokemonNameAndIdListItem.valueText = String.format("%s #%d", pokemon.name, pokemon.id)
        pokemonNameTextView.text = pokemon.name
        pokemonHeightListItem.valueText = String.format("%d m", pokemon.height)
        pokemonWeightListItem.valueText = String.format("%d kg", pokemon.weight)

        /*if(pokemon.description.isNotEmpty())
            characterDescriptionListItem.valueText = pokemon.description
        characterDetailTitleTextView.text = pokemon.name
        pokemon.modified?.let {
            characterModifiedAtItem.valueText = modifiedAtDateFormat.format(it)
        }

        characterComicItem.apply {
            valueText = String.format(Locale.getDefault(),
                getString(R.string.character_comic_value), pokemon.comics.available)
            if(pokemon.comics.items.isNotEmpty())
                addAction {
                    showDetailDialog(
                        titleRes = R.string.character_comic_dialog_detail_title,
                        items = pokemon.comics.items
                    )
                }
        }

        characterSeriesItem.apply {
            valueText = String.format(Locale.getDefault(),
                getString(R.string.character_series_value), pokemon.series.available)
            if(pokemon.series.items.isNotEmpty())
                addAction {
                    showDetailDialog(
                        titleRes = R.string.character_series_dialog_detail_title,
                        items = pokemon.series.items
                    )
                }
        }

        characterEventsItem.apply {
            valueText = String.format(Locale.getDefault(),
                getString(R.string.character_events_value), pokemon.events.available)
            if(pokemon.events.items.isNotEmpty())
                addAction {
                    showDetailDialog(
                        titleRes = R.string.character_events_dialog_detail_title,
                        items = pokemon.events.items
                    )
                }
        }


*/
    }

    /**
     * on Error Occurred
     * @param ex
     */
    private fun onErrorOccurred(ex: Exception) {
        Timber.d("OnErrorOccurred -> ${ex.message}")
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(resources.getString(R.string.character_detail_error))
            .setPositiveButton(resources.getString(R.string.character_detail_error_accept_button)) { dialog, which ->
                popBackStack()
            }
            .show()
    }

    /**
     * Show Detail Dialog
     * @param titleRes
     * @param items
     */
   /** private fun showDetailDialog(@StringRes titleRes: Int, items: List<ComicsItem>) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(titleRes)
            .setItems(items.map { it.name }.toTypedArray(), null)
            .show()
    }
*/

}