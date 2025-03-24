package com.example.quizzapp.modules.quizModule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizzapp.R
import com.example.quizzapp.modules.core.components.CommonImage
import com.example.quizzapp.modules.core.components.CommonText
import com.example.quizzapp.modules.quizModule.Testing
import com.example.quizzapp.modules.quizModule.model.QuizOptionsModel

@Preview(showBackground = true)
@Composable
fun AnswerComponent(
    modifier: Modifier = Modifier,
    optionsModel: QuizOptionsModel = Testing.quizList()[0].options[0],
    isShowCorrectAnswer: Boolean = false,
    onAnswerSelected: (QuizOptionsModel) -> Unit = {}
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onAnswerSelected(optionsModel)
            }
            .shadow(
                elevation = if (optionsModel.isSelected) {
                    8.dp
                } else {
                    0.dp
                },
                shape = RoundedCornerShape(16.dp),
                clip = true,
            )
            .background(
                color = if (optionsModel.isSelected) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    Color.White
                },
                shape = RoundedCornerShape(16.dp),
            )
            .padding(16.dp)

    ) {
        CommonText(
            modifier = Modifier.fillMaxWidth(0.9F),
            text = optionsModel.option,
        )
        if (isShowCorrectAnswer) {
            if (optionsModel.isCorrectAnswer) {
                CommonImage(
                    painter = painterResource(id = R.drawable.baseline_check_circle_24)
                )
            } else if (optionsModel.isSelected) {
                CommonImage(
                    painter = painterResource(id = R.drawable.baseline_cancel_24),
                )
            }
        }
    }
}