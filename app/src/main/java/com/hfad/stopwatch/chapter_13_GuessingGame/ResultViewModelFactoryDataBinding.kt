package com.hfad.stopwatch.chapter_13_GuessingGame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactoryDataBinding(private val finalResult: String)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultViewModelDataBinding::class.java))
            return ResultViewModelDataBinding(finalResult) as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}