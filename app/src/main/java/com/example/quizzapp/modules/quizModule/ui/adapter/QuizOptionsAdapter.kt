package com.example.quizzapp.modules.quizModule.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzapp.databinding.ItemOptionsBinding
import com.example.quizzapp.modules.quizModule.models.ui.QuizOptionsModel
import com.example.quizzapp.modules.quizModule.ui.listeners.EventListener

class QuizOptionsAdapter(val onOptionSelected: (QuizOptionsModel) -> Unit) :
    ListAdapter<QuizOptionsModel, RecyclerView.ViewHolder>(object :
        DiffUtil.ItemCallback<QuizOptionsModel>() {
        override fun areItemsTheSame(
            oldItem: QuizOptionsModel,
            newItem: QuizOptionsModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: QuizOptionsModel,
            newItem: QuizOptionsModel
        ): Boolean {
            val r = (oldItem.id == newItem.id
                    && oldItem.oldIsSelected == newItem.isSelected
                    && oldItem.bg == newItem.bg)
            return r
        }

    }) {


    inner class OptionViewHolder(private val binding: ItemOptionsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(item: QuizOptionsModel?, eventListener: EventListener) {
            binding.quizOptionsModel = item
            binding.eventListener = eventListener
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OptionViewHolder(
            ItemOptionsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p: Int) {
        when (holder) {
            is OptionViewHolder -> {
                holder.bindData(getItem(p), EventListener {
                    onOptionSelected(it)
                })
            }
        }
    }
}