package com.np.retrofit.practices.practical_6

import androidx.lifecycle.viewModelScope
import com.np.retrofit.core.appLog
import com.np.retrofit.practices.practical_6.api_provider.ApiClient
import com.np.retrofit.utils.base_pkgs.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PracticalFragmentViewModel @Inject constructor(): BaseViewModel() {

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        getWithStaticHeaders()
        getWithDynamicHeaders()
        getWithHeaderMap()
    }

    private suspend fun getWithStaticHeaders() {
        showResponse(ApiClient.apiClient.getWithStaticHeaders())
    }

    private suspend fun getWithDynamicHeaders() {
        showResponse(ApiClient.apiClient.getWithDynamicHeaders(loginId = "MyLogin", password = "MyPassword"))
    }

    private suspend fun getWithHeaderMap() {
        val headers = mapOf(
            "loginId" to "MyLogin",
            "password" to "MyPassword"
        )
        showResponse(ApiClient.apiClient.getWithHeaderMap(headers))
    }

    private fun showResponse(response: Response<Any>) {
        // Handle the retrieved data
        if (response.isSuccessful) {
            appLog("Body: ${response.body()}")
        }
        // Handle error
        else {
            appLog("Error: ${response.errorBody()}")
        }
    }
}