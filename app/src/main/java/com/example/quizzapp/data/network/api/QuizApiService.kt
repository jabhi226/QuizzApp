package com.example.quizzapp.data.network.api

import com.example.quizzapp.data.network.models.QuestionV1Response
import retrofit2.http.GET

interface QuizApiService {

    @GET("v2/questions")
    suspend fun getQuizQuestions(): List<QuestionV1Response?>
}