package com.mirkwoodsoftware.presentation.di

import com.mirkwoodsoftware.presentation.MedicineViewModel
import com.mirkwoodsoftware.presentation.NetworkStatusViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    // ViewModels
    viewModel { NetworkStatusViewModel(get()) }
    viewModel { MedicineViewModel(get()) }
}