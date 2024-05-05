package com.np.retrofit.practices.practical_9

import com.np.retrofit.core.appLog
import com.np.retrofit.practices.practical_9.api_provider.ApiClient
import com.np.retrofit.utils.base_pkgs.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PracticalFragmentViewModel @Inject constructor(): BaseViewModel() {

    init {
        fetch()
    }

    private fun fetch() {
        call()
    }

    private fun call() {
        val call: Call<Any> = ApiClient.apiClient.get()
        call.enqueue(
            object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    showResponse(response)
                }
                override fun onFailure(call: Call<Any>, t: Throwable) {
                    if(call.isCanceled) {
                        appLog("Cancel: ${t.message}")
                    } else {
                        appLog("Failure: ${t.message}")
                    }
                }
            }
        )

        Thread.sleep(100)
        call.cancel()
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