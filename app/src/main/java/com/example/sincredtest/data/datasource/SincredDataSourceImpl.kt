package com.example.sincredtest.data.datasource

import com.example.sincredtest.data.response.EventsResponse
import com.example.sincredtest.data.service.SincredService
import kotlin.coroutines.suspendCoroutine

class SincredDataSourceImpl(private val api: SincredService) : SincredDataSource {

    override suspend fun getEvents(): EventsResponse = api.getEvents()
}
