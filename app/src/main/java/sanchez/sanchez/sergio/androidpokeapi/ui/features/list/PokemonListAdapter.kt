package sanchez.sanchez.sergio.androidpokeapi.ui.features.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import sanchez.sanchez.sergio.androidpokeapi.R
import sanchez.sanchez.sergio.androidpokeapi.domain.models.Pokemon
import sanchez.sanchez.sergio.androidpokeapi.ui.core.ext.loadFromCacheIfExists
import timber.log.Timber

/**
 * Pokemon List Adapter
 */
class PokemonListAdapter(
    private val context: Context,
    private val data: MutableList<Pokemon>,
    private val pokemonItemListener: OnPokemonClickListener,
    private val paginationCallBack: IPaginationCallBack? = null
): RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {


    /**
     * Create View Holder
     * @param parent
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(inflateLayout(R.layout.pokemon_item_list, parent))

    /**
     * On Bind Model to View Holder
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        Timber.d("PAGINATION -> onBindViewHolder CALLED")
        data.getOrNull(position)?.let {
            holder.bind(it)
        }
        paginationCallBack?.let { callback ->
            if(callback.isPaginationEnabled()) {
                if(position+1 == data.size) {
                    callback.onLoadNextPage()
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    /**
     * Update Adapter Data
     * @param newData
     */
    fun addData(newData: List<Pokemon>) {
        data.apply {
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    /**
     * Clear Data
     */
    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    /**
     * Replace Data
     * @param newData
     */
    fun replaceData(newData: List<Pokemon>) {
        data.clear()
        addData(newData)
    }

    /**
     * Private Methods
     */

    /**
     * Inflate Layout
     */
    private fun inflateLayout(@LayoutRes layoutRest: Int, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return inflater.inflate(layoutRest, parent, false)
    }

    interface OnPokemonClickListener {
        fun onPokemonClicked(pokemon: Pokemon)
    }

    /**
     * Pokemon View Holder
     * @param itemView
     */
    inner class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) {
            itemView.apply {
                setOnClickListener { pokemonItemListener.onPokemonClicked(pokemon) }
                findViewById<ImageView>(R.id.pokemonThumbnail).loadFromCacheIfExists(pokemon.imageUrl)
                findViewById<TextView>(R.id.pokemonNameTextView).text = pokemon.name
                findViewById<TextView>(R.id.pokemonIdTextView).text = String.format("#%d", pokemon.id)
             }
        }
    }

}