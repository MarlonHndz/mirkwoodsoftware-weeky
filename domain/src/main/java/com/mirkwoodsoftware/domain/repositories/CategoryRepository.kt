package com.mirkwoodsoftware.domain.repositories

import com.mirkwoodsoftware.domain.models.MedCategory
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getMedCategories(): Flow<List<MedCategory>>
}
