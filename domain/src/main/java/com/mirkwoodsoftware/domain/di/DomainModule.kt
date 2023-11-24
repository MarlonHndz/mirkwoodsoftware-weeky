package com.mirkwoodsoftware.domain.di

import com.mirkwoodsoftware.domain.useCases.FormAddMedicineUseCase
import com.mirkwoodsoftware.domain.useCases.MedicineUseCase
import com.mirkwoodsoftware.domain.useCases.NetworkStatusUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { NetworkStatusUseCase(get()) }
    factory { MedicineUseCase(get()) }
    factory { FormAddMedicineUseCase(get()) }
}
