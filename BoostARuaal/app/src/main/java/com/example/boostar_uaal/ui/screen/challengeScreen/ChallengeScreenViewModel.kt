package com.example.boostar_uaal.ui.screen.challengeScreen



import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.R

import com.example.boostar_uaal.core.entities.ChallengeData
import com.example.boostar_uaal.core.entities.ChallengeQuestion
import com.example.boostar_uaal.core.entities.ChallengeStep
import com.example.boostar_uaal.core.entities.QuestionAnswer
import com.snap.camerakit.support.media.recording.internal.fa
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ChallengeScreenViewModel: ViewModel() {
    private var _challenge: MutableStateFlow<ChallengeData?> = MutableStateFlow(null)
    private var _currentChallengeStep: MutableStateFlow<ChallengeStep?> = MutableStateFlow(null)
    val currentChallengeStep: StateFlow<ChallengeStep?> = _currentChallengeStep.asStateFlow()

    private var _challengeStepState: MutableStateFlow<ChallengeStepPositoin>
    = MutableStateFlow(ChallengeStepPositoin.START)
    val challengeStepState: StateFlow<ChallengeStepPositoin> = _challengeStepState.asStateFlow()

    private var _maxSteps: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
    val maxSteps: StateFlow<Int> = _maxSteps.asStateFlow()
    private var _currentStepId: MutableStateFlow<Int> = MutableStateFlow<Int>(-1)
    val currentStepId: StateFlow<Int> = _currentStepId.asStateFlow()

    private var _currentQuestion: MutableStateFlow<ChallengeQuestion?> = MutableStateFlow(null)
    val currentQuestion: StateFlow<ChallengeQuestion?> = _currentQuestion.asStateFlow()

    private var _currentQuestionId: MutableStateFlow<Int> = MutableStateFlow<Int>(-1)


    private var _selectedAnswerId: MutableStateFlow<Int?> = MutableStateFlow<Int?>(null)
    val selectedAnswerId: StateFlow<Int?> = _selectedAnswerId.asStateFlow()

    private var _totalCorrectAnswers: MutableStateFlow<Int> = MutableStateFlow<Int>(0)
    val totalCorrectAnswers: StateFlow<Int> =  _totalCorrectAnswers.asStateFlow()

    private var _isButtonVisible: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isButtonVisible: StateFlow<Boolean> = _isButtonVisible.asStateFlow()
    fun loadChallenge(id: Int){

        val challenge = ChallengeData(
            id = 1,
            steps = listOf(
                ChallengeStep(
                    id = 1,
                    multimedia = R.drawable.color_wheel,
                    title = "<b>TU <span style=\"color:#007AFF\">PALETA.</span><br>TU <span style=\"color:#007AFF\">IDENTIDAD.</span></b>",
                    text = "Aprende a leer el color como un estilista y crea outfits que te definen.",
                    sleepTimeInMilliseconds = 0
                ),
                ChallengeStep(
                    id = 2,
                    multimedia = R.drawable.color_wheel,
                    title = "<b>¿Qué es la <br><span style=\"color:#007AFF\">colorimetría</span>?</b>",
                    text = "Entiende el lenguaje del color y cómo potencia tu estilo.",
                    sleepTimeInMilliseconds = 1500
                ),
                ChallengeStep(
                    id = 3,
                    multimedia = R.drawable.challenge_phone_1,
                    text = "La <b><span style=\"color:#007AFF\">colorimetría</span></b> sirve para <b><span style=\"color:#007AFF\">combinar prendas</span></b> entre sí",
                    sleepTimeInMilliseconds = 500
                ),
                ChallengeStep(
                    id = 4,
                    multimedia = R.drawable.challenge_phone_2,
                    text = "Hay combinaciones <b><span style=\"color:#007AFF\">seguras</span></b> y combinaciones <b><span style=\"color:#007AFF\">arriesgadas</span></b>.",
                    sleepTimeInMilliseconds = 500
                ),
                ChallengeStep(
                    id = 5,
                    multimedia = R.drawable.monocromatic,
                    title = "<b><span style=\"color:#007AFF\">MONOCROMÁTICOS</span></b>",
                    text = "Mismo <b><span style=\"color:#007AFF\">color</span></b>, diferentes <b><span style=\"color:#007AFF\">tonos</span></b>",
                    sleepTimeInMilliseconds = 500
                ),
                ChallengeStep(
                    id = 6,
                    multimedia = R.drawable.complementary,
                    title = "<b><span style=\"color:#007AFF\">COMPLEMENTARIOS</span></b>",
                    text = "Colores <b><span style=\"color:#007AFF\">opuestos</span></b> que juntos crean <b><span style=\"color:#007AFF\">contraste</span></b> y hacen que el <b><span style=\"color:#007AFF\">outfit destaque</span></b>",
                    sleepTimeInMilliseconds = 500
                ),
                ChallengeStep(
                    id = 7,
                    multimedia = R.drawable.neutral,
                    title = "<b><span style=\"color:#007AFF\">NEUTROS</span></b>",
                    text = "Colores que se utilizan para <b><span style=\"color:#007AFF\">combinar</span></b> y que <b><span style=\"color:#007AFF\">no destacan</span></b> en general",
                    sleepTimeInMilliseconds = 500
                )
            ),
            questions = listOf(
                ChallengeQuestion(
                    id = 1,
                    question = "¿Cuál de estas opciones es una paleta de colores monocromática?",
                    answers = listOf(
                        QuestionAnswer(
                            answerImage = R.drawable.complementary_line,
                            isCorrect = false
                        ),
                        QuestionAnswer(
                            answerImage = R.drawable.neutral_line,
                            isCorrect = false
                        ),
                        QuestionAnswer(
                            answerImage = R.drawable.monocromatic_line,
                            isCorrect = true
                        ),
                    )
                ),
                ChallengeQuestion(
                    id = 2,
                    question = "¿Cuál de estas opciones es una paleta de colores complementaria?",
                    answers = listOf(
                        QuestionAnswer(
                            answerImage = R.drawable.complementary_line,
                            isCorrect = true
                        ),
                        QuestionAnswer(
                            answerImage = R.drawable.neutral_line,
                            isCorrect = false
                        ),
                        QuestionAnswer(
                            answerImage = R.drawable.monocromatic_line,
                            isCorrect = false
                        ),
                    )
                ),
                ChallengeQuestion(
                    id = 1,
                    question = "¿Cuál de estas opciones es una paleta de colores neutra?",
                    answers = listOf(
                        QuestionAnswer(
                            answerImage = R.drawable.complementary_line,
                            isCorrect = true
                        ),
                        QuestionAnswer(
                            answerImage = R.drawable.neutral_line,
                            isCorrect = false
                        ),
                        QuestionAnswer(
                            answerImage = R.drawable.monocromatic_line,
                            isCorrect = false
                        ),
                    )
                )
            )
        )
        _challenge.value = challenge
        _currentStepId.value = -1
        _currentQuestionId.value = -1
        _maxSteps.value = challenge.steps.size
    }
    fun loadNextStep(){
        if (_challenge.value == null) return
        nextStepOrEnd()
        _currentChallengeStep.value = _challenge.value!!.steps[_currentStepId.value]
        startButtonVisibilityTimer()
    }
    private fun nextStepOrEnd(){
        if (_challenge.value == null) return
        val lastStep = _challenge.value!!.steps.size - 1
        _currentStepId.value++
        when (_currentStepId.value) {
            0 -> _challengeStepState.value = ChallengeStepPositoin.START
            lastStep -> _challengeStepState.value = ChallengeStepPositoin.END
            else -> _challengeStepState.value = ChallengeStepPositoin.MIDDLE
        }
    }

    private fun nextQuestionOrEnd(){
        if (_challenge.value == null) return
        val lastStep = _challenge.value!!.questions.size - 1
        _currentQuestionId.value++
        when (_currentQuestionId.value) {
            0 -> _challengeStepState.value = ChallengeStepPositoin.START
            lastStep -> _challengeStepState.value = ChallengeStepPositoin.END
            else -> _challengeStepState.value = ChallengeStepPositoin.MIDDLE
        }
    }

    fun loadNextQuestion(){
        if (_challenge.value == null) return
        _isButtonVisible.value = false
        nextQuestionOrEnd()
        _currentQuestion.value = _challenge.value!!.questions[_currentQuestionId.value]
        _selectedAnswerId.value = null

    }
    fun selectAnswer(id: Int){
        _selectedAnswerId.value = id
        _isButtonVisible.value = true
    }

    fun submitAnswer(){
        addPointOnCorrectAnswer()
    }

    private fun addPointOnCorrectAnswer(){
        if (isAnswerSelectedCorrect()) _totalCorrectAnswers.value++
    }
    private fun isAnswerSelectedCorrect(): Boolean{
         return _currentQuestion.value!!.answers[_selectedAnswerId.value!!].isCorrect
    }

    private fun startButtonVisibilityTimer(){
        if (_currentChallengeStep.value == null) return
        _isButtonVisible.value = false
        Log.d("Visibilidad 1", "${_isButtonVisible.value}")

        var counter = _currentChallengeStep.value!!.sleepTimeInMilliseconds
        val timeStep = 100
        viewModelScope.launch {
            while (counter > 0){
                counter -= timeStep
                delay(timeStep.toLong())
            }
            _isButtonVisible.value = true
        }
    }
}
