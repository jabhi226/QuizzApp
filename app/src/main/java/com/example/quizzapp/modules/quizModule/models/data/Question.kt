package com.example.quizzapp.modules.quizModule.models.data

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("text") var text: String? = null
)