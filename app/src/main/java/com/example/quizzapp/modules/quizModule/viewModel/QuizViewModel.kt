package com.example.quizzapp.modules.quizModule.viewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.modules.quizModule.repository.QuizRepository
import com.example.quizzapp.modules.quizModule.models.ui.OptionBackground
import com.example.quizzapp.modules.quizModule.models.ui.QuizModel
import com.example.quizzapp.modules.quizModule.models.ui.QuizOptionsModel
import com.example.quizzapp.modules.quizModule.ui.fragment.QuizzFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler)

    val currentFragment = MutableStateFlow<Fragment>(QuizzFragment())

    private val quizQuestionsList = MutableStateFlow<QuizResponseModel?>(null)
    private val currentQuestionNumber = MutableStateFlow(0)
    var isCurrentAnswerSelected = MutableStateFlow<Boolean?>(null)
    val checkIfBackButtonIsClickable = MutableStateFlow<Boolean?>(null)

    val uiEvent = MutableStateFlow<Int?>(null)

    val currentQuestion = quizQuestionsList.combine(currentQuestionNumber) { l, qNumber ->
        checkIfBackButtonIsClickable.emit(qNumber > 0)
        when (l){
            is QuizResponseModel.QuizOptionList -> {
                if (l.list.size > qNumber){
                    isCurrentAnswerSelected.emit(l.list[qNumber].options.any { it.isSelected })
                    l.list[qNumber]
                } else {
                    // last quesiton
                    uiEvent.emit((uiEvent.value ?: 1) + 1)
                    null
                }
            }
            is QuizResponseModel.Error, QuizResponseModel.Loading, null -> {
                null
            }
        }
    }

    init {
        scope.launch {
            getQuizQuestions()
        }
    }

    fun getNextQuestion() {
        CoroutineScope(Dispatchers.Default).launch {
            currentQuestionNumber.emit(currentQuestionNumber.value + 1)
        }
    }

    fun getPreviousQuestion() {
        CoroutineScope(Dispatchers.Default).launch {
            currentQuestionNumber.emit(currentQuestionNumber.value - 1)
        }
    }

    private suspend fun getQuizQuestions() {
        val list = quizRepository.getQuizQuestions()

        quizQuestionsList.emit(
            QuizResponseModel.QuizOptionList(
                list
            )
        )
    }

    fun updateSelectedOptions(quizOptionsModel: QuizOptionsModel) {
        viewModelScope.launch(coroutineExceptionHandler) {
            if (quizQuestionsList.value !is QuizResponseModel.QuizOptionList) {
                return@launch
            }
            val l =
                (quizQuestionsList.value as QuizResponseModel.QuizOptionList).list.toMutableList()
            quizQuestionsList.tryEmit(QuizResponseModel.Loading)
            delay(2)
            val i = l[currentQuestionNumber.value]
            i.options = i.options.map { c ->
                c.oldIsSelected = c.isSelected
                c.isSelected = c.id == quizOptionsModel.id
                c.bg = if (c.isSelected && !c.isCorrectAnswer && i.isShowCorrectAnswer) {
                    OptionBackground.RED
                } else if (c.isCorrectAnswer && i.isShowCorrectAnswer) {
                    OptionBackground.GREEN
                } else if (c.isSelected) {
                    OptionBackground.BLUE
                } else {
                    OptionBackground.WHITE
                }
                c
            }.toMutableList() as ArrayList<QuizOptionsModel>
            l[currentQuestionNumber.value] = i
            quizQuestionsList.tryEmit(QuizResponseModel.QuizOptionList(l))
        }
    }

    fun showCorrectAnswers() {
        val list = quizQuestionsList.value
        if (list is QuizResponseModel.QuizOptionList){
            viewModelScope.launch {
                quizQuestionsList.emit(QuizResponseModel.QuizOptionList(list.list.filter {
                    it.isShowCorrectAnswer = true
                    true
                }))
                currentQuestionNumber.emit(0)
            }
        }
    }

    sealed class QuizResponseModel {
        data class QuizOptionList(val list: List<QuizModel>) : QuizResponseModel()
        object Loading : QuizResponseModel()
        data class Error(val error: String) : QuizResponseModel()
    }

}