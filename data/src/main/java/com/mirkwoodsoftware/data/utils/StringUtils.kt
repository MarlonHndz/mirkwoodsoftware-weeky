package com.mirkwoodsoftware.data.utils

import android.content.Context
import com.mirkwoodsoftware.domain.utils.StringUtils.EMPTY_STRING
import java.io.IOException

object StringUtils {

    fun getJsonDataFromAssetAsString(context: Context, fileName: String): String {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return EMPTY_STRING
        }
        return jsonString
    }
}