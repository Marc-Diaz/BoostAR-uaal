package com.example.boostar_uaal.ui.screen.feedScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FeedScreenViewModelFactory(private val sortOrder: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedScreenViewModel::class.java)) {
            return FeedScreenViewModel(sortOrder) as T       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}