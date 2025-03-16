package com.example.quizzapp.modules.quizModule.model

data class ResultAction(
    val title: String,
    val icon: Int,
    val action: Action,
    val tint: Int
) {
    companion object {
        enum class Action {
            PLAY_AGAIN,
            SEE_RESULTS
        }
    }
}

