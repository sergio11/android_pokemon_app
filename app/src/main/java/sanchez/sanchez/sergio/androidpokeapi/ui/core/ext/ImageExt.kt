package sanchez.sanchez.sergio.androidpokeapi.ui.core.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import sanchez.sanchez.sergio.androidpokeapi.R

/**
 * Image Extensions
 */

/**
 * Load resource from cache if exists otherwise make the http call
 * @param resourcePath
 */
fun ImageView.loadFromCacheIfExists(resourcePath: String) {
    Glide.with(context)
            .load(resourcePath)
            .transition(
                DrawableTransitionOptions.withCrossFade(
                    DrawableCrossFadeFactory.Builder()
                        .setCrossFadeEnabled(true)
                        .build()
                )
            )
            .apply(RequestOptions.skipMemoryCacheOf(false))
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
            .placeholder(R.drawable.pokeball_placeholder)
            .error(R.drawable.pokeball_placeholder)
            .fitCenter()
            .into(this)
}