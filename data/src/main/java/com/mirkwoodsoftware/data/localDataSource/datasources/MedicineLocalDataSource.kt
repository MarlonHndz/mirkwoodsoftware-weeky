package com.mirkwoodsoftware.data.localDataSource.datasources

import com.mirkwoodsoftware.data.localDataSource.daos.MedicineDao
import com.mirkwoodsoftware.data.localDataSource.models.MedicineLocal
import com.mirkwoodsoftware.data.mappers.medicine.MedicineResponseToMedicineLocalMapper
import com.mirkwoodsoftware.data.remoteDatasource.models.MedicineResponse
import kotlinx.coroutines.flow.Flow

class MedicineLocalDataSource(
    private val medicineDao: MedicineDao,
) {

    fun getMedicineList(): Flow<List<MedicineLocal>> = medicineDao.getAll()

    suspend fun insertMedicineListFromResponse(medicines: List<MedicineLocal>) {
        medicineDao.insertAll(medicines)
    }
}
