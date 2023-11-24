package com.mirkwoodsoftware.data.repositories

import com.mirkwoodsoftware.data.localDataSource.datasources.MedicineLocalDataSource
import com.mirkwoodsoftware.data.mappers.medicine.MedicineLocalToMedicineMapper
import com.mirkwoodsoftware.data.mappers.medicine.MedicineResponseToMedicineLocalMapper
import com.mirkwoodsoftware.data.mappers.medicine.MedicineResponseToMedicineMapper
import com.mirkwoodsoftware.data.remoteDatasource.datasources.MedicineRemoteDataSource
import com.mirkwoodsoftware.data.repositories.base.BaseRepositoryImpl
import com.mirkwoodsoftware.domain.models.Medicine
import com.mirkwoodsoftware.domain.models.network.Result
import com.mirkwoodsoftware.domain.models.network.ServiceCalled
import com.mirkwoodsoftware.domain.repositories.MedicineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class MedicineRepositoryImplImpl(
    private val medicineRemoteDataSource: MedicineRemoteDataSource,
    private val medicineLocalDataSource: MedicineLocalDataSource,
    private val medicineLocalToMedicineMapper: MedicineLocalToMedicineMapper,
    private val medicineResponseToMedicineMapper: MedicineResponseToMedicineMapper,
    private val medicineResponseToMedicineLocalMapper: MedicineResponseToMedicineLocalMapper
) : BaseRepositoryImpl(), MedicineRepository {

    override suspend fun getMedicines(): Flow<List<Medicine>> {
        makeApiCallWithGlobalHandler(
            call = { medicineRemoteDataSource.getMedicineList() },
            saveCallResponseLocally = {
                medicineLocalDataSource.insertMedicineListFromResponse(
                    medicineResponseToMedicineLocalMapper(it)
                )
            },
            serviceCalled = ServiceCalled.MEDICINE_GET,
        )
        return medicineLocalDataSource.getMedicineList()
            .map { list -> list.map { medicineLocalToMedicineMapper(it) } }
    }

    override suspend fun getMedicinesWithNetworkStatus(): Flow<Result<List<Medicine>>> = flow {
        val serviceCalled = ServiceCalled.MEDICINE_GET

        // Start Loading
        emit(Result.Loading(serviceCalled))

        // make Api call
        val result = makeSafeApiCall({ medicineRemoteDataSource.getMedicineList() }, serviceCalled)

        // posting values
        when (result) {
            is Result.Loading -> emit(Result.Loading(serviceCalled))
            is Result.Success -> {
                medicineLocalDataSource.insertMedicineListFromResponse(
                    medicineResponseToMedicineLocalMapper(result.data)
                )
                emit(Result.Success(medicineResponseToMedicineMapper(result.data), serviceCalled))
            }

            is Result.Error -> {
                emit(Result.Error(result.exception, serviceCalled = serviceCalled))
                // Emit LocalItems
                val localItems = medicineLocalDataSource.getMedicineList().firstOrNull()
                    ?.let { list -> list.map { medicineLocalToMedicineMapper(it) } }
                localItems?.let { emit(Result.Success(it, serviceCalled)) }
            }
        }
    }
}
