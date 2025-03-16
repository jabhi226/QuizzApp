package com.example.quizzapp.modules.quizModule

import com.example.quizzapp.modules.quizModule.model.OptionBackground
import com.example.quizzapp.modules.quizModule.model.QuizModel
import com.example.quizzapp.modules.quizModule.model.QuizOptionsModel

object Testing {

    fun quizList(): ArrayList<QuizModel> {
        return arrayListOf(
            QuizModel().apply {
                question =
                    "In what year did the United States host the FIFA World Cup for the First time?"
                options = arrayListOf(
                    QuizOptionsModel(
                        id = "",
                        option = "1930",
                        oldIsSelected = false,
                        isSelected = false,
                        isCorrectAnswer = false,
                        bg = OptionBackground.BLUE
                    ),
                    QuizOptionsModel(
                        id = "",
                        option = "1931",
                        oldIsSelected = false,
                        isSelected = false,
                        isCorrectAnswer = false,
                        bg = OptionBackground.BLUE
                    ),
                    QuizOptionsModel(
                        id = "",
                        option = "1932",
                        oldIsSelected = false,
                        isSelected = false,
                        isCorrectAnswer = false,
                        bg = OptionBackground.BLUE
                    ),
                    QuizOptionsModel(
                        id = "",
                        option = "1933",
                        oldIsSelected = false,
                        isSelected = false,
                        isCorrectAnswer = false,
                        bg = OptionBackground.BLUE
                    ),
                )
            }
        )
    }

}