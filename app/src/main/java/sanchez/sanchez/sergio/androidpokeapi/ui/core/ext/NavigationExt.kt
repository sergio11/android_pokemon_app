package sanchez.sanchez.sergio.androidpokeapi.ui.core.ext

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

/**
 * Navigation Extensions
 */

/**
 * Navigate
 * @param navDirections
 */
fun Fragment.navigate(navDirections: NavDirections) {
    NavHostFragment.findNavController(this).navigate(navDirections)
}

fun Fragment.popBackStack(@IdRes idFragmentRes: Int) {
    NavHostFragment.findNavController(this).popBackStack(idFragmentRes, true)
}

fun Fragment.navigate(destination: ActivityNavigator.Destination) {
    ActivityNavigator(requireContext()).navigate(
            destination, null, null, null
    )
}

fun <T> Activity.createDestination(clazz: Class<T>, args: Bundle = Bundle()): ActivityNavigator.Destination =
        ActivityNavigator(this)
                .createDestination()
                .setIntent(Intent(this, clazz).apply {
                    putExtras(args)
                })

fun Fragment.popBackStack() {
    NavHostFragment.findNavController(this).popBackStack()
}

fun Fragment.navigateAndFinish(destination: ActivityNavigator.Destination) {
    navigate(destination)
    requireActivity().finish()
}