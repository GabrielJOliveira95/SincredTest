package com.example.sincredtest.data.repository

import com.example.sincredtest.data.datasource.SincredDataSource
import com.example.sincredtest.data.response.EventsResponse

class SincredRepositoryImpl(private val dataSource: SincredDataSource): SincredRepository {

    override suspend fun getEvents(): EventsResponse = dataSource.getEvents()
}