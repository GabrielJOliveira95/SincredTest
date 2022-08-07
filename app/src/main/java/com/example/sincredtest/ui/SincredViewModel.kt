package com.example.sincredtest.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sincredtest.data.repository.SincredRepository
import com.example.sincredtest.ui.sealedClass.SincredSealedClassResponse
import kotlinx.coroutines.launch

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
                mSealedClassResponse.value = SincredSealedClassResponse.OnFailure(e)
            }
        }
    }
}