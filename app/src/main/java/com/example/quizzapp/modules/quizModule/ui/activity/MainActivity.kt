package com.example.quizzapp.modules.quizModule.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivityMainBinding
import com.example.quizzapp.modules.quizModule.ui.fragment.QuizzFragment
import com.example.quizzapp.modules.quizModule.viewModel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

//    private val viewModel by viewModels<QuizViewModel>()
    private lateinit var viewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("---> onCreate")
        _binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initView()
        observeData()
        addNewFragment(QuizzFragment())
    }

    private fun initView() {
        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]
    }

    private fun observeData() {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.currentFragment.collectLatest {
//                println("---===> ${it?.javaClass?.simpleName}")
//                addNewFragment(it)
            }
        }
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
        when(f){
            is QuizzFragment -> {
                viewModel.getPreviousQuestion()
            } else -> {
                super.onBackPressed()
            }
        }
    }

    // test


    override fun onStart() {
        super.onStart()
        println("---> onStart")
    }

    override fun onResume() {
        super.onResume()
        println("---> onResume")
    }

    override fun onPause() {
        super.onPause()
        println("---> onPause")
    }

    override fun onStop() {
        super.onStop()
        println("---> onStop")
    }

    override fun onRestart() {
        super.onRestart()
        println("---> onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("---> onDestroy")
    }
}