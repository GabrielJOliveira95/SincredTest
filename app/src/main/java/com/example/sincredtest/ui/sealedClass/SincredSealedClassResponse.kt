package com.example.sincredtest.ui.sealedClass

import com.example.sincredtest.data.response.EventsResponse
import java.lang.Exception

sealed class SincredSealedClassResponse {

    data class OnSuccess(val response: EventsResponse): SincredSealedClassResponse()
    data class OnFailure(val exception: Exception): SincredSealedClassResponse()
}
