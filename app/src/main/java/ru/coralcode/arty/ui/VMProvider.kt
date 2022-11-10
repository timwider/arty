package ru.coralcode.arty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner

interface VMProvider {

    fun <T: ViewModel> provide(clazz: Class<T>, owner: ViewModelStoreOwner): T
}