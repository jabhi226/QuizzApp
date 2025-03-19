package com.example.quizzapp.modules.quizModule.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentQuizzBinding
import com.example.quizzapp.utils.Utils
import com.example.quizzapp.modules.quizModule.view.adapter.QuizOptionsAdapter
import com.example.quizzapp.modules.quizModule.view.dialog.ConfirmDialog
import com.example.quizzapp.modules.quizModule.viewModel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@Deprecated("Migrated to Jetpack Compose")
@AndroidEntryPoint
class QuizzFragment : Fragment() {

    private var _binding: FragmentQuizzBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<QuizViewModel>()

    @Inject
    lateinit var coroutineExceptionHandler: CoroutineExceptionHandler

    private val optionsAdapter = QuizOptionsAdapter { selectedOptionsModel ->
//        viewModel.updateSelectedOptions(selectedOptionsModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quizz,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeData()
    }

    private fun initView() {
        binding.apply {
            this.viewModel = viewModel
            this.lifecycleOwner = this@QuizzFragment
        }
        binding.rvOptions.apply {
            this.adapter = optionsAdapter
            this.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeData() {
        CoroutineScope(Dispatchers.Main).launch(coroutineExceptionHandler) {
//            viewModel.currentQuestion.collect {
//                if (it == null) {
//                    return@collect
//                }
//                binding.quizModel = it
//                optionsAdapter.submitList(it.options)
//            }
        }

        CoroutineScope(Dispatchers.Main).launch {
//            viewModel.uiEvent.collectLatest {
//                if (it == null) return@collectLatest
//                when (it) {
//                    101 -> {
//                        Utils.showToast(requireContext(), "Final Question")
//                        val dialog = ConfirmDialog {
//                            viewModel.showCorrectAnswers()
//                        }
//                        dialog.show(requireActivity().supportFragmentManager, null)
//                    }
//                }
//            }
        }
    }

}
