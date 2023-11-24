package com.mirkwoodsoftware.presentation.di

import com.mirkwoodsoftware.presentation.ui.formAddMedicine.FormAddViewModel
import com.mirkwoodsoftware.presentation.ui.home.MedicineViewModel
import com.mirkwoodsoftware.presentation.ui.networkHandler.NetworkStatusViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    // ViewModels
    viewModel { NetworkStatusViewModel(get()) }
    viewModel { MedicineViewModel(get()) }
    viewModel { FormAddViewModel(get()) }
}