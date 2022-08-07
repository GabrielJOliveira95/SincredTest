package com.example.sincredtest.data.datasource

import com.example.sincredtest.data.request.SincredCheckInRequest
import com.example.sincredtest.data.response.EventsResponse
import com.example.sincredtest.data.service.SincredService
import kotlin.coroutines.suspendCoroutine

class SincredDataSourceImpl(private val api: SincredService) : SincredDataSource {

    override suspend fun getEvents(): EventsResponse = api.getEvents()

    override suspend fun checkInt(request: SincredCheckInRequest): Any =
        api.checkIn(request)
}
