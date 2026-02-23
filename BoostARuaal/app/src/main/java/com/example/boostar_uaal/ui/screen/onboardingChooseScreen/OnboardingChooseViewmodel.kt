package com.example.boostar_uaal.ui.screen.onboardingChooseScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.data.repository.MockProductRepositoryImpl
import com.example.boostar_uaal.ui.screen.onboardingChooseScreen.components.OnboardingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingChooseViewmodel : ViewModel() {
    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    private val repository = MockProductRepositoryImpl()


    init {
        loadOnboardingData()
    }

    private fun loadOnboardingData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

           val steps = repository.getOnboardingSteps()
            _uiState.update {
                it.copy(
                    isLoading = false,
                    steps = steps,
                    currentStepIndex = 0
                )
            }
        }
    }
    fun toggleOption(productId: Int) {
        val currentState = _uiState.value
        if (currentState.steps.isEmpty()) return

        val currentStepId = currentState.steps[currentState.currentStepIndex].id

        val currentSelected = currentState.selectedIds[currentStepId] ?: emptyList()

        val newSelected = if (currentSelected.contains(productId)) {
            currentSelected - productId // Si ya estaba, lo quitamos
        } else {
            if (currentSelected.size < 2) currentSelected + productId else currentSelected // Si hay menos de 2, lo añadimos
        }

        // Actualizamos el mapa
        val newMap = currentState.selectedIds.toMutableMap()
        newMap[currentStepId] = newSelected

        _uiState.update { it.copy(selectedIds = newMap) }
    }

    fun nextStep(navigateTo: () -> Unit) {
        val currentState = _uiState.value
        if (currentState.currentStepIndex < currentState.steps.size - 1) {
            _uiState.update { it.copy(currentStepIndex = it.currentStepIndex + 1) }
        } else {
            println("Selecciones finales: ${currentState.selectedIds}")
            navigateTo()
        }
    }
}