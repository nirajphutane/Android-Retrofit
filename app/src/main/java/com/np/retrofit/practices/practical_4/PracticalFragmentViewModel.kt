package com.np.retrofit.practices.practical_4

import androidx.lifecycle.viewModelScope
import com.np.retrofit.practices.practical_4.api_provider.ApiClient
import com.np.retrofit.utils.base_pkgs.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticalFragmentViewModel @Inject constructor(): BaseViewModel() {

    init {
        getWithNoQuery()
        getWithStaticQuery()
        getWithDynamicQueryEncoded()
        getWithDynamicQueryNoEncoded()
        getWithDynamicOptionalQueryEncoded()
        getWithQueryEncodedNullable()
        getWithQueryMapEncoded()
        getWithPathEncoded()
        getDynamicURLBypass()
    }

    private fun getWithNoQuery() = viewModelScope.launch {
        ApiClient.apiClient.getWithNoQuery()
    }

    private fun getWithStaticQuery() = viewModelScope.launch {
        ApiClient.apiClient.getWithStaticQuery()
    }

    private fun getWithDynamicQueryEncoded() = viewModelScope.launch {
        ApiClient.apiClient.getWithDynamicQueryEncoded(id = 4, model = "Apple iPhone 11")
        ApiClient.apiClient.getWithDynamicQueryEncoded(id = 4, model = "Apple+iPhone+11")
    }

    private fun getWithDynamicQueryNoEncoded() = viewModelScope.launch {
        ApiClient.apiClient.getWithDynamicQueryEncoded(id = 4, model = "Apple iPhone 11")
        ApiClient.apiClient.getWithDynamicQueryNoEncoded(id = 4, model = "Apple+iPhone+11")
    }

    private fun getWithDynamicOptionalQueryEncoded() = viewModelScope.launch {
        ApiClient.apiClient.getWithDynamicOptionalQueryEncoded(id = 4, model = null)
    }

    private fun getWithQueryEncodedNullable() = viewModelScope.launch {
        ApiClient.apiClient.getWithDynamicQueryInNullableList(listOf(1, null, 3))
    }

    private fun getWithQueryMapEncoded() = viewModelScope.launch {
        ApiClient.apiClient.getWithQueryMapEncoded(
            mapOf(
                "id" to "4",
                "name" to "Apple iPhone 11"
            )
        )
    }

    private fun getWithPathEncoded() = viewModelScope.launch {
        ApiClient.apiClient.getWithPathEncoded(4, "Apple iPhone 11")
    }

    private fun getDynamicURLBypass() = viewModelScope.launch {
        ApiClient.apiClient.getDynamicURLBypass("object/4")
        ApiClient.apiClient.getDynamicURLBypass("https://example.com/object/4")
    }
}