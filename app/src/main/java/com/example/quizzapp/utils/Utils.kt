package com.example.quizzapp.utils

import android.content.Context
import android.widget.Toast

object Utils {

    fun showToast(c: Context, text: String){
        Toast.makeText(c, text, Toast.LENGTH_SHORT).show()
    }
}