package com.example.quizzapp.modules.core.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quizzapp.R

@Preview
@Composable
fun CommonImage(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(R.drawable.baseline_check_circle_24),
    contentDescription: String? = null,
    tint: Color? = null
) {
    Image(
        modifier = modifier,
        contentDescription = contentDescription,
        painter = painter,
        colorFilter = tint?.let { ColorFilter.tint(it) }
    )
}