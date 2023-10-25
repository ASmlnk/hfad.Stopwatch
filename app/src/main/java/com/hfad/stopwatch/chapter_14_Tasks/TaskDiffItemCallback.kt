package com.hfad.stopwatch.chapter_14_Tasks

import androidx.recyclerview.widget.DiffUtil

class TaskDiffItemCallback : DiffUtil.ItemCallback<Tasks>() {
    override fun areItemsTheSame(oldItem: Tasks, newItem: Tasks) =
        (oldItem.tasksId == newItem.tasksId)

    override fun areContentsTheSame(oldItem: Tasks, newItem: Tasks) = (oldItem == newItem)
}