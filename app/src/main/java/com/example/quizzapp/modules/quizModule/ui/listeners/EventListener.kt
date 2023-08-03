package com.example.quizzapp.modules.quizModule.ui.listeners

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ItemOptionsBinding
import com.example.quizzapp.modules.quizModule.models.ui.QuizOptionsModel

class EventListener(val onOptionSelected: (QuizOptionsModel) -> Unit) {

    fun onOptionSelected(view: View, quizOptionsModel: QuizOptionsModel) {
//        view.background = ContextCompat.getDrawable(view.context, R.drawable.bg_blue_round)
        onOptionSelected(quizOptionsModel)
    }

}