package com.mohamedabdelaziz.ahoytask.core.utils

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.text.SimpleDateFormat
import java.util.*

class Common {
    companion object {
        fun convertUnixToDate(dt: Long): String? {
            val date = Date(dt * 1000L)
            val simpleDateFormat = SimpleDateFormat("dd MMM yyyy")
            return simpleDateFormat.format(date)
        }

        fun getTemperatureType(temp: String): String {
            var result: String = ""
            if (temp == "" || temp == "metric")
                result = "C"
            else if (temp == "imperial")
                result = "F"

            return result
        }

    }
}