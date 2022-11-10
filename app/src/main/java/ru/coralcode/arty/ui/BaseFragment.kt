package ru.coralcode.arty.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T: ViewModel, VB: ViewBinding>(layoutId: Int): Fragment(layoutId) {

    protected lateinit var vm: T
    protected abstract val viewModelClass: Class<T>
    protected abstract fun getViewBinding(): VB
    protected var realBinding: VB? = null
    val binding get() = realBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = (requireActivity() as VMProvider).provide(viewModelClass, this)
        realBinding = getViewBinding()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = realBinding?.root

    override fun onDestroyView() {
        super.onDestroyView()
        realBinding = null
    }
}