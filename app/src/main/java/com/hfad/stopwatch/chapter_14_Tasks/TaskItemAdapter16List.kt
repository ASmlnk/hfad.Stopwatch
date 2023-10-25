package com.hfad.stopwatch.chapter_14_Tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.stopwatch.R
import com.hfad.stopwatch.databinding.TaskItemBinding

class TaskItemAdapter16List(val clickListener: (taskId: Long) -> Unit) :
    ListAdapter<Tasks, TaskItemAdapter16List.TaskItemViewHolder16>(TaskDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder16 =
        TaskItemViewHolder16.inflaterFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder16, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }


    class TaskItemViewHolder16 (val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflaterFrom(parent: ViewGroup): TaskItemViewHolder16 {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false )
                return TaskItemViewHolder16(binding)
            }
        }

        fun bind(item: Tasks, clickListener: (taskId: Long) -> Unit) {
            binding.task = item
            binding.root.setOnClickListener{
                clickListener(item.tasksId)
            }
        }
    }
}