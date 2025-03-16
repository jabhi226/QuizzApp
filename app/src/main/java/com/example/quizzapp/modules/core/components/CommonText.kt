package com.example.quizzapp.modules.core.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Preview(showSystemUi = true)
@Composable
fun CommonText(
    modifier: Modifier = Modifier,
    text: String = "In what year did the United States host the FIFA World Cup for the First time?",
    fontSize: TextUnit = 14.sp,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.W500,
//    fontFamily: FontFamily = FontFamily(Font(R.font.rubik_font)),
    textColor: Color = MaterialTheme.colorScheme.primary,
    lineHeight: TextUnit = 24.sp,
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontStyle = fontStyle,
//        fontFamily = fontFamily
        lineHeight = lineHeight,
    )
}