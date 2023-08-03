package com.example.quizzapp.modules.quizModule.models.data

import com.example.quizzapp.modules.quizModule.models.ui.OptionBackground
import com.example.quizzapp.modules.quizModule.models.ui.QuizModel
import com.example.quizzapp.modules.quizModule.models.ui.QuizOptionsModel
import com.google.gson.annotations.SerializedName


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

    fun getQuizModelListFromQuestionResponse(): QuizModel {
        val q = QuizModel()
        q.question = "Question: ${question?.text}"
        q.isShowCorrectAnswer = false
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
            q.options.add(item)
        }
        return q
    }
}