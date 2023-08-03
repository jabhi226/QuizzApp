package com.example.quizzapp.modules.quizModule.models.ui

class QuizModel {
    var question: String = ""
    var options: ArrayList<QuizOptionsModel> = arrayListOf()
    var selected: Int = -1
    var correctAnswer: Int = 0
    var isShowCorrectAnswer = false
}