package com.example.quizzapp.modules.quizModule.ui.dialog

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.quizzapp.R

class ConfirmDialog(val showCorrectAnswers: () -> Unit) : DialogFragment(R.layout.dialog_confirm) {

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogFragmentTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_submit).setOnClickListener {
            showCorrectAnswers()
            this.dismiss()
        }
        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            this.dismiss()
        }
    }
}