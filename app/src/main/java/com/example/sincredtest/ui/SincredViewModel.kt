package com.example.sincredtest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sincredtest.data.repository.SincredRepository
import kotlinx.coroutines.launch

class SincredViewModel(private val sincredRepository: SincredRepository): ViewModel() {


    fun getEvents() {
        viewModelScope.launch {
            sincredRepository.getEvents()
        }
    }
}