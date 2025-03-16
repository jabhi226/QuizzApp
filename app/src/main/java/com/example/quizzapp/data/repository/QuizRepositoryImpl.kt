package com.example.quizzapp.data.repository

import com.example.quizzapp.data.network.api.QuizApiService
import com.example.quizzapp.domain.entites.QuizModel
import com.example.quizzapp.domain.repository.QuizRepository
import com.google.gson.Gson
import java.lang.Exception
import javax.inject.Singleton

@Singleton
class QuizRepositoryImpl(private val quizApiService: QuizApiService) : QuizRepository {

    override suspend fun getQuizQuestions(): ArrayList<QuizModel> {
        try {
            val api = quizApiService.getQuizQuestions()
            println("==> ${Gson().toJson(api)}")
            return ArrayList<QuizModel>().apply {
                addAll(api.filterNotNull().map { it.getDomainModel() })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return arrayListOf()
        }
    }
}
