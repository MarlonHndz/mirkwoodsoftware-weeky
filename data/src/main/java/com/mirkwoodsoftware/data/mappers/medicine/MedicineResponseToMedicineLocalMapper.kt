package com.mirkwoodsoftware.data.mappers.medicine

import com.mirkwoodsoftware.data.localDataSource.models.MedicineLocal
import com.mirkwoodsoftware.data.remoteDatasource.models.MedicineResponse

class MedicineResponseToMedicineLocalMapper {
    operator fun invoke(medicineResponse: MedicineResponse): List<MedicineLocal> {
        return medicineResponse.result.map { medicineResponse ->
            MedicineLocal(
                id = medicineResponse.name,
                url = medicineResponse.url
            )
        }
    }
}
