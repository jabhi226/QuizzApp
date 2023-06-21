package com.example.quizzapp.modules.quizModule.models.ui

data class QuizOptionsModel(
    val position: Int,
    val option: String,
    val isSelected: Boolean,
    val isCorrectAnswer: Boolean
)