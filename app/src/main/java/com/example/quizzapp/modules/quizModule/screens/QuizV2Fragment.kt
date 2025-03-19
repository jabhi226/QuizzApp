package com.example.quizzapp.modules.quizModule.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.quizzapp.R
import com.example.quizzapp.modules.quizModule.model.ResultAction
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

            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = ScreenQuizzScreen
            ) {
                composable<ScreenQuizzScreen> {
                    QuizzScreen(
                        modifier = Modifier,
                        redirectToNextScreen = { total, correct, incorrect, attempted ->
                            navController.navigate(
                                ScreenResultScreen(
                                    totalQuestions = total,
                                    totalCorrectAnswers = correct,
                                    totalInCorrectAnswers = incorrect,
                                    totalAttemptedQuestion = attempted
                                )
                            )
                        }
                    )
                }
                composable<ScreenResultScreen> {
                    val arg = it.toRoute<ScreenResultScreen>()
                    ResultScreen(
                        modifier = Modifier,
                        totalQuestions = arg.totalQuestions,
                        totalCorrectAnswers = arg.totalCorrectAnswers,
                        totalInCorrectAnswer = arg.totalInCorrectAnswers,
                        totalAttemptedQuestion = arg.totalAttemptedQuestion
                    )
                }
            }

        }
    }
}