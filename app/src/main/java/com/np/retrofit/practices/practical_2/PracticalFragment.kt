package com.np.retrofit.practices.practical_2

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.np.retrofit.utils.base_pkgs.BaseFragment
import com.np.retrofit.ui.theme.RetrofitTheme

class PracticalFragment: BaseFragment() {

    private lateinit var viewModel: PracticalFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PracticalFragmentViewModel::class.java]
    }

    @Composable
    override fun ComposeView() = View()

    @Composable
    private fun View() {
        RetrofitTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "Retrofit Practice 2"
                    )
                }
            }
        }
    }
}