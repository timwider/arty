package ru.coralcode.arty.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import ru.coralcode.arty.R
import ru.coralcode.arty.data.utils.AppTheme
import ru.coralcode.arty.databinding.SettingsFragmentBinding
import ru.coralcode.arty.ui.BaseFragment
import ru.coralcode.arty.ui.views.CoolArrow
import ru.coralcode.arty.ui.views.hideCompoundDrawables
import ru.coralcode.arty.ui.views.setMultipleClickListeners
import ru.coralcode.arty.ui.views.showCompoundDrawables

private const val TV_THEME_DRAWABLE_POSITION = 2
private const val ARTIC_EDU_LINK = "https://artic.edu"
private const val ALPHA_VISIBLE = 255
private const val ALPHA_INVISIBLE = 0


class SettingsFragment :
    BaseFragment<SettingsViewModel, SettingsFragmentBinding>(R.layout.settings_fragment) {
    override val viewModelClass: Class<SettingsViewModel> = SettingsViewModel::class.java
    override fun getViewBinding(): SettingsFragmentBinding =
        SettingsFragmentBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            setMultipleClickListeners(tvPaintingOfTheDay, tvPaintingOfTheDayDescription, ivImage)
            { switchPaintingOfTheDay.performClick() }

            setMultipleClickListeners(ivVisitMuseum, tvVisitMuseum, tvVisitMuseumDescription)
            { openMuseumLink() }

            coolArrow.setOnClickListener {
                (it as CoolArrow)
                    .onClick(CoroutineScope(Dispatchers.Main)) { pos -> vm.onPositionChanged(pos) }
            }
        }
        val themeTextViews = listOf(binding.tvThemeLight, binding.tvThemeDark, binding.tvThemeSystem,)
        themeTextViews.forEach { tvTheme ->
            tvTheme.compoundDrawablesRelative[TV_THEME_DRAWABLE_POSITION].alpha = ALPHA_INVISIBLE
            tvTheme.setOnClickListener { onThemeSelected(it as TextView, themeTextViews) }
        }
        vm.coolArrowPosition().observe(viewLifecycleOwner) {
            toggleThemeTextViewsVisibility(themeTextViews, it)
        }
    }

    private fun toggleThemeTextViewsVisibility(tvs: List<TextView>, pos: CoolArrow.Position) {
        val themeTextViewsVisibility = if (pos == CoolArrow.Position.UP) {
            View.GONE
        } else View.VISIBLE
        tvs.forEach { it.visibility = themeTextViewsVisibility }
    }

    private fun onThemeSelected(tvTheme: TextView, all: List<TextView>) {
        all.forEach { it.hideCompoundDrawables() }
        tvTheme.showCompoundDrawables()
        val selectedTheme = tvTheme.tag as AppTheme
        vm.onThemeSelected(selectedTheme)
    }

    private fun openMuseumLink() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(ARTIC_EDU_LINK))
        startActivity(intent)
    }

    private fun setupThemeTextViews() {
        with(binding) {
            hideCompoundDrawables(tvThemeLight, tvThemeDark, tvThemeSystem)
            tvThemeLight.tag = AppTheme.LIGHT
            tvThemeDark.tag = AppTheme.DARK
            tvThemeSystem.tag = AppTheme.SYSTEM
        }
    }
}