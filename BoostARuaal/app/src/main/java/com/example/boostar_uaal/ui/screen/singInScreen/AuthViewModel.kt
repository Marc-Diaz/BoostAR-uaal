package com.example.boostar_uaal.ui.screen.singInScreen

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.core.repository.AuthRepository
import com.example.boostar_uaal.data.datasource.SharedPreferencesHelper
import io.github.jan.supabase.auth.user.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val auth: AuthRepository,
    private val sharedPreferences: SharedPreferencesHelper
): ViewModel() {
    fun onGoogleSignIn() {

    }

}