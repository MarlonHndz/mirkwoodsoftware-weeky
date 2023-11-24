package com.mirkwoodsoftware.data.repositories

import com.mirkwoodsoftware.data.localDataSource.datasources.MedCategoryLocalDataSource
import com.mirkwoodsoftware.data.mappers.category.CategoryLocalToMedCategoryMapper
import com.mirkwoodsoftware.data.mappers.category.CategoryResponseToCategoryLocalMapper
import com.mirkwoodsoftware.data.remoteDatasource.datasources.MedCategoryRemoteDataSource
import com.mirkwoodsoftware.domain.models.MedCategory
import com.mirkwoodsoftware.domain.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val medCategoryRemoteDataSource: MedCategoryRemoteDataSource,
    private val medCategoryLocalDataSource: MedCategoryLocalDataSource,
    private val categoryResponseToCategoryLocalMapper: CategoryResponseToCategoryLocalMapper,
    private val categoryLocalToMedCategoryMapper: CategoryLocalToMedCategoryMapper,
) : CategoryRepository {
    override suspend fun getMedCategories(): Flow<List<MedCategory>> {
        val categoriesResponse = medCategoryRemoteDataSource.getCategoryResponse()
        medCategoryLocalDataSource.insertCategoriesList(
            categoryResponseToCategoryLocalMapper(categoriesResponse)
        )
        return medCategoryLocalDataSource.getCategoriesList()
            .map { list ->
                list.map {
                    categoryLocalToMedCategoryMapper(it)
                }
            }
    }

}
