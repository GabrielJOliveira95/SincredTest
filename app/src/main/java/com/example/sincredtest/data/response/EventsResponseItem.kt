package com.example.sincredtest.data.response

import java.io.Serializable

data class EventsResponseItem(
    val date: Long,
    val description: String,
    val id: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val people: List<Any>,
    val price: Double,
    val title: String
): Serializable