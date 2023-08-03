package com.example.quizzapp.modules.quizModule.repository

import com.example.quizzapp.modules.quizModule.models.ui.QuizModel

interface QuizRepository {
    suspend fun getQuizQuestions(): ArrayList<QuizModel>
}