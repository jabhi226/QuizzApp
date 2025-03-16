package com.example.quizzapp.modules.quizModule.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizzapp.R
import com.example.quizzapp.modules.quizModule.components.ResultAction
import com.example.quizzapp.modules.quizModule.components.ResultDetail
import com.example.quizzapp.modules.quizModule.components.ScoreComponent
import com.example.quizzapp.modules.quizModule.model.ResultAction
import com.example.quizzapp.modules.quizModule.model.ResultDetailsModel

@Preview(showSystemUi = true)
@Composable
fun ResultScreen(
    modifier: Modifier = Modifier,
    resultActions: List<ResultAction> = listOf(
        ResultAction(
            "Play Again",
            R.drawable.ic_restart,
            ResultAction.Companion.Action.PLAY_AGAIN,
            R.color.color1
        ),
        ResultAction(
            "Check Answers",
            R.drawable.ic_check_answers,
            ResultAction.Companion.Action.SEE_RESULTS,
            R.color.color2
        )
    )
) {

    val resultList = remember {
        mutableStateListOf<ResultDetailsModel>()
    }

    LaunchedEffect(Unit) {
        val colors = listOf(
            R.color.color1,
            R.color.color2,
            R.color.success_green,
            R.color.error_red,
        )
        val titles = listOf(
            "Completion",
            "Total",
            "Correct",
            "Incorrect",
        )
        val values = listOf(
            "100%",
            "10",
            "6",
            "4",
        )
        for (i in colors.indices) {
            resultList.add(
                i,
                ResultDetailsModel(
                    title = titles[i],
                    value = values[i],
                    color = colors[i],
                )
            )
        }
    }

    Scaffold(
        topBar = {
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(modifier = Modifier) {
                ScoreComponent(
                    modifier = Modifier
                )
                ResultDetail(
                    details = resultList
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalArrangement = Arrangement.Center,
                ) {
                    items(resultActions.size) { index ->
                        ResultAction(model = resultActions[index])
                    }
                }
            }


        }
    }
}