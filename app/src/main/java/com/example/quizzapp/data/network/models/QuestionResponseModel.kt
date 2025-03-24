package com.example.quizzapp.data.network.models

import com.example.quizzapp.domain.entites.Option
import com.example.quizzapp.domain.entites.QuizModel
import com.google.gson.annotations.SerializedName
import java.util.Date


data class QuestionV1Response(

    @SerializedName("category") var category: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("correctAnswer") var correctAnswer: String? = null,
    @SerializedName("incorrectAnswers") var incorrectAnswers: ArrayList<String> = arrayListOf(),
    @SerializedName("question") var question: Question? = Question(),
    @SerializedName("tags") var tags: ArrayList<String> = arrayListOf(),
    @SerializedName("type") var type: String? = null,
    @SerializedName("difficulty") var difficulty: String? = null,
    @SerializedName("regions") var regions: ArrayList<String> = arrayListOf(),
    @SerializedName("isNiche") var isNiche: Boolean? = null

) {

    private var optionId = 0
    fun getDomainModel(): QuizModel {
        val options = ArrayList<Option>().apply {
            addAll(incorrectAnswers.map {
                optionId++
                Option(optionId.toString(), it, false)
            })
            optionId++
            add(
                Option(optionId.toString(), correctAnswer.toString(), true)
            )
        }.shuffled()
        val correctAnswerPosition = options.map { it.optionValue }.indexOf(correctAnswer)

        return QuizModel(
            quizId = id.toString(),
            question = question?.text.toString(),
            options = options,
            correctAnswerPosition = correctAnswerPosition
        )
    }


    /*
    fun getQuizModelListFromQuestionResponse() = QuizModel().apply {
        question = "Question: ${this@QuestionV1Response.question?.text}"
        isShowCorrectAnswer = false
        incorrectAnswers.add(correctAnswer.toString())
        incorrectAnswers.shuffled().forEachIndexed { index, s ->
            val item = QuizOptionsModel(
                "${id.toString()}_$index",
                s,
                oldIsSelected = false,
                isSelected = false,
                isCorrectAnswer = s == correctAnswer.toString(),
                bg = OptionBackground.WHITE
            )
            options.add(item)
        }
    }
     */

}

data class Question(
    @SerializedName("text") var text: String? = null
)
