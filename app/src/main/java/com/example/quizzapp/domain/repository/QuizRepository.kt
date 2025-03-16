package com.example.quizzapp.domain.repository

import com.example.quizzapp.domain.entites.QuizModel


interface QuizRepository {
    suspend fun getQuizQuestions(): ArrayList<QuizModel>
}