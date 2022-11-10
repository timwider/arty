package ru.coralcode.arty.ui.home

import android.os.Bundle
import android.view.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.coralcode.arty.R
import ru.coralcode.arty.databinding.HomeFragmentBinding
import ru.coralcode.arty.ui.BaseFragment

class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding>(R.layout.home_fragment) {

    override val viewModelClass: Class<HomeViewModel> = HomeViewModel::class.java

    override fun getViewBinding(): HomeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        CoroutineScope(Dispatchers.Main).launch {

            with(requireBinding()) {
                loadingPicView.startLoading(this@launch)
                delay(5000)
                loadingPicView.stopLoading()
                loadingPicView.visibility = View.GONE
                rvArtworks.visibility = View.VISIBLE
            }
        }
    }

}