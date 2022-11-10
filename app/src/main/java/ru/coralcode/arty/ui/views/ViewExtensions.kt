package ru.coralcode.arty.ui.views

import android.view.View
import android.widget.TextView

fun setMultipleClickListeners(vararg views: View, action: (View) -> Unit) =
    views.forEach { it.setOnClickListener { view -> action(view)} }

fun setMultipleClickListeners(viewsList: List<View>, action: (View) -> Unit) =
    viewsList.forEach { it.setOnClickListener { view -> action(view) } }

fun TextView.hideCompoundDrawables() {
    this.compoundDrawablesRelative.forEach { it.alpha = 0 }
}

fun TextView.showCompoundDrawables() {
    this.compoundDrawablesRelative.forEach { it.alpha = 255 }
}

fun hideCompoundDrawables(vararg textViews: TextView) =
    textViews.forEach { it.hideCompoundDrawables() }

