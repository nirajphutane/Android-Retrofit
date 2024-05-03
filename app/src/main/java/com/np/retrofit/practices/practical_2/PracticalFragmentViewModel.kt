package com.np.retrofit.practices.practical_2

import androidx.lifecycle.viewModelScope
import com.np.retrofit.core.appLog
import com.np.retrofit.practices.practical_2.api_provider.ApiClient
import com.np.retrofit.practices.practical_2.model.APIResponse
import com.np.retrofit.utils.base_pkgs.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PracticalFragmentViewModel @Inject constructor(): BaseViewModel() {

    init {
        fetchDataByCall()
        fetchDataByResponse()
    }

    private fun fetchDataByCall() {
        val call = ApiClient.apiClient.getDataByCall()
        call.enqueue(object : Callback<List<APIResponse.Phone>> {
            override fun onResponse(call: Call<List<APIResponse.Phone>>, response: Response<List<APIResponse.Phone>>) {
                // Handle the retrieved data
                if (response.isSuccessful) {
                    appLog("Body: ${response.body()}")
                }
                // Handle error
                else {
                    appLog("Error: ${response.errorBody()}")
                }
            }

            // Handle failure
            override fun onFailure(call: Call<List<APIResponse.Phone>>, t: Throwable) {
                appLog("Failure: ${t.message}")
            }
        })
    }

    private fun fetchDataByResponse() = viewModelScope.launch {
        val response = ApiClient.apiClient.getDataByResponse()

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