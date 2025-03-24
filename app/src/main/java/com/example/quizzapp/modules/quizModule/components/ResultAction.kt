package com.example.quizzapp.modules.quizModule.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzapp.R
import com.example.quizzapp.modules.core.components.CommonImage
import com.example.quizzapp.modules.core.components.CommonText
import com.example.quizzapp.modules.quizModule.model.ResultAction

//@Preview(showBackground = true)
@Composable
fun ResultAction(
    modifier: Modifier = Modifier,
    model: ResultAction
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CommonImage(
            modifier = Modifier
                .size(64.dp)
                .background(color = MaterialTheme.colorScheme.inversePrimary, shape = RoundedCornerShape(200.dp))
                .padding(8.dp),
            painter = painterResource(id = model.icon),
        )
        CommonText(
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .align(Alignment.CenterHorizontally),
            text = model.title,
            fontSize = 18.sp
        )

    }
}