package com.example.quizzapp.modules.quizModule.ui.fragment

import android.content.Context
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.graphics.ColorFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzapp.R
import com.example.quizzapp.databinding.FragmentQuizzBinding
import com.example.quizzapp.modules.core.utils.Utils
import com.example.quizzapp.modules.quizModule.models.ui.QuizModel
import com.example.quizzapp.modules.quizModule.ui.adapter.QuizOptionsAdapter
import com.example.quizzapp.modules.quizModule.ui.dialog.ConfirmDialog
import com.example.quizzapp.modules.quizModule.viewModel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class QuizzFragment : Fragment() {

    private var _binding: FragmentQuizzBinding? = null
    private val binding get() = _binding!!
//    private val viewModel by viewModels<QuizViewModel>()
    private lateinit var viewModel: QuizViewModel

    @Inject
    lateinit var coroutineExceptionHandler: CoroutineExceptionHandler

    private val optionsAdapter = QuizOptionsAdapter { selectedOptionsModel ->
        viewModel.updateSelectedOptions(selectedOptionsModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        println("---> Fragement-> onCreateView")
        _binding = DataBindingUtil.inflate(
            LayoutInflater.from(requireContext()),
            R.layout.fragment_quizz,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
        println("---> Fragement-> onDestroy")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("---> Fragement-> onViewCreated")
        initRecyclerView()
        observeData()
    }

    private fun initRecyclerView() {
        viewModel = ViewModelProvider(requireActivity())[QuizViewModel::class.java]

        binding.rvOptions.apply {
            this.adapter = optionsAdapter
            this.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeData() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        CoroutineScope(Dispatchers.Main).launch(coroutineExceptionHandler) {
            viewModel.currentQuestion.collect {
                if (it == null) {
                    return@collect
                }
                binding.quizModel = it
                optionsAdapter.submitList(it.options)
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.uiEvent.collectLatest {
                if (it == null) return@collectLatest
                when(it){
                    101 -> {
                        Utils.showToast(requireContext(), "Final Question")
                        val dialog = ConfirmDialog{
                            viewModel.showCorrectAnswers()
                        }
                        dialog.show(requireActivity().supportFragmentManager, null)
                    }
                }
            }
        }
    }

//     test
    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("---> Fragement-> onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("---> Fragement-> onCreate")
    }

    override fun onStart() {
        super.onStart()
        println("---> Fragement-> onStart")
    }

    override fun onResume() {
        super.onResume()
        println("---> Fragement-> onResume")
    }

    override fun onPause() {
        super.onPause()
        println("---> Fragement-> onPause")
    }

    override fun onStop() {
        super.onStop()
        println("---> Fragement-> onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("---> Fragement-> onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        println("---> Fragement-> onDetach")
    }

    companion object {
        fun createInstance(): QuizzFragment {
            return QuizzFragment()
        }

        @JvmStatic
        @BindingAdapter("android:setP1Background")
        fun setP1Background(iv: ImageView, s: String) {
            if (s.toInt() >= 25) {
                iv.setColorFilter(ContextCompat.getColor(iv.context, R.color.green))
            } else {
                iv.setColorFilter(ContextCompat.getColor(iv.context, R.color.blue))
            }
        }

        @JvmStatic
        @BindingAdapter("android:setP2Background")
        fun setP2Background(iv: ImageView, s: String) {
            println("=[=========> ${s.toInt() >= 50}")
            if (s.toInt() >= 50) {
                iv.setColorFilter(ContextCompat.getColor(iv.context, R.color.green))
            } else {
                iv.setColorFilter(ContextCompat.getColor(iv.context, R.color.blue))
            }
        }

        @JvmStatic
        @BindingAdapter("android:setP3Background")
        fun setP3Background(iv: ImageView, s: String) {
            println("=[=========> ${s.toInt() >= 75}")
            if (s.toInt() >= 75) {
                iv.setColorFilter(ContextCompat.getColor(iv.context, R.color.green))
            } else {
                iv.setColorFilter(ContextCompat.getColor(iv.context, R.color.blue))
            }
        }
    }


}
