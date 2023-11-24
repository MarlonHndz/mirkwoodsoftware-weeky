package com.mirkwoodsoftware.domain.models

data class MedCategory(
    val name: String,
    val description: String,
    val examples: String,
    val type: CategoryType,
)

enum class CategoryType(val description: String) {
    MEDICATION("Medicamento"),
    UTENSIL("Utensilio"),
}
