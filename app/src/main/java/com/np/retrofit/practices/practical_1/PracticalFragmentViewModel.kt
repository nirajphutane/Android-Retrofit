package com.np.retrofit.practices.practical_1

import androidx.lifecycle.viewModelScope
import com.np.retrofit.core.appLog
import com.np.retrofit.practices.practical_1.api_provider.ApiClient
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
        fetchPostByCall(1)
        fetchPostByResponse(2)
    }

    private fun fetchPostByCall(postId: Int) {
        val call = ApiClient.apiClient.getPostsByCall(postId)

        appLog("URL: ${call.request().url}")
        appLog("Header: ${call.request().headers}")

        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                appLog("Code: ${response.code()}")
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
            override fun onFailure(call: Call<Any>, t: Throwable) {
                appLog("Failure: ${t.message}")
            }
        })
    }

    private fun fetchPostByResponse(postId: Int) = viewModelScope.launch {
        val response = ApiClient.apiClient.getPostsByResponse(postId)

        appLog("URL: ${response.raw().request.url}")
        appLog("Header: ${response.raw().headers}")
        appLog("Code: ${response.code()}")
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