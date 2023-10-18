package com.mirkwoodsoftware.data.mappers.medicine

import com.mirkwoodsoftware.data.localDataSource.models.MedicineLocal
import com.mirkwoodsoftware.domain.models.Medicine

class MedicineLocalToMedicineMapper {

    operator fun invoke(medicineLocal: MedicineLocal): Medicine {
        return Medicine(
            id = medicineLocal.id,
            url = medicineLocal.url
        )
    }
}
