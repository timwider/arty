package ru.coralcode.arty.ui.onboarding

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.coralcode.arty.R
import ru.coralcode.arty.databinding.OnboardingFragmentBinding
import ru.coralcode.arty.ui.BaseFragment


class OnboardingFragment :
    BaseFragment<OnboardingViewModel, OnboardingFragmentBinding>(R.layout.onboarding_fragment) {

    override val viewModelClass: Class<OnboardingViewModel> =
        OnboardingViewModel::class.java
    override fun getViewBinding(): OnboardingFragmentBinding =
        OnboardingFragmentBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSpans()

        requireBinding().btnTakeALook.setOnClickListener { navigateHome() }
    }

    private fun setSpans() {
        // powered by
        val startIndex = 11
        val endIndex = requireBinding().tvPoweredBy.text.toString().lastIndex + 1
        val span = ForegroundColorSpan(resources.getColor(R.color.main_pink, null))
        val ssb = SpannableStringBuilder(requireBinding().tvPoweredBy.text.toString())
        ssb.setSpan(span, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        requireBinding().tvPoweredBy.text = ssb
        ssb.clear()
        ssb.clearSpans()

        val tvArtistsAndArtworksFirstIndicesPair = Pair(0, 3)
        val tvArtistsAndArtworksSecondIndicesPair = Pair(11, 16)
        val tvArtistsAndArtworksThirdIndicesPair = Pair(
            requireBinding().artistsAndArtworks.text.toString().lastIndex - 10,
            requireBinding().artistsAndArtworks.text.toString().lastIndex + 1
        )

        val secondSSB = SpannableStringBuilder(requireBinding().artistsAndArtworks.text.toString())

        secondSSB.setSpan(span, tvArtistsAndArtworksFirstIndicesPair.first, tvArtistsAndArtworksFirstIndicesPair.second, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        secondSSB.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.main_pink, null)),
        tvArtistsAndArtworksSecondIndicesPair.first, tvArtistsAndArtworksSecondIndicesPair.second,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        secondSSB.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.main_pink, null)),
            tvArtistsAndArtworksThirdIndicesPair.first, tvArtistsAndArtworksThirdIndicesPair.second,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        requireBinding().artistsAndArtworks.text = secondSSB
    }

    private fun navigateHome() {
        findNavController().navigate(R.id.action_onboarding_to_home)
    }
}
