package com.example.sincredtest.ui.sealedClass

import com.example.sincredtest.data.response.EventsResponse

sealed class SincredSealedClassResponse {

    data class OnSuccess(val response: EventsResponse): SincredSealedClassResponse()
    data class OnFailure(val exception: String): SincredSealedClassResponse()
    object CheckSuccessIn: SincredSealedClassResponse()
    object NoInternet: SincredSealedClassResponse()
}
