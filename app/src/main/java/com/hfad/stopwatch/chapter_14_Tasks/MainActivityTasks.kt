package com.hfad.stopwatch.chapter_14_Tasks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.stopwatch.R

class MainActivityTasks: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_activity_main)
    }
    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, MainActivityTasks::class.java)
        }
    }
}