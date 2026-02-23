package com.example.boostar_uaal.ui.screen.onboardingTextScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OnboardingTextViewmodel : ViewModel() {
    private val _uiState = MutableStateFlow<OnboardingState>(OnboardingState.Step1)
    val uiState: StateFlow<OnboardingState> = _uiState

    // Evento de navegación (Channel es mejor para eventos de una sola vez)
    private val _navigationEvent = Channel<Boolean>()
    val navigationEvent = _navigationEvent.receiveAsFlow()

    init {
        loadDataAndAnimate()
    }
    private fun loadDataAndAnimate() {
        viewModelScope.launch {
            // 1. Empezamos en STEP 1
            _uiState.value = OnboardingState.Step1

            // 2. PARALELISMO:
            // Lanzamos la carga de datos de la BBDD y esperamos un tiempo mínimo de animación
            // para que al usuario le dé tiempo a leer "Now, tell me your style"

            val loadingJob = launch {
                // Aqui mi GET que tiene que hacer el Marcoooooo
                // repository.getProducts()
                delay(3000) // Simulamos que la BBDD tarda 3 segundos
            }

            loadingJob.join()

            //Cuando los datos están listos, pasamos al siguiente paso
            _uiState.value = OnboardingState.Step2

            // 4. Dejamos ver el "Let's go!" un segundo
            delay(1500)

            // 5. Mandamos la señal de navegar y que se pire a los onboardings 3y 4
            _navigationEvent.send(true)
        }
    }
}
