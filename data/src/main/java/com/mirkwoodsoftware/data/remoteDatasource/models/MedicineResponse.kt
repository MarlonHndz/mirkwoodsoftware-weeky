package com.mirkwoodsoftware.data.remoteDatasource.models

data class MedicineResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val result: List<MedicineItemResponse>
) {
    data class MedicineItemResponse(
        val name: String,
        val url: String,
    )
}
