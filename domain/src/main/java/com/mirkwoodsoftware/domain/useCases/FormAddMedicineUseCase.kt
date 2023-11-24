package com.mirkwoodsoftware.domain.useCases

import com.mirkwoodsoftware.domain.models.MedCategory
import com.mirkwoodsoftware.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow

class FormAddMedicineUseCase(
    private val categoryRepository: CategoryRepository
) {

    suspend fun getCategories(): Flow<List<MedCategory>> {
        return categoryRepository.getMedCategories()
    }
}
