package com.example.boostar_uaal.ui.screen.singInScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.repository.AuthRepository
import com.example.boostar_uaal.data.datasource.SharedPreferencesHelper
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SingUpScreenViewModel(
    private val auth: AuthRepository,
    private val sharedPreferences: SharedPreferencesHelper
): ViewModel() {
    private val _isCompanyAccount = MutableStateFlow(false)
    val isCompanyAccount: StateFlow<Boolean> = _isCompanyAccount.asStateFlow()

    fun toggleCompanyAccount() {
        _isCompanyAccount.value = !_isCompanyAccount.value
    }

    // 2. Lógica de inicio de sesión con Google
    fun handleGoogleSignInResult(result: NativeSignInResult, navigateTo: (Routes) -> Unit) {
        when(result) {
            is NativeSignInResult.Success -> {
                // Como ya tienes el SharedPreferences y AuthRepository inyectados,
                // más adelante podrás guardar el token del usuario aquí.
                Log.d("AuthViewModel", "¡Login exitoso!")
                navigateTo(Routes.Authenticated)
            }
            is NativeSignInResult.Error -> {
                Log.e("AuthViewModel", "Error en el login")
                navigateTo(Routes.AuthScreen)
            }
            else -> {
                Log.d("AuthViewModel", "Resultado no manejado: $result")
            }
        }
    }
}