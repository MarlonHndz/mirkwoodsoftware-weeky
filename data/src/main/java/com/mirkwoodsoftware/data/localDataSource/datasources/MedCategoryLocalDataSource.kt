package com.mirkwoodsoftware.data.localDataSource.datasources

import com.mirkwoodsoftware.data.localDataSource.daos.CategoryDao
import com.mirkwoodsoftware.data.localDataSource.models.CategoryLocal
import kotlinx.coroutines.flow.Flow

class MedCategoryLocalDataSource(
    private val categoryDao: CategoryDao,
) {
    fun getCategoriesList(): Flow<List<CategoryLocal>> = categoryDao.getAll()

    suspend fun insertCategoriesList(categories: List<CategoryLocal>){
        categoryDao.insertAll(categories)
    }
}
