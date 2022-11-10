package ru.coralcode.arty

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import ru.coralcode.arty.data.utils.*
import ru.coralcode.arty.databinding.ActivityMainBinding
import ru.coralcode.arty.ui.VMProvider

class MainActivity : AppCompatActivity(), VMProvider {

    private val binding: ActivityMainBinding by lazy { initBinding() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHost.navController.addOnDestinationChangedListener { _, navDestination, _ ->
            if (navDestination.id == R.id.home_fragment) {
                binding.bottomNav.visibility = View.VISIBLE
                binding.bottomNav.setOnItemSelectedListener { item ->
                    bottomNavListener(item.itemId, navHost.navController)
                    return@setOnItemSelectedListener true
                }
            }

            if (navDestination.id == R.id.onboarding_fragment) {
                binding.bottomNav.visibility = View.GONE
            }
        }
    }


    private fun bottomNavListener(id: Int, navController: NavController) {
        when (id) {
            R.id.nav_main ->  navController.navigate(R.id.home_fragment)
            R.id.nav_favorites ->  Log.d("tagger_navigation", "favorites")
            R.id.nav_settings ->  navController.navigate(R.id.settings_fragment)
        }
    }

    private fun initBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun <T : ViewModel> provide(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner)[clazz]

}