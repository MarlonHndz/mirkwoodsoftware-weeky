package com.mirkwoodsoftware.data.mappers.category

import com.mirkwoodsoftware.data.localDataSource.models.CategoryLocal
import com.mirkwoodsoftware.domain.models.CategoryType
import com.mirkwoodsoftware.domain.models.MedCategory

class CategoryLocalToMedCategoryMapper {
    operator fun invoke(categoryLocal: CategoryLocal): MedCategory {
        return MedCategory(
            name = categoryLocal.name,
            description = categoryLocal.description,
            examples = categoryLocal.examples,
            type = getCategoryTypeFromString(categoryLocal.categoryType)
        )
    }
}

fun getCategoryTypeFromString(categoryString: String): CategoryType {
    return CategoryType.values().find { it.description == categoryString } ?: CategoryType.MEDICATION
}
