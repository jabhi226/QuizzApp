package com.example.quizzapp.modules.quizModule

import com.example.quizzapp.modules.quizModule.model.OptionBackground
import com.example.quizzapp.modules.quizModule.model.QuizModel
import com.example.quizzapp.modules.quizModule.model.QuizOptionsModel

object Testing {

    fun quizList(): ArrayList<QuizModel> {
        return arrayListOf(
            QuizModel().apply {
                isShowCorrectAnswer = false
                question =
                    "In what year did the United States host the FIFA World Cup for the First time?"
                options = arrayListOf(
                    QuizOptionsModel(
                        id = "",
                        option = "1930",
                        isSelected = true,
                        isCorrectAnswer = true,
                        
                    ),
                    QuizOptionsModel(
                        id = "",
                        option = "1931",
                        
                        isSelected = false,
                        isCorrectAnswer = false,
                        
                    ),
                    QuizOptionsModel(
                        id = "",
                        option = "1932",
                        
                        isSelected = true,
                        isCorrectAnswer = false,
                        
                    ),
                    QuizOptionsModel(
                        id = "",
                        option = "1933",
                        
                        isSelected = false,
                        isCorrectAnswer = false,
                        
                    ),
                )
            }
        )
    }

}