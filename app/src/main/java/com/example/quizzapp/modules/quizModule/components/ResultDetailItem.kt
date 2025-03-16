package com.example.quizzapp.modules.quizModule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzapp.modules.quizModule.model.ResultDetailsModel


@Composable
fun ResultDetailItem(
    modifier: Modifier = Modifier,
    detail: ResultDetailsModel
) {
    Row(modifier = modifier.padding(8.dp)) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(20.dp)
                .background(color = colorResource(detail.color), shape = RoundedCornerShape(20.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = detail.value,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold, color = colorResource(detail.color)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = detail.title, fontSize = 16.sp)
        }
    }
}