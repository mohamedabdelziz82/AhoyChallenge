package com.mohamedabdelaziz.ahoytask.domain.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Clouds(
    @SerializedName("all") var all: Int? = null
)