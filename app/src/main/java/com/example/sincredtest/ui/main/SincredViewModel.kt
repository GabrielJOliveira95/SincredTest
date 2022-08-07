package com.example.sincredtest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sincredtest.data.repository.SincredRepository
import com.example.sincredtest.data.request.SincredCheckInRequest
import com.example.sincredtest.ui.sealedClass.SincredSealedClassResponse
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class SincredViewModel(
    private val sincredRepository: SincredRepository
) : ViewModel() {

    private val mSealedClassResponse = MutableLiveData<SincredSealedClassResponse>()
    var sealedClassResponse = mSealedClassResponse

    fun loadEvents() {
        viewModelScope.launch {
            try {
                val response = sincredRepository.getEvents()
                mSealedClassResponse.value = SincredSealedClassResponse.OnSuccess(response)
            } catch (e: Exception) {
                setError(e)
            }
        }
    }

    fun checkIn(request: SincredCheckInRequest) {
        viewModelScope.launch {
            try {
              sincredRepository.checkInt(request)
                mSealedClassResponse.value = SincredSealedClassResponse.CheckSuccessIn
            } catch (e: Exception) {
                setError(e)
            }
        }
    }

    private fun setError(e: Exception) {
         when(e) {
             is UnknownHostException -> {
                 mSealedClassResponse.value = SincredSealedClassResponse.NoInternet
             }
             else -> {
                 mSealedClassResponse.value = SincredSealedClassResponse.OnFailure(e.message.orEmpty())
             }
         }
    }
}