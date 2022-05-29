package com.mohamedabdelaziz.ahoytask.domain.model

import com.google.gson.annotations.SerializedName


data class ForecastResponse(
    @SerializedName("city") var city: City? = City(),
    @SerializedName("cod") var cod: String? = null,
    @SerializedName("message") var message: Double? = null,
    @SerializedName("cnt") var cnt: Int? = null,
    @SerializedName("list") var list: ArrayList<ForecastList> = arrayListOf()

)