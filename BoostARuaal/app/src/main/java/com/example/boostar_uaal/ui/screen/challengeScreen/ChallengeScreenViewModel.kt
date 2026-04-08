package com.example.boostar_uaal.ui.screen.challengeScreen



import androidx.lifecycle.ViewModel
import com.example.boostar_uaal.R

import com.example.boostar_uaal.core.entities.ChallengeData
import com.example.boostar_uaal.core.entities.ChallengeStep
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ChallengeScreenViewModel: ViewModel() {
    private var _challenge: MutableStateFlow<ChallengeData?> = MutableStateFlow(null)
    private var _currentChallengeStep: MutableStateFlow<ChallengeStep?> = MutableStateFlow(null)
    val currentChallengeStep: StateFlow<ChallengeStep?> = _currentChallengeStep.asStateFlow()

    private var _challengeStepState: MutableStateFlow<ChallengeStepPositoin>
    = MutableStateFlow(ChallengeStepPositoin.START)
    val challengeStepState: StateFlow<ChallengeStepPositoin> = _challengeStepState.asStateFlow()
    private var currentStepId: Int = 0

    fun loadChallenge(id: Int){
        val challenge = ChallengeData(
            id = 1,
            steps = listOf(
                ChallengeStep(
                    id = 1,
                    multimedia = R.drawable.color_wheel,
                    title = "TU PALETA.\nTU IDENTIDAD.",
                    text = "Aprene a leer el color como un estilista y crea outfits que te definen.",
                    sleepTimeInSeconds = 0
                ),
                ChallengeStep(
                    id = 2,
                    multimedia = R.drawable.color_wheel,
                    title = "¿Qué es la \ncolorimetría?",
                    text = "Entiende el lenguaje del color y cómo potencia tu estilo.",
                    sleepTimeInSeconds = 1
                ),
                ChallengeStep(
                    id = 3,
                    multimedia = R.drawable.color_wheel,
                    text = "La colorimetría sirve para combinar prendas entre si.",
                    sleepTimeInSeconds = 1
                ),
                ChallengeStep(
                    id = 4,
                    multimedia = R.drawable.color_wheel,
                    text = "Hay combinaciones seguras y combinaciones arriesgadas.",
                    sleepTimeInSeconds = 1
                ),
                ChallengeStep(
                    id = 5,
                    multimedia = R.drawable.color_wheel,
                    title = "MONOCROMÁTICOS",
                    text = "La colorimetría sirve para combinar prendas entre si.",
                    sleepTimeInSeconds = 1
                ),
                ChallengeStep(
                    id = 6,
                    multimedia = R.drawable.color_wheel,
                    title = "COMPLEMENTARIOS",
                    text = "Hay combinaciones seguras y combinaciones arriesgadas.",
                    sleepTimeInSeconds = 1
                )
            ))
        _challenge.value = challenge
    }
    fun loadNextStep(){
        if (_challenge.value == null) return
        nextStepOrEnd()
        _currentChallengeStep.value = _challenge.value!!.steps[currentStepId]
    }
    private fun nextStepOrEnd(){
        if (_challenge.value == null) return
        val lastStep = _challenge.value!!.steps.size - 1
        if (_currentChallengeStep.value != null){
            currentStepId++
            if (currentStepId == lastStep) _challengeStepState.value = ChallengeStepPositoin.END
            else _challengeStepState.value = ChallengeStepPositoin.MIDDLE
        }
    }
}
