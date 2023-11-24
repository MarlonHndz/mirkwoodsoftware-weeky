package com.mirkwoodsoftware.domain.models

data class Medicine(
    val id: String,
    val name: String,
    val category: MedCategory?= null,
)
