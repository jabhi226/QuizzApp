package com.example.quizzapp.modules.quizModule.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentQuizzBinding
import com.example.quizzapp.modules.quizModule.models.ui.QuizOptionsModel
import com.example.quizzapp.modules.quizModule.ui.adapter.QuizOptionsAdapter
import com.example.quizzapp.modules.quizModule.viewModel.QuizViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class QuizzFragment : Fragment() {

    private var _binding: FragmentQuizzBinding? = null
    private val binidng get() = _binding!!

    private val viewModel by viewModels<QuizViewModel>()
    private val optionsAdapter = QuizOptionsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentQuizzBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binidng.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeData()
        binidng.tvNext.setOnClickListener {
            viewModel.getNextQuestion()
        }
    }

    private fun initRecyclerView() {
        binidng.rvOptions.apply {
            this.adapter = optionsAdapter
            this.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeData() {
        CoroutineScope(Dispatchers.Main).launch {
//            viewModel.currentQuestionData.collectLatest {
//                binidng.quizModel = it
//                optionsAdapter.submitList(it.options)
//            }
        }
    }

}