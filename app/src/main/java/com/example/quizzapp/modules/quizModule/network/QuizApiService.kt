package com.example.quizzapp.modules.quizModule.network

import com.example.quizzapp.modules.quizModule.models.data.QuestionV1Response
import retrofit2.http.GET

interface QuizApiService {

    @GET("v2/questions")
    suspend fun getQuizQuestions(): List<QuestionV1Response?>
}