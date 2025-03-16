package com.example.quizzapp.domain.entites

data class QuizModel(
    val quizId: String,
    val question: String,
    val options: List<Option> = listOf(),
    val correctAnswerPosition: Int
)
