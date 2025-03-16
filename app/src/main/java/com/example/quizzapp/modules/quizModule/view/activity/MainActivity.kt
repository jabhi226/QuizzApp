package com.example.quizzapp.modules.quizModule.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivityMainBinding
import com.example.quizzapp.modules.quizModule.screens.QuizV2Fragment
import com.example.quizzapp.modules.quizModule.viewModel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]
        setContentView(binding.root)
        addNewFragment(QuizV2Fragment())
    }

    private fun addNewFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(binding.mainActivityFragments.id, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        val f = supportFragmentManager.findFragmentById(R.id.main_activity_fragments)
        when (f) {
            is QuizV2Fragment -> {
                viewModel.getPreviousQuestion()
            }

            else -> {
                super.onBackPressed()
            }
        }
    }

}