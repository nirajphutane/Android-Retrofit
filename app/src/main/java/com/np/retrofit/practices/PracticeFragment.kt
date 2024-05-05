package com.np.retrofit.practices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.np.retrofit.R
import com.np.retrofit.utils.base_pkgs.BaseFragment
import androidx.fragment.app.viewModels
import com.np.retrofit.ui.theme.RetrofitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PracticeFragment: BaseFragment() {

    private val viewModel: PracticeFragmentViewModel by viewModels()

    @Composable
    override fun ComposeView() = View()

    @Composable
    private fun View() {
        RetrofitTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Button(
                        onClick = {
                            navigateTo(R.id.practical_fragment_1)
                        }
                    ) {
                        Text(text = "Retrofit Practical 1")
                    }

                    Button(
                        onClick = {
                            navigateTo(R.id.practical_fragment_2)
                        }
                    ) {
                        Text(text = "Retrofit Practical 2")
                    }

                    Button(
                        onClick = {
                            navigateTo(R.id.practical_fragment_3)
                        }
                    ) {
                        Text(text = "Retrofit Practical 3")
                    }

                    Button(
                        onClick = {
                            navigateTo(R.id.practical_fragment_4)
                        }
                    ) {
                        Text(text = "Retrofit Practical 4")
                    }

                    Button(
                        onClick = {
                            navigateTo(R.id.practical_fragment_5)
                        }
                    ) {
                        Text(text = "Retrofit Practical 5")
                    }

                    Button(
                        onClick = {
                            navigateTo(R.id.practical_fragment_6)
                        }
                    ) {
                        Text(text = "Retrofit Practical 6")
                    }

                    Button(
                        onClick = {
                            navigateTo(R.id.practical_fragment_7)
                        }
                    ) {
                        Text(text = "Retrofit Practical 7")
                    }

                    Button(
                        onClick = {
                            navigateTo(R.id.practical_fragment_8)
                        }
                    ) {
                        Text(text = "Retrofit Practical 8")
                    }

                    Button(
                        onClick = {
                            navigateTo(R.id.practical_fragment_9)
                        }
                    ) {
                        Text(text = "Retrofit Practical 9")
                    }

                    Button(
                        onClick = {
                            navigateTo(R.id.practical_fragment_10)
                        }
                    ) {
                        Text(text = "Retrofit Practical 10")
                    }
                }
            }
        }
    }
}