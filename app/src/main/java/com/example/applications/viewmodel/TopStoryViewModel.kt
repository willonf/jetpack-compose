package com.example.applications.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applications.repository.TopStoryRepository
import com.example.applications.serializer.ApiResponseSerializer
import com.example.applications.serializer.TopStorySerializer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopStoryViewModel : ViewModel() {
    private val _departmentList = MutableStateFlow<ApiResponseSerializer<TopStorySerializer>>(ApiResponseSerializer())
    val departmentList = _departmentList.asStateFlow()

    private val repository = TopStoryRepository()

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            _departmentList.value = repository.getAll("arts")
        }
    }

}