package com.example.quizzapp.modules.quizModule.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quizzapp.R
import com.example.quizzapp.modules.core.components.CommonButton
import com.example.quizzapp.modules.core.components.CommonImage
import com.example.quizzapp.modules.core.components.CommonText
import com.example.quizzapp.modules.quizModule.Testing
import com.example.quizzapp.modules.quizModule.components.AnswerComponent
import com.example.quizzapp.modules.quizModule.components.QuestionComponent
import com.example.quizzapp.modules.quizModule.model.QuizModel
import com.example.quizzapp.modules.quizModule.model.QuizOptionsModel
import com.example.quizzapp.modules.quizModule.viewModel.QuizViewModel
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Composable
fun QuizzScreen(
    modifier: Modifier = Modifier,
    redirectToNextScreen: (Int, Int, Int, Int) -> Unit = { _, _, _, _ -> }
) {

    val viewModel: QuizViewModel = hiltViewModel()

    val questionsList by viewModel.quizQuestionsListV2.collectAsState(initial = null)
    var currentQuestionNumber by rememberSaveable { mutableIntStateOf(0) }
    var selectedQuestion by remember { mutableStateOf<QuizModel?>(null) }

    var timer by remember { mutableFloatStateOf(0F) }

    fun handleClickEvents(eventType: ClickEventType) {
        val questions = questionsList ?: return
        selectedQuestion?.let {
            when (eventType) {
                ClickEventType.PREVIOUS -> {
                    viewModel.updateQuestionList(
                        it.copy(timeTakenToSolve = timer),
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
                        it.copy(timeTakenToSolve = timer),
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

                ClickEventType.SUBMIT -> {
                    viewModel.updateQuestionList(
                        it.copy(timeTakenToSolve = timer),
                        currentQuestionNumber
                    )
                    redirectToNextScreen(
                        questions.size,
                        questions.count { it.correctAnswer == it.selected },
                        questions.count { it.correctAnswer != it.selected },
                        questions.count { it.selected != -1 }
                    )
                }
            }
        }
    }

//    LaunchedEffect(questionsList) {
//        questionsList?.let {
//            timer = it[currentQuestionNumber].timeTakenToSolve
//        }
//    }

    LaunchedEffect(currentQuestionNumber, questionsList) {
        val list = questionsList ?: return@LaunchedEffect
        selectedQuestion = list[currentQuestionNumber]
        selectedQuestion?.let {
            timer = it.timeTakenToSolve
            while (timer < 30F) {
                delay(1000)
                timer++
            }
            if (timer == 30F) {
                handleClickEvents(
                    if (currentQuestionNumber < (list.size - 1)) ClickEventType.NEXT else ClickEventType.SUBMIT
                )
            }
        }
    }

    BackHandler {
        if (currentQuestionNumber != 0) {
            selectedQuestion?.let {
                handleClickEvents(
                    ClickEventType.PREVIOUS
                )
            }
        }
    }

    Scaffold(
        topBar = {
        },
    ) { innerPadding ->
        if (questionsList == null) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .scale(1.4F)
            )
        } else {
            questionsList?.let {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(color = MaterialTheme.colorScheme.secondaryContainer),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    item {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable((currentQuestionNumber > 0), onClick = {
                                        handleClickEvents(
                                            ClickEventType.PREVIOUS
                                        )
                                    })
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CommonImage(
                                    modifier = Modifier.scale(0.8F),
                                    painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                CommonText(
                                    fontSize = 16.sp,
                                    text = "Previous"
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                CommonText(
                                    text = "${currentQuestionNumber + 1}/${it.size}",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Black,
                                )
                            }
                        }
                    }

                    item {
                        QuestionComponent(
                            modifier = Modifier,
                            currentQuestionNumber = currentQuestionNumber,
                            questionsList = it,
                            timer = timer,
                            currentQuizQuestion = it[currentQuestionNumber].question,
                            handleClickEvents = { _, _ -> }
                        )
                    }

                    item {
                        AnswersComponent(
                            question = selectedQuestion,
                            updateSelectedOption = { updatedOption ->
                                var selectedIndex = -1
                                selectedQuestion = selectedQuestion?.copy(
                                    options = selectedQuestion?.options?.mapIndexed { index, quizOptionsModel ->
                                        if (quizOptionsModel.id == updatedOption.id) {
                                            selectedIndex = index
                                            quizOptionsModel.copy(isSelected = true)
                                        } else {
                                            quizOptionsModel.copy(isSelected = false)
                                        }
                                    }?.toMutableList() ?: listOf(),
                                    selected = selectedIndex
                                )
                                println("==> ${selectedQuestion?.selected}")
                            })
                    }

                    item {
                        CommonButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            onClick = {
                                handleClickEvents(
                                    if (currentQuestionNumber < (it.size - 1)) ClickEventType.NEXT else ClickEventType.SUBMIT,
                                )
                            },
                            text = if (currentQuestionNumber < (it.size - 1)) "Next" else "Submit"
                        )
                    }

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun AnswersComponent(
    question: QuizModel? = Testing.quizList()[0],
    updateSelectedOption: (QuizOptionsModel) -> Unit = {}
) {
    Column {
        question?.options?.forEach { option ->
            AnswerComponent(
                modifier = Modifier,
                optionsModel = option,
                isShowCorrectAnswer = question.isShowCorrectAnswer,
                onAnswerSelected = {
                    updateSelectedOption(it)
                }
            )
        }
    }
}

enum class ClickEventType {
    PREVIOUS,
    NEXT,
    SUBMIT
}

@Serializable
object ScreenQuizzScreen