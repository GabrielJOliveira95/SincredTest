package com.example.sincredtest.data.di

import com.example.sincredtest.data.datasource.SincredDataSource
import com.example.sincredtest.data.datasource.SincredDataSourceImpl
import com.example.sincredtest.data.repository.SincredRepository
import com.example.sincredtest.data.repository.SincredRepositoryImpl
import com.example.sincredtest.data.retrofit.Retrofit.createRetrofit
import com.example.sincredtest.data.service.SincredService
import com.example.sincredtest.ui.SincredViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SincredModule {

    val instance = module {
        viewModel { SincredViewModel(get()) }
        single<SincredService> { createRetrofit() }
        single<SincredRepository> { SincredRepositoryImpl(get()) }
        single<SincredDataSource> { SincredDataSourceImpl(get()) }
    }
}