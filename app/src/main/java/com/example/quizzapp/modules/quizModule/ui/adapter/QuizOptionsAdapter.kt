package com.example.quizzapp.modules.quizModule.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzapp.databinding.ItemOptionsBinding
import com.example.quizzapp.modules.quizModule.models.ui.QuizOptionsModel

class QuizOptionsAdapter : ListAdapter<QuizOptionsModel, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<QuizOptionsModel>() {
    override fun areItemsTheSame(oldItem: QuizOptionsModel, newItem: QuizOptionsModel): Boolean {
        return oldItem.position == newItem.position
    }

    override fun areContentsTheSame(oldItem: QuizOptionsModel, newItem: QuizOptionsModel): Boolean {
        return oldItem == newItem
    }

}) {

    inner class OptionViewHolder(private val binding: ItemOptionsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(item: QuizOptionsModel?) {
            binding.quizOptionsModel = item
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OptionViewHolder(ItemOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OptionViewHolder -> {
                holder.bindData(getItem(position))
            }
        }
    }
}