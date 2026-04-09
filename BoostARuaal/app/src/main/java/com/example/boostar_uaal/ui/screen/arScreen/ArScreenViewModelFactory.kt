package com.example.boostar_uaal.ui.screen.arScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boostar_uaal.core.entities.ProductDetail
import com.example.boostar_uaal.ui.screen.feedScreen.FeedScreenViewModel
import com.example.core.entities.Product

class ArScreenViewModelFactory(private val product: ProductDetail) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedScreenViewModel::class.java)) {
            return ArScreenViewModel(product) as T       }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}