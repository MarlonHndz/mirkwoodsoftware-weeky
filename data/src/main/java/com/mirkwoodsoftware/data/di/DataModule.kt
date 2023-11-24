package com.mirkwoodsoftware.data.di

import com.mirkwoodsoftware.data.localDataSource.datasources.MedCategoryLocalDataSource
import com.mirkwoodsoftware.data.localDataSource.datasources.MedicineLocalDataSource
import com.mirkwoodsoftware.data.localDataSource.room.AppDatabase
import com.mirkwoodsoftware.data.mappers.category.CategoryLocalToMedCategoryMapper
import com.mirkwoodsoftware.data.mappers.category.CategoryResponseToCategoryLocalMapper
import com.mirkwoodsoftware.data.mappers.medicine.MedicineLocalToMedicineMapper
import com.mirkwoodsoftware.data.mappers.medicine.MedicineResponseToMedicineLocalMapper
import com.mirkwoodsoftware.data.mappers.medicine.MedicineResponseToMedicineMapper
import com.mirkwoodsoftware.data.remoteDatasource.datasources.MedCategoryRemoteDataSource
import com.mirkwoodsoftware.data.remoteDatasource.datasources.MedicineRemoteDataSource
import com.mirkwoodsoftware.data.remoteDatasource.retrofit.RetrofitClient
import com.mirkwoodsoftware.data.remoteDatasource.services.MedicineService
import com.mirkwoodsoftware.data.repositories.CategoryRepositoryImpl
import com.mirkwoodsoftware.data.repositories.MedicineRepositoryImplImpl
import com.mirkwoodsoftware.data.repositories.base.BaseRepositoryImpl
import com.mirkwoodsoftware.domain.repositories.BaseRepository
import com.mirkwoodsoftware.domain.repositories.CategoryRepository
import com.mirkwoodsoftware.domain.repositories.MedicineRepository
import org.koin.dsl.module

val dataModule = module {

    // Retrofit
    single { RetrofitClient.create(MedicineService::class.java) }

    // Room
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().MedicineDao() }
    single { get<AppDatabase>().CategoryDao() }

    // Mappers
    factory { MedicineLocalToMedicineMapper() }
    factory { MedicineResponseToMedicineLocalMapper() }
    factory { MedicineResponseToMedicineMapper() }

    factory { CategoryLocalToMedCategoryMapper() }
    factory { CategoryResponseToCategoryLocalMapper() }

    // DataSources
    factory { MedicineLocalDataSource(get()) }
    factory { MedicineRemoteDataSource(get()) }
    factory { MedCategoryRemoteDataSource(get()) }
    factory { MedCategoryLocalDataSource(get()) }


    // Repositories
    factory<BaseRepository> { BaseRepositoryImpl() }
    factory<MedicineRepository> { MedicineRepositoryImplImpl(get(), get(), get(), get(), get()) }
    factory<CategoryRepository> { CategoryRepositoryImpl(get(), get(), get(), get()) }
}
