package com.example.boostar_uaal.ui.screen.loginScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.core.navigation.Routes
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {

    val authRepository = BoostArApplication.authRepository
    val userRepository = BoostArApplication.userRepository

    private var _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()
    fun handleGoogleSignInResult(
        result: NativeSignInResult,
        navigateTo: (Routes) -> Unit
    ) {
        when (result) {
            is NativeSignInResult.Success -> {

                viewModelScope.launch {
                    if (!userRepository.hasUserRole()){
                        authRepository.clearSession()
                        _errorMessage.value = "No tienes un rol asignado."
                        return@launch
                    }
                    authRepository.saveSession()
                    navigateTo(Routes.HomeScreen)
                }

            }
            is NativeSignInResult.Error -> {
                navigateTo(Routes.AuthScreen)
            }
            is NativeSignInResult.ClosedByUser -> {

            }
            is NativeSignInResult.NetworkError -> {
            }
            else -> {
            }
        }
    }
    fun clearError(){
        _errorMessage.value = null
    }
}