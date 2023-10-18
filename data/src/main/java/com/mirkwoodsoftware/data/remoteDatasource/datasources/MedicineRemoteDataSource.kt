package com.mirkwoodsoftware.data.remoteDatasource.datasources

import com.mirkwoodsoftware.data.remoteDatasource.services.MedicineService

class MedicineRemoteDataSource(
    private val medicineService: MedicineService
) {
    suspend fun getMedicineList() = medicineService.getMedicineList()
}
