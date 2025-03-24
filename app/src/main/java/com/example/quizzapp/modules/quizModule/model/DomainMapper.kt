package com.example.quizzapp.modules.quizModule.model

import com.example.quizzapp.domain.entites.QuizModel as DomainQuizModel
import com.example.quizzapp.domain.entites.Option as DomainOptionModel

object DomainMapper {

    fun DomainQuizModel.getModel(): QuizModel {
        return QuizModel().apply {
            this.question = this@getModel.question
            this.options = this@getModel.options.map { it.getModel() }
            this.correctAnswer = this@getModel.correctAnswerPosition
        }
    }

    fun DomainOptionModel.getModel(): QuizOptionsModel {
        return QuizOptionsModel(
            id = this.optionId,
            option = this.optionValue,
            isSelected = false,
            isCorrectAnswer = false,
        )
    }

    /**
     *
     *     var question: String = ""
     *     var options: ArrayList<QuizOptionsModel> = arrayListOf()
     *     var selected: Int = -1
     *     var correctAnswer: Int = 0
     *     var isShowCorrectAnswer = false
     */
}