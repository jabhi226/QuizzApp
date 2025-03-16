package com.example.quizzapp.modules.quizModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.domain.repository.QuizRepository
import com.example.quizzapp.modules.quizModule.model.DomainMapper.getModel
import com.example.quizzapp.modules.quizModule.model.OptionBackground
import com.example.quizzapp.modules.quizModule.model.QuizModel
import com.example.quizzapp.modules.quizModule.model.QuizOptionsModel
import com.example.quizzapp.modules.quizModule.screens.ClickEventType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) : ViewModel() {

    val quizQuestionsListV2 = MutableStateFlow<MutableList<QuizModel>?>(null)
    val currentQuestionNumber = MutableStateFlow(0)

    private val quizQuestionsList = MutableStateFlow<List<QuizModel>>(listOf())
    var isCurrentAnswerSelected = MutableStateFlow<Boolean?>(null)
    val checkIfBackButtonIsClickable = MutableStateFlow<Boolean?>(null)

    val uiEvent = MutableStateFlow<Int?>(null)

//    val currentQuestionV2 = quizQuestionsListV2.combine(currentQuestionNumber) { list, number ->
//        list ?: return@combine null
//        if (list.size > number) {
//            return@combine list[number]
//        } else {
//            return@combine null
//        }
//    }

    val currentQuestion = quizQuestionsList.combine(currentQuestionNumber) { list, qNumber ->
        checkIfBackButtonIsClickable.emit(qNumber > 0)
        println("==>> QuizOptionList ${list.size}")
        if (list.size > qNumber) {
            isCurrentAnswerSelected.emit(list[qNumber].options.any { it.isSelected })
            list[qNumber]
        } else {
            // last quesiton
            uiEvent.emit((uiEvent.value ?: 1) + 1)
            null
        }
    }

    init {
        viewModelScope.launch {
            getQuizQuestions()
        }
    }

    fun getNextQuestion() {
        viewModelScope.launch {
            currentQuestionNumber.emit(currentQuestionNumber.value + 1)
        }
    }

    fun getPreviousQuestion() {
        viewModelScope.launch {
            currentQuestionNumber.emit(currentQuestionNumber.value - 1)
        }
    }

    private suspend fun getQuizQuestions() {
        val list = quizRepository.getQuizQuestions()
        quizQuestionsList.emit(list.map { it.getModel() })
        quizQuestionsListV2.emit(list.map { it.getModel() }.toMutableList())
    }

    fun updateSelectedOptions(quizOptionsModel: QuizOptionsModel) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val l = quizQuestionsList.value.toMutableList()
            val i = l[currentQuestionNumber.value]
            i.options = i.options.map { c ->
                c.copy(
                    oldIsSelected = c.isSelected,
                    isSelected = c.id == quizOptionsModel.id,
                    bg = if (c.isSelected && !c.isCorrectAnswer && i.isShowCorrectAnswer) {
                        OptionBackground.RED
                    } else if (c.isCorrectAnswer && i.isShowCorrectAnswer) {
                        OptionBackground.GREEN
                    } else if (c.isSelected) {
                        OptionBackground.BLUE
                    } else {
                        OptionBackground.WHITE
                    }
                )
            }.toMutableList()
            l[currentQuestionNumber.value] = i
            quizQuestionsList.tryEmit(l)
        }
    }

    fun showCorrectAnswers() {
        val list = quizQuestionsList.value

        viewModelScope.launch {
            quizQuestionsList.emit(list.filter {
                it.isShowCorrectAnswer = true
                true
            })
            currentQuestionNumber.emit(0)
        }
    }

    fun onButtonClicked(buttonType: ClickEventType) {
        when (buttonType) {
            ClickEventType.PREVIOUS -> {
                currentQuestionNumber.apply {
                    if (value > 0) {
                        value -= value
                    }
                }
            }

            ClickEventType.NEXT -> {
                currentQuestionNumber.apply {
                    if (value < (quizQuestionsListV2.value?.size ?: 0)) {
                        value += value
                    }
                }
            }
        }

    }

    fun updateQuestionList(currentQuestionModel: QuizModel, currentQuestionNumber: Int) {
        viewModelScope.launch {
            quizQuestionsListV2.update { questions ->
                questions?.apply {
                    println("==> ${this[currentQuestionNumber]}")
                    println("==> $currentQuestionModel")
                    this[currentQuestionNumber] = currentQuestionModel
                }
            }
        }
    }

    sealed class QuizResponseModel {
        data class QuizOptionList(val list: List<QuizModel>) : QuizResponseModel()
        object Loading : QuizResponseModel()
        data class Error(val error: String) : QuizResponseModel()
    }

}