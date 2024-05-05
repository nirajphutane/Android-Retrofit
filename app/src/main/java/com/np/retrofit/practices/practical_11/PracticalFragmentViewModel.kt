package com.np.retrofit.practices.practical_11

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.np.retrofit.core.appLog
import com.np.retrofit.practices.practical_11.api_provider.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PracticalFragmentViewModel (private val application: Application): AndroidViewModel(application) {

    init {
        fetch()
    }

    private fun fetch() {
        call()
    }

    private fun call() {
        ApiClient(application.applicationContext).apiClient.get().enqueue(
            object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    showResponse(response)
                }
                override fun onFailure(call: Call<Any>, t: Throwable) {
                    appLog("Failure: ${t.message}")
                }
            }
        )
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