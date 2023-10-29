package com.hfad.stopwatch.chapter_19_GuessingGame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactoryCompose(private val finalResult: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModelCompose::class.java))
            return ResultViewModelCompose(finalResult) as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}