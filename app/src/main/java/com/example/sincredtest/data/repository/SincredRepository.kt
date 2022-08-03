package com.example.sincredtest.data.repository

import com.example.sincredtest.data.response.EventsResponse

interface SincredRepository {

    suspend fun getEvents(): EventsResponse
}