package com.example.sincredtest.data.repository

import com.example.sincredtest.data.datasource.SincredDataSource
import com.example.sincredtest.data.request.SincredCheckInRequest
import com.example.sincredtest.data.response.EventsResponse

class SincredRepositoryImpl(private val dataSource: SincredDataSource) : SincredRepository {

    override suspend fun getEvents(): EventsResponse = dataSource.getEvents()

    override suspend fun checkInt(request: SincredCheckInRequest): Any =
        dataSource.checkInt(request)
}