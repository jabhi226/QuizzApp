package com.example.quizzapp.modules.quizModule.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivityMainBinding
import com.example.quizzapp.modules.quizModule.ui.fragment.QuizzFragment

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        addNewFragment(QuizzFragment())
    }

    private fun addNewFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(binding.mainActivityFragments.id, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }
}