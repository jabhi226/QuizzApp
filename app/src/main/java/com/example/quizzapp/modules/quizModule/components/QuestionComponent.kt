package com.example.quizzapp.modules.quizModule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzapp.modules.core.components.CommonText
import com.example.quizzapp.modules.quizModule.Testing
import com.example.quizzapp.modules.quizModule.model.QuizModel
import com.example.quizzapp.modules.quizModule.screens.ClickEventType


@Preview(showSystemUi = true)
@Composable
fun QuestionComponent(
    modifier: Modifier = Modifier,
    currentQuestionNumber: Int = 3,
    questionsList: List<QuizModel> = Testing.quizList(),
    handleClickEvents: (ClickEventType, QuizModel) -> Unit = { _, _ -> },
    timer: Float = 10F,
    currentQuizQuestion: QuizModel = Testing.quizList()[0]
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .shadow(
                    elevation = 32.dp,
                    shape = RoundedCornerShape(16.dp),
                    clip = true,
                )
                .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CommonText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                fontSize = 16.sp,
                text = currentQuizQuestion.question,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .scale(1.5F)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(100.dp)
                    ),
                progress = { timer / 30f },
                strokeWidth = 3.dp,
                trackColor = MaterialTheme.colorScheme.inversePrimary,
                color = MaterialTheme.colorScheme.primary,
                strokeCap = StrokeCap.Round,
                gapSize = 0.dp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            CommonText(
                text = "${timer.toInt()}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}