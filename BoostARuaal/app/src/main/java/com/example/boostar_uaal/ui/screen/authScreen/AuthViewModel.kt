package com.example.boostar_uaal.ui.screen.authScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.core.navigation.Routes
import com.example.boostar_uaal.core.utils.AuthState
import io.github.jan.supabase.auth.user.UserSession
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.boostar_uaal.BoostArApplication.Companion.authRepository
import com.example.boostar_uaal.BoostArApplication.Companion.userRepository

class AuthViewModel: ViewModel() {
    private val _session = MutableStateFlow<UserSession?>(null)
    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState = _authState.asStateFlow()
    private val _isCompanyAccount = MutableStateFlow(false)
    val isCompanyAccount: StateFlow<Boolean> = _isCompanyAccount.asStateFlow()

    private var _errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        checkExistingSession()
    }

    /**
     * Verifica la existencia y validez de una sesión de usuario previa.
     *
     * Comprueba de forma asíncrona si hay un token guardado localmente y si no ha expirado.
     * Si la sesión es completamente válida, la refresca automáticamente y actualiza el estado
     * a `Authenticated`. En caso contrario, rechaza el acceso y establece el estado
     * en `Unauthenticated`.
     */
    fun checkExistingSession(){
        viewModelScope.launch {
            _session.value = authRepository.loadSession()
            if(_session.value == null) _authState.value = AuthState.Unauthenticated
            else if (!authRepository.isAccessTokenValid()) _authState.value = AuthState.Unauthenticated
            else{
                authRepository.refreshSession()
                _authState.value = AuthState.Authenticated
            }
        }

    }

    fun toggleCompanyAccount() {
        _isCompanyAccount.value = !_isCompanyAccount.value
    }

    /**
     * Procesa el resultado de un intento de inicio de sesión nativo con Google.
     *
     * Si la autenticación es exitosa, verifica obligatoriamente que el usuario tenga
     * un rol asignado en la base de datos antes de concederle acceso a la `HomeScreen`.
     * Si carece de rol, cancela la sesión y muestra un error. En caso de fallo general
     * de inicio de sesión, redirige de vuelta a la `AuthScreen`.
     *
     * @param result El estado resultante del flujo de autenticación de Google.
     * @param navigateTo Callback de enrutamiento para redirigir al usuario según el resultado.
     */
    fun handleGoogleLogIn(
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
                    _authState.value = AuthState.Authenticated
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
        }
    }
    /**
     * Procesa el resultado de un intento de registro (Sign Up) nativo con Google.
     *
     * Si el flujo es exitoso, verifica que el usuario sea realmente nuevo (sin rol previo).
     * Tras confirmarlo, le asigna el rol seleccionado, guarda la sesión y lo redirige al
     * flujo de configuración inicial (Onboarding). Si el usuario ya existía y tenía un rol,
     * cancela la sesión para evitar sobrescribir sus datos.
     *
     * @param result El estado resultante del flujo de registro de Google.
     * @param navigateTo Callback de enrutamiento para redirigir al usuario según el resultado.
     */
    fun handleGoogleSignInResult(result: NativeSignInResult, navigateTo: (Routes) -> Unit) {
        when(result) {
            is NativeSignInResult.Success -> {
                viewModelScope.launch {
                    if (userRepository.hasUserRole()){
                        authRepository.clearSession()
                    }
                    else {
                        userRepository.setUserRole(isCompanyAccount.value)
                        authRepository.saveSession()
                        _authState.value = AuthState.Authenticated
                        navigateTo(Routes.OnboardingTextScreen)
                    }
                }

            }
            is NativeSignInResult.Error -> {
                navigateTo(Routes.AuthScreen)
            }
            else -> { }
        }
    }

    /**
     * Cierra la sesión del usuario actual de forma asíncrona.
     *
     * Invalida la sesión activa a través del repositorio, actualiza el estado reactivo
     * a `Unauthenticated` y ejecuta una acción de éxito personalizada.
     *
     * @param onSuccess Callback que se ejecuta inmediatamente después de cerrar la sesión con éxito.
     */

    fun signOut(onSuccess: () -> Unit){
        viewModelScope.launch{
            authRepository.signOut()
            onSuccess()
            _authState.value = AuthState.Unauthenticated

        }
    }
    fun clearError(){
        _errorMessage.value = null
    }
}