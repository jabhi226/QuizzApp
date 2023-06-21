package com.example.quizzapp.modules.quizModule.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.modules.quizModule.models.ui.QuizModel
import com.example.quizzapp.modules.quizModule.models.ui.QuizOptionsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {

    private val quizQuestionsList = MutableSharedFlow<ArrayList<QuizModel>>()

    private var currentQuestion: Int = 0


    init {
        viewModelScope.launch {
            getQuizQuestions()
        }
    }

    fun getNextQuestion() {
        val abc = 10
        println(
            abc.let {
                it.plus(10)
            }
        )

        println(
            abc.also {
                it.plus(10)
            }
        )

        currentQuestion++
    }

    private suspend fun getQuizQuestions() {
        val l = arrayListOf<QuizModel>()
        for (i in 0..9) {
            val q = QuizModel()
            q.question = "Question $i"
            for (j in 0..3) {
                val optionsModel = QuizOptionsModel(
                    j,
                    "Answer: $j",
                    false,
                    j % 3 == 0
                )
                q.options.add(optionsModel)
            }
            l.add(q)
        }
        quizQuestionsList.emit(l)
    }
}