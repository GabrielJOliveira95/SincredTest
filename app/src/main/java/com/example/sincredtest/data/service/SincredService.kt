package com.example.sincredtest.data.service

import com.example.sincredtest.data.request.SincredCheckInRequest
import com.example.sincredtest.data.response.EventsResponse
import com.example.sincredtest.data.service.SincredService.URL.CHECK_IN_URL
import com.example.sincredtest.data.service.SincredService.URL.EVENT_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SincredService {

    @GET(EVENT_URL)
    suspend fun getEvents(): EventsResponse

    @POST(CHECK_IN_URL)
    suspend fun checkIn(
        @Body request: SincredCheckInRequest
    ): Response<Any>

    private object URL {
        const val EVENT_URL = "api/events"
        const val CHECK_IN_URL = "api/checkin"
    }
}