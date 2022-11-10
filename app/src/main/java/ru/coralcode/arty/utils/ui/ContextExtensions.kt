package ru.coralcode.arty.utils.ui

import android.content.Context
import android.content.res.Configuration


fun Context.isNightMode() =
    resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
