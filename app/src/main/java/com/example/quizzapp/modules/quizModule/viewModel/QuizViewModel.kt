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

    init {
        viewModelScope.launch {
            getQuizQuestions()
        }
    }


    private suspend fun getQuizQuestions() {
        val list = quizRepository.getQuizQuestions()
        quizQuestionsListV2.emit(list.map { it.getModel() }.toMutableList())
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

}