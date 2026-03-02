package com.example.boostar_uaal.ui.screen.singInScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.repository.AuthRepository
import com.example.boostar_uaal.data.datasource.SharedPreferencesHelper
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SingUpScreenViewModel(): ViewModel() {

    private val authRepository = BoostArApplication.authRepository
    private val _isCompanyAccount = MutableStateFlow(false)
    val isCompanyAccount: StateFlow<Boolean> = _isCompanyAccount.asStateFlow()

    fun toggleCompanyAccount() {
        _isCompanyAccount.value = !_isCompanyAccount.value
    }

    // 2. Lógica de inicio de sesión con Google
    fun handleGoogleSignInResult(result: NativeSignInResult, navigateTo: (Routes) -> Unit) {
        when(result) {
            is NativeSignInResult.Success -> {
                viewModelScope.launch {
                    authRepository.saveSession()
                }
                navigateTo(Routes.Authenticated)
            }
            is NativeSignInResult.Error -> {
                navigateTo(Routes.AuthScreen)
            }
            else -> { }
        }
    }
}