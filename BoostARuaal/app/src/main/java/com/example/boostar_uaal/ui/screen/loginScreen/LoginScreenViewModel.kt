package com.example.boostar_uaal.ui.screen.loginScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.BoostArApplication
import com.example.boostar_uaal.core.navigation.Routes
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {

    val authRepository = BoostArApplication.authRepository
    fun handleGoogleSignInResult(
        result: NativeSignInResult,
        navigateTo: (Routes) -> Unit
    ) {
        when (result) {
            is NativeSignInResult.Success -> {
                Log.d("LogInViewModel", "¡Login con Google exitoso!")
                viewModelScope.launch {
                    authRepository.saveSession()
                }
                navigateTo(Routes.HomeScreen)
            }
            is NativeSignInResult.Error -> {
                Log.e("LogInViewModel", "Error al iniciar sesión con Google")
                navigateTo(Routes.AuthScreen)
            }
            is NativeSignInResult.ClosedByUser -> {
                Log.d("LogInViewModel", "El usuario cerró la ventana de Google")
            }
            is NativeSignInResult.NetworkError -> {
                Log.e("LogInViewModel", "Error de red al conectar con Google")
            }
            else -> {
                Log.d("LogInViewModel", "Resultado no manejado: $result")
            }
        }
    }

}