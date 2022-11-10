package ru.coralcode.arty.utils

import android.content.Context


/**
 * Can be used by any class.
 */

interface ContextDependentComponent<T> {

    fun initialize(context: Context): T

}