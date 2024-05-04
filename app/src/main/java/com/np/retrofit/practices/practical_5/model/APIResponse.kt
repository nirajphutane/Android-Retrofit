package com.np.retrofit.practices.practical_5.model

import com.google.gson.annotations.SerializedName

object APIResponse {
    data class Device (
        var id: String,
        var name: String,
        @SerializedName("data")var features: Features? = Features()
    )

    data class Features (
        @SerializedName("color") var colour: String? = null,
        var capacity: String? = null
    )
}