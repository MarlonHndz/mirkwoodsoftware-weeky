package com.mirkwoodsoftware.data.remoteDatasource.datasources

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mirkwoodsoftware.data.remoteDatasource.models.CategoryResponse
import com.mirkwoodsoftware.data.utils.StringUtils.getJsonDataFromAssetAsString

class MedCategoryRemoteDataSource(
    private val context: Context
) {

    suspend fun getCategoryResponse(): CategoryResponse {
        val jsonFileString = getJsonDataFromAssetAsString(context, CATEGORY_JSON_FILE_NAME)
        val categoryResponseType = object : TypeToken<CategoryResponse>() {}.type
        val myCategoryResponse: CategoryResponse =
            Gson().fromJson(jsonFileString, categoryResponseType)

        Log.e("--->data", "> Item:\n$myCategoryResponse")

        return myCategoryResponse
    }

    companion object {
        const val CATEGORY_JSON_FILE_NAME = "weeky_medicine_categories.json"
    }
}
