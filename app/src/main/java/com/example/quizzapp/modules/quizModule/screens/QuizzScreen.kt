package com.example.quizzapp.modules.quizModule.screens

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzapp.R
import com.example.quizzapp.modules.core.components.CommonButton
import com.example.quizzapp.modules.core.components.CommonImage
import com.example.quizzapp.modules.core.components.CommonText
import com.example.quizzapp.modules.quizModule.Testing
import com.example.quizzapp.modules.quizModule.components.AnswerComponent
import com.example.quizzapp.modules.quizModule.components.QuestionComponent
import com.example.quizzapp.modules.quizModule.model.QuizModel
import kotlinx.coroutines.delay

@Preview(showSystemUi = true)
@Composable
fun QuizzScreen(
    modifier: Modifier = Modifier,
    currentQuestionNumber: Int = 3,
    questionsList: List<QuizModel> = Testing.quizList(),
    handleClickEvents: (ClickEventType, QuizModel) -> Unit = { _, _ -> },
) {

    var currentQuizQuestion by remember { mutableStateOf(questionsList[currentQuestionNumber]) }
    var timer by remember { mutableFloatStateOf(currentQuizQuestion.timeTakenToSolve) }

    LaunchedEffect(currentQuestionNumber) {
        currentQuizQuestion = questionsList[currentQuestionNumber]
        timer = currentQuizQuestion.timeTakenToSolve
        while (timer < 30F) {
            delay(1000)
            timer++
        }
    }

    Scaffold(
        topBar = {
        },
    ) { innerPadding ->
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
                                    ClickEventType.PREVIOUS,
                                    currentQuizQuestion.copy(timeTakenToSolve = timer)
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
                            text = "${currentQuestionNumber + 1}/${questionsList.size}",
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
                    questionsList = questionsList,
                    timer = timer,
                    currentQuizQuestion = currentQuizQuestion,
                    handleClickEvents = { _, _ -> }
                )
            }

            item {
                Column {
                    currentQuizQuestion.options.forEach {
                        AnswerComponent(modifier = Modifier, question = it)
                    }
                }
            }

            item {
                CommonButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    enabled = currentQuestionNumber < (questionsList.size - 1),
                    onClick = {
                        handleClickEvents(
                            ClickEventType.NEXT,
                            currentQuizQuestion.copy(timeTakenToSolve = timer)
                        )
                    },
                    text = "Next"
                )
            }

        }
    }

}

enum class ClickEventType {
    PREVIOUS,
    NEXT
}
