package com.example.quizzapp.modules.quizModule.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.quizzapp.modules.quizModule.viewModel.QuizViewModel

class QuizV2Fragment : Fragment() {

    private val viewModel by activityViewModels<QuizViewModel>()
    private lateinit var composeView: ComposeView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).also {
            composeView = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        composeView.setContent {

            var currentQuestionNumber by rememberSaveable { mutableIntStateOf(0) }
            val questionsList by viewModel.quizQuestionsListV2.collectAsState(initial = null)

            questionsList?.let { questions ->
                QuizzScreen(
                    modifier = Modifier,
                    currentQuestionNumber = currentQuestionNumber,
                    handleClickEvents = { eventType, currentQuestionModel ->
                        when (eventType) {
                            ClickEventType.PREVIOUS -> {
                                viewModel.updateQuestionList(
                                    currentQuestionModel,
                                    currentQuestionNumber
                                )
                                while (currentQuestionNumber != 0) {
                                    if (questions[currentQuestionNumber - 1].timeTakenToSolve < 30F) {
                                        currentQuestionNumber--
                                        break
                                    }
                                    currentQuestionNumber--
                                }
                            }

                            ClickEventType.NEXT -> {
                                viewModel.updateQuestionList(
                                    currentQuestionModel,
                                    currentQuestionNumber
                                )

                                while (currentQuestionNumber != (questions.size - 1)) {
                                    if (questions[currentQuestionNumber + 1].timeTakenToSolve < 30F) {
                                        currentQuestionNumber++
                                        break
                                    }
                                    currentQuestionNumber++
                                }
                            }
                        }
                    },
                    questionsList = questions
                )
            }

        }
    }
}