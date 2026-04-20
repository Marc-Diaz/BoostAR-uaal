package com.example.boostar_uaal.ui.screen.challengeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boostar_uaal.R
import com.example.boostar_uaal.core.entities.ChallengeData
import com.example.boostar_uaal.core.entities.ChallengeQuestion
import com.example.boostar_uaal.core.entities.ChallengeStep
import com.example.boostar_uaal.core.entities.QuestionAnswer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChallengeScreenViewModel : ViewModel() {

    // ─── State ───────────────────────────────────────────────────────────────

    private val _challenge = MutableStateFlow<ChallengeData?>(null)

    private val _currentStep = MutableStateFlow<ChallengeStep?>(null)
    val currentStep: StateFlow<ChallengeStep?> = _currentStep.asStateFlow()

    private val _currentQuestion = MutableStateFlow<ChallengeQuestion?>(null)
    val currentQuestion: StateFlow<ChallengeQuestion?> = _currentQuestion.asStateFlow()

    private val _stepPosition = MutableStateFlow(ChallengeStepPositoin.START)
    val stepPosition: StateFlow<ChallengeStepPositoin> = _stepPosition.asStateFlow()

    private val _maxSteps = MutableStateFlow(0)
    val maxSteps: StateFlow<Int> = _maxSteps.asStateFlow()

    private val _currentStepIndex = MutableStateFlow(-1)
    val currentStepIndex: StateFlow<Int> = _currentStepIndex.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(-1)

    private val _selectedAnswerIndex = MutableStateFlow<Int?>(null)
    val selectedAnswerIndex: StateFlow<Int?> = _selectedAnswerIndex.asStateFlow()

    private val _totalCorrectAnswers = MutableStateFlow(0)
    val totalCorrectAnswers: StateFlow<Int> = _totalCorrectAnswers.asStateFlow()

    private val _isButtonVisible = MutableStateFlow(true)
    val isButtonVisible: StateFlow<Boolean> = _isButtonVisible.asStateFlow()
    private val challenge = _challenge.value
    private val questions = challenge?.questions.orEmpty()
    private val steps = challenge?.steps.orEmpty()

    fun loadChallenge(id: Int) {
        val data = buildChallengeData()
        _challenge.value = data
        _currentStepIndex.value = -1
        _currentQuestionIndex.value = -1
        _maxSteps.value = data.steps.size
    }

    fun loadNextStep() {
        if (challenge == null) return
        advanceStepIndex()
        _currentStep.value = steps[_currentStepIndex.value]
        scheduleButtonVisibility(_currentStep.value!!.sleepTimeInMilliseconds)
    }

    fun loadNextQuestion() {
        if (challenge == null) return
        _isButtonVisible.value = false
        advanceQuestionIndex()
        _currentQuestion.value = questions[_currentQuestionIndex.value]
        _selectedAnswerIndex.value = null
    }

    fun selectAnswer(index: Int) {
        _selectedAnswerIndex.value = index
        _isButtonVisible.value = true
    }

    fun submitAnswer() {
        if (isSelectedAnswerCorrect()) {
            _totalCorrectAnswers.value++
        }
    }

    fun scheduleCallback(delayMs: Long, callback: () -> Unit){
        viewModelScope.launch {
            delay(delayMs)
            callback()
        }
    }

    // ─── Private helpers ─────────────────────────────────────────────────────

    private fun advanceStepIndex() {
        _currentStepIndex.value++
        _stepPosition.value = resolvePosition(_currentStepIndex.value, steps.size)
    }

    private fun advanceQuestionIndex() {
        _currentQuestionIndex.value++
        _stepPosition.value = resolvePosition(_currentQuestionIndex.value, questions.size)
    }

    private fun resolvePosition(index: Int, total: Int): ChallengeStepPositoin = when (index) {
        0 -> ChallengeStepPositoin.START
        total - 1 -> ChallengeStepPositoin.END
        else -> ChallengeStepPositoin.MIDDLE
    }

    private fun isSelectedAnswerCorrect(): Boolean {
        val question = _currentQuestion.value ?: return false
        val answerIndex = _selectedAnswerIndex.value ?: return false
        return question.answers[answerIndex].isCorrect
    }

    private fun scheduleButtonVisibility(delayMs: Long) {
        _isButtonVisible.value = false
        viewModelScope.launch {
            delay(delayMs)
            _isButtonVisible.value = true
        }
    }


    private fun buildChallengeData() = ChallengeData(
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
                    QuestionAnswer(answerImage = R.drawable.complementary_line, isCorrect = false),
                    QuestionAnswer(answerImage = R.drawable.neutral_line, isCorrect = false),
                    QuestionAnswer(answerImage = R.drawable.monocromatic_line, isCorrect = true),
                )
            ),
            ChallengeQuestion(
                id = 2,
                question = "¿Cuál de estas opciones es una paleta de colores complementaria?",
                answers = listOf(
                    QuestionAnswer(answerImage = R.drawable.complementary_line, isCorrect = true),
                    QuestionAnswer(answerImage = R.drawable.neutral_line, isCorrect = false),
                    QuestionAnswer(answerImage = R.drawable.monocromatic_line, isCorrect = false),
                )
            ),
            ChallengeQuestion(
                id = 3,
                question = "¿Cuál de estas opciones es una paleta de colores neutra?",
                answers = listOf(
                    QuestionAnswer(answerImage = R.drawable.complementary_line, isCorrect = false),
                    QuestionAnswer(answerImage = R.drawable.neutral_line, isCorrect = true),
                    QuestionAnswer(answerImage = R.drawable.monocromatic_line, isCorrect = false),
                )
            )
        )
    )
}