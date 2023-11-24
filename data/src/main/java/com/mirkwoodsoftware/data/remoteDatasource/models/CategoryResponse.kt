package com.mirkwoodsoftware.data.remoteDatasource.models

data class CategoryResponse(
    val status: Int,
    val categories: List<Category>
) {
    data class Category(
        val name: String,
        val description: String,
        val examples: String,
        val categoryType: String
    )
}
