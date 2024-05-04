package com.np.retrofit.practices.practical_5

import androidx.lifecycle.viewModelScope
import com.np.retrofit.core.appLog
import com.np.retrofit.practices.practical_5.api_provider.ApiClient
import com.np.retrofit.practices.practical_5.model.APIResponse
import com.np.retrofit.utils.base_pkgs.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PracticalFragmentViewModel @Inject constructor(): BaseViewModel() {

    init {
       fetch()
    }

    private fun fetch() = viewModelScope.launch {
        postByBody()
        postByMap()
        postByDataModel()
        postByMapField()
        postByFiled()
        postListByFiled()
    }

    private suspend fun postByBody() {
        val json: String = JSONObject(
            mapOf(
                "name" to "Apple AirPods",
                "data" to mapOf(
                    "generation" to "3rd",
                    "price" to 120
                )
            )
        ).toString()

        showResponse(ApiClient.apiClient.postByBody(json))
    }

    private suspend fun postByMap() {

        val details = mapOf(
            "id" to "10",
            "name" to "Apple AirPods"
        )

        showResponse(ApiClient.apiClient.postByMap(details))
    }

    private suspend fun postByDataModel() {

        val details = APIResponse.Device(
            id = "1",
            name = "Apple AirPods",
            features = APIResponse.Features(
                colour = "Black",
                capacity = null
            )
        )

        showResponse(ApiClient.apiClient.postByDataModel(details))
    }

    private suspend fun postByMapField() {

        val details = mapOf(
            "id" to "10",
            "name" to "Apple AirPods"
        )

        showResponse(ApiClient.apiClient.postByFieldMap(details))
    }

    private suspend fun postByFiled() {
        showResponse(ApiClient.apiClient.postByFiled(id = "10", model = "Apple AirPods"))
    }

    private suspend fun postListByFiled() {
        showResponse(ApiClient.apiClient.postListByFiled(listOf("1", "2", "3")))
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