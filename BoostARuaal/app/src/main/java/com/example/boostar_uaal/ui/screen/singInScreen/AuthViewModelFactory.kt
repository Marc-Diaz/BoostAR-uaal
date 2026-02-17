package com.example.boostar_uaal.ui.screen.singInScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.boostar_uaal.core.repository.AuthRepository
import com.example.boostar_uaal.data.datasource.SharedPreferencesHelper

class AuthViewModelFactory(private val auth: AuthRepository, private val sharedPreferencesHelper: SharedPreferencesHelper): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(auth, sharedPreferencesHelper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}