package com.example.sincredtest.data.service

import com.example.sincredtest.data.response.EventsResponse
import com.example.sincredtest.data.service.SincredService.URL.EVENT_URL
import retrofit2.http.GET

interface SincredService {

    @GET(EVENT_URL)
    suspend fun getEvents(): EventsResponse

    private object URL {
        const val EVENT_URL = "api/events"
    }
}