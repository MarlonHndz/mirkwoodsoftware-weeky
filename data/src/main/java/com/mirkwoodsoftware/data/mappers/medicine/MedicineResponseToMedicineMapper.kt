package com.mirkwoodsoftware.data.mappers.medicine

import com.mirkwoodsoftware.data.remoteDatasource.models.MedicineResponse
import com.mirkwoodsoftware.domain.models.Medicine

class MedicineResponseToMedicineMapper {
    operator fun invoke(medicineResponse: MedicineResponse): List<Medicine> {
        return medicineResponse.result.map { data ->
            Medicine(
                id = data.name,
                name = data.name,
            )
        }
    }
}