package com.example.sincredtest.data.datasource

import com.example.sincredtest.data.response.EventsResponse

interface SincredDataSource {

    suspend fun getEvents(): EventsResponse
}