package com.example.quizzapp.modules.quizModule.model

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.quizzapp.R

data class QuizOptionsModel(
    val id: String,
    val option: String,
    var isSelected: Boolean,
    var isCorrectAnswer: Boolean,
) {
    companion object {
        @JvmStatic
        @BindingAdapter("android:setCustomBackground")
        fun ConstraintLayout.setCustomBackground(item: QuizOptionsModel) {

        }

        @JvmStatic
        @BindingAdapter("android:setCustomBackgroundImage")
        fun ImageView.setCustomBackgroundImage(item: QuizOptionsModel) {
            val color = if (item.isSelected && !item.isCorrectAnswer) {
                ContextCompat.getDrawable(this.context, R.drawable.baseline_cancel_24)
            } else if (item.isCorrectAnswer) {
                ContextCompat.getDrawable(this.context, R.drawable.baseline_check_circle_24)
            } else {
                ContextCompat.getDrawable(this.context, R.color.white)
            }
            this.setImageDrawable(color)
        }
    }
}