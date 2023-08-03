package com.example.quizzapp.modules.quizModule.repository

import com.example.quizzapp.modules.quizModule.models.ui.OptionBackground
import com.example.quizzapp.modules.quizModule.models.ui.QuizModel
import com.example.quizzapp.modules.quizModule.models.ui.QuizOptionsModel
import kotlin.random.Random

class QuizRepositoryTestImpl : QuizRepository {

    override suspend fun getQuizQuestions(): ArrayList<QuizModel> {
        val l = arrayListOf<QuizModel>()
        for (i in 0..9) {
            val q = QuizModel()
            q.question = "Question $i"
            q.isShowCorrectAnswer = true
            for (j in 0..3) {
                val item = QuizOptionsModel(
                    "$j",
                    "Answer: $j",
                    oldIsSelected = false,
                    isSelected = false,
                    isCorrectAnswer = Random.nextInt(0, 4) == j,
                    bg = OptionBackground.WHITE
                )
                q.options.add(item)
            }
            l.add(q)
        }
        return l
    }
}