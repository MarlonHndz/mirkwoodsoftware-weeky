package com.mirkwoodsoftware.domain.useCases

import com.mirkwoodsoftware.domain.models.Medicine
import com.mirkwoodsoftware.domain.repositories.MedicineRepository
import kotlinx.coroutines.flow.Flow

class MedicineUseCase(
    private val medicineRepository: MedicineRepository
) {
    suspend fun getMedicines(): Flow<List<Medicine>> {
        return medicineRepository.getMedicines()
    }
}
