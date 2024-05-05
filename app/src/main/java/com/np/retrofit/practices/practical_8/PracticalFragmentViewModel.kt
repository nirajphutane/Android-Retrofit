package com.np.retrofit.practices.practical_8

import com.np.retrofit.core.appLog
import com.np.retrofit.practices.practical_8.api_provider.ApiClient
import com.np.retrofit.utils.base_pkgs.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PracticalFragmentViewModel @Inject constructor(): BaseViewModel() {

    private var job: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.message?.let{
            appLog("Failure: $it")
        }
    }

    init {
        fetch()
    }

    private fun fetch() {
        syncCall()
        asyncCall()
    }

    private fun syncCall() {
        job = CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            val response: Response<Any> = ApiClient.apiClient.get().execute()
            withContext(Dispatchers.Main) {
                showResponse(response)
            }
        }
    }

    private fun asyncCall() {
        ApiClient.apiClient.get().enqueue(
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

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}