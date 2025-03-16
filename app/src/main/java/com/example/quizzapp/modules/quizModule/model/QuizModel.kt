package com.example.quizzapp.modules.quizModule.model

data class QuizModel(
    var question: String = "",
    var options: List<QuizOptionsModel> = listOf(),
    var selected: Int = -1,
    var correctAnswer: Int = 0,
    var isShowCorrectAnswer: Boolean = false,
    var timeTakenToSolve: Float = 0F
)