package com.example.quizzapp.modules.quizModule.models.ui

import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.quizzapp.R

data class QuizOptionsModel(
    val id: String,
    val option: String,
    var oldIsSelected: Boolean,
    var isSelected: Boolean,
    var isCorrectAnswer: Boolean,
    var bg: OptionBackground
) {
    companion object {
        @JvmStatic
        @BindingAdapter("android:setCustomBackground")
        fun ConstraintLayout.setCustomBackground(item: QuizOptionsModel) {
            val iv = this.findViewById<ImageView>(R.id.iv_true_false)
            val (color, icon) = when (item.bg) {
                OptionBackground.RED -> {
                    (ContextCompat.getDrawable(this.context, R.drawable.bg_red_round)
                            to ContextCompat.getDrawable(
                        this.context,
                        R.drawable.baseline_cancel_24
                    ))
                }

                OptionBackground.GREEN -> {
                    (ContextCompat.getDrawable(this.context, R.drawable.bg_green_round)
                            to ContextCompat.getDrawable(
                        this.context,
                        R.drawable.baseline_check_circle_24
                    ))
                }

                OptionBackground.BLUE -> {
                    (ContextCompat.getDrawable(this.context, R.drawable.bg_blue_round)
                            to ContextCompat.getDrawable(this.context, R.color.white))
                }

                OptionBackground.WHITE -> {
                    (ContextCompat.getDrawable(this.context, R.drawable.bg_white_round)
                            to ContextCompat.getDrawable(this.context, R.color.white))
                }
            }
            this.background = color
            iv.setImageDrawable(icon)
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