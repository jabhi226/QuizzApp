package com.example.quizzapp.modules.quizModule.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizzapp.modules.core.components.CommonText

@Preview(showSystemUi = true)
@Composable
fun ScoreComponent(
    modifier: Modifier = Modifier,
    score: Float = 100.0F
) {

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animation = infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(3000)),
        label = ""
    )

    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5F)
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary,
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 32.dp)
                    .scale(animation.value)
                    .clip(shape = CircleShape)
                    .size(250.dp)
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 32.dp)
                    .size(200.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(100.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CommonText(
                    text = "Your Score",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.Bottom) {

                    CommonText(
                        text = score.toInt().toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    CommonText(
                        text = "pts",
                        fontSize = 18.sp
                    )
                }
            }
        }

    }

}