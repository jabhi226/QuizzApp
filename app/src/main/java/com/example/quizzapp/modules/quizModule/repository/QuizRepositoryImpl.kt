package com.example.quizzapp.modules.quizModule.repository

import com.example.quizzapp.modules.quizModule.models.ui.QuizModel
import com.example.quizzapp.modules.quizModule.network.QuizApiService
import java.lang.Exception
import javax.inject.Singleton

@Singleton
class QuizRepositoryImpl(private val quizApiService: QuizApiService) : QuizRepository {

    override suspend fun getQuizQuestions(): ArrayList<QuizModel> {
        try {
            val api = quizApiService.getQuizQuestions()
            return api.map {
                it?.getQuizModelListFromQuestionResponse() ?: QuizModel()
            } as ArrayList<QuizModel>
        } catch (e: Exception) {
            e.printStackTrace()

        }
        return arrayListOf()
    }
}
