package com.hfad.stopwatch.chapter_14_Tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditTextViewModelFactory(
    private val taskId: Long,
    private val dao: TaskDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditTaskViewModel::class.java)) {
            return EditTaskViewModel(taskId,dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}