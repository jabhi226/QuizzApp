package com.example.quizzapp.modules.quizModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.domain.repository.QuizRepository
import com.example.quizzapp.modules.quizModule.model.DomainMapper.getModel
import com.example.quizzapp.modules.quizModule.model.QuizModel
import com.example.quizzapp.modules.quizModule.model.QuizOptionsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
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
//
//    fun updateSelectedOption(updatedOption: QuizOptionsModel, currentQuestionNumber: Int) {
//        println("==> " + quizQuestionsListV2.value?.get(currentQuestionNumber)?.options)
//        viewModelScope.launch {
//            quizQuestionsListV2.emit(
//                quizQuestionsListV2.value.apply {
//                    this?.get(currentQuestionNumber)?.apply {
//                        options = options.mapIndexed { index, quizOptionsModel ->
//                            if (quizOptionsModel.id == updatedOption.id) {
//                                selected = index
//                                quizOptionsModel.copy(isSelected = true)
//                            } else {
//                                quizOptionsModel.copy(isSelected = false)
//                            }
//                        }.toMutableList()
//                    }
//                }
//            )
//        }
//        println("--> " + quizQuestionsListV2.value?.get(currentQuestionNumber)?.options)
//    }

}