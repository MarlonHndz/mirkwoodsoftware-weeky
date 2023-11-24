package com.mirkwoodsoftware.data.mappers.category

import com.mirkwoodsoftware.data.localDataSource.models.CategoryLocal
import com.mirkwoodsoftware.data.remoteDatasource.models.CategoryResponse

class CategoryResponseToCategoryLocalMapper {
    operator fun invoke(categoryResponse: CategoryResponse): List<CategoryLocal> {
        with(categoryResponse) {
            return categories.map { categoryResponse ->
                CategoryLocal(
                    name = categoryResponse.name,
                    description = categoryResponse.description,
                    examples = categoryResponse.examples,
                    categoryType = categoryResponse.categoryType
                )
            }
        }
    }
}
