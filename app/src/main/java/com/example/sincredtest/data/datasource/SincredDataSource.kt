package com.example.sincredtest.data.datasource

import com.example.sincredtest.data.request.SincredCheckInRequest
import com.example.sincredtest.data.response.EventsResponse

interface SincredDataSource {

    suspend fun getEvents(): EventsResponse

    suspend fun checkIn(request: SincredCheckInRequest): Any
}