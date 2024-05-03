package com.np.retrofit

import android.os.Bundle
import androidx.activity.viewModels
import com.np.retrofit.utils.base_pkgs.MainActivityViewModel
import com.np.retrofit.utils.base_pkgs.WrapperActivity
import com.np.retrofit.utils.base_pkgs.viewBinding
import com.np.retrofit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : WrapperActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}