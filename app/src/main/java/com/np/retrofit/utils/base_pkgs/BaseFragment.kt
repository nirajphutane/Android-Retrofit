package com.np.retrofit.utils.base_pkgs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment: Fragment {

    constructor() : super()

    @Composable
    protected abstract fun ComposeView ()

    @ContentView
    constructor(@LayoutRes containLayoutId: Int): super(containLayoutId)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireActivity()).apply {
            setContent {
                ComposeView()
            }
        }
    }

    fun navigateTo(fragmentId: Int) = findNavController().navigate(fragmentId)
}