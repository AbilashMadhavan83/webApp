package com.example.webapp.ui.retrofit.shared.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.webapp.data.repository.AppRepository


class SharedViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SharedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
