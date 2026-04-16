package com.example.boostar_uaal.ui.screen.feedScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boostar_uaal.data.models.ProductFilters

class FeedScreenViewModelFactory(private val sortOrder: String, private val productFilters: ProductFilters) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedScreenViewModel::class.java)) {
            return FeedScreenViewModel(sortOrder, productFilters) as T       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}