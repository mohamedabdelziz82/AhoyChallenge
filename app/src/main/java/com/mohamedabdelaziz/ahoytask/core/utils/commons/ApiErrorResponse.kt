package com.mohamedabdelaziz.ahoytask.core.utils.commons

import com.google.gson.annotations.SerializedName

class ApiErrorResponse(
    @field:SerializedName("error") val error: String, httpCode: Int, @field:SerializedName(
        "details"
    ) override val details: String
) :
    BaseApiErrorResponse(httpCode, details) {

    @SerializedName("error_description")
    val desc: String? = null

    @SerializedName("en")
    val en: String? = null

    @SerializedName("ar")
    val ar: String? = null

    @JvmName("getDetails1")
    fun getDetails(): String {
        return details
    }

    override val message: String
        get() = error
    override val description: String?
        get() = desc

}
