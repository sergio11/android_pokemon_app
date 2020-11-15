package sanchez.sanchez.sergio.androidpokeapi.ui.features.detail

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
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
import java.util.*

/**
 * Pokemon Detail Fragment
 */
class PokemonDetailFragment: SupportFragment<PokemonDetailViewModel>(PokemonDetailViewModel::class.java) {

    private val fragmentComponent: PokemonDetailFragmentComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerComponentFactory.getPokemonDetailFragmentComponent(requireActivity() as AppCompatActivity)
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
        pokemonDetailTitleTextView.text = pokemon.name
        pokemonHeightListItem.valueText = String.format("%d m", pokemon.height)
        pokemonWeightListItem.valueText = String.format("%d kg", pokemon.weight)

        // Pokemon Sprites

        pokemon.sprites.backDefault?.let {
            pokemonSpriteBackDefault.loadFromCacheIfExists(it)
        }

        pokemon.sprites.frontDefault?.let {
            pokemonSpriteFrontDefault.loadFromCacheIfExists(it)
        }

        pokemon.sprites.backShiny?.let {
            pokemonSpriteBackShiny.loadFromCacheIfExists(it)
        }

        pokemon.sprites.frontShiny?.let {
            pokemonSpriteFrontShiny.loadFromCacheIfExists(it)
        }

        // Pokemon Types
        pokemonTypesItem.apply {
            valueText = String.format(Locale.getDefault(), getString(R.string.pokemon_types_value), pokemon.types.size)
            if(pokemon.types.isNotEmpty())
                addAction {
                    showDetailDialog(
                            titleRes = R.string.pokemon_types_dialog_detail_title,
                            items = pokemon.types.map {
                                it.name
                            }.toTypedArray()
                    )
                }
        }
        // Pokemon Abilities
        pokemonAbilitiesItem.apply {
            valueText = String.format(Locale.getDefault(), getString(R.string.pokemon_abilities_value), pokemon.abilities.size)
            if(pokemon.abilities.isNotEmpty())
                addAction {
                    showDetailDialog(
                            titleRes = R.string.pokemon_abilities_dialog_detail_title,
                            items = pokemon.abilities.map {
                                it.name
                            }.toTypedArray()
                    )
                }
        }
        // Pokemon Moves
        pokemonMovesItem.apply {
            valueText = String.format(Locale.getDefault(), getString(R.string.pokemon_moves_value),
                    pokemon.moves.size)
            if(pokemon.moves.isNotEmpty())
                addAction {
                    showDetailDialog(
                            titleRes = R.string.pokemon_moves_dialog_detail_title,
                            items = pokemon.moves.map {
                                it.name
                            }.toTypedArray()
                    )
                }
        }

        // Pokemon Stats
        pokemonStatsItem.apply {
            valueText = String.format(Locale.getDefault(), getString(R.string.pokemon_stats_value),
                    pokemon.stats.size)
            if(pokemon.stats.isNotEmpty())
                addAction {
                    showDetailDialog(
                            titleRes = R.string.pokemon_stats_dialog_detail_title,
                            items = pokemon.stats.map {
                                String.format(context.getString(R.string.pokemon_stats_item_value),
                                        it.name, it.baseStat, it.effort)
                            }.toTypedArray()
                    )
                }
        }

    }

    /**
     * on Error Occurred
     * @param ex
     */
    private fun onErrorOccurred(ex: Exception) {
        Timber.d("OnErrorOccurred -> ${ex.message}")
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(resources.getString(R.string.pokemon_detail_error))
            .setPositiveButton(resources.getString(R.string.pokemon_detail_error_accept_button)) { dialog, which ->
                popBackStack()
            }
            .show()
    }

    /**
     * Show Detail Dialog
     * @param titleRes
     * @param items
     */
   private fun showDetailDialog(@StringRes titleRes: Int, items: Array<String>) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(titleRes)
            .setItems(items, null)
            .show()
    }

}