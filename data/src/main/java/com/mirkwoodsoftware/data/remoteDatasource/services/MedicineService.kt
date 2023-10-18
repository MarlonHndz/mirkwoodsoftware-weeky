package com.mirkwoodsoftware.data.remoteDatasource.services

import com.mirkwoodsoftware.data.remoteDatasource.models.MedicineResponse
import retrofit2.Response
import retrofit2.http.GET

interface MedicineService {

    @GET("pokemon/")
    suspend fun getMedicineList(): Response<MedicineResponse>
}
