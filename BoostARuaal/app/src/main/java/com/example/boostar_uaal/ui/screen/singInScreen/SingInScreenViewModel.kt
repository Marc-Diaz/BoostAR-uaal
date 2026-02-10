package com.example.boostar_uaal.ui.screen.singInScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.data.repository.AuthRepositoryImpl
import kotlinx.coroutines.launch

class SingInScreenViewModel(private val auth: AuthRepositoryImpl): ViewModel() {

    fun onGoogleSignIn() {
        viewModelScope.launch {
            auth.signInWithGoogle()
        }
    }

}