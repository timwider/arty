package ru.coralcode.arty.ui.settings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.coralcode.arty.data.utils.AppTheme
import ru.coralcode.arty.ui.views.CoolArrow

class SettingsViewModel: ViewModel() {

    private var coolArrowPosition = MutableLiveData(CoolArrow.Position.UP)

    fun onPositionChanged(value: CoolArrow.Position) {
        coolArrowPosition.value = value
    }

    fun coolArrowPosition() = coolArrowPosition

    fun onThemeSelected(value: AppTheme) {
        Log.d("theme_value_vm", value.toString())
    }
}