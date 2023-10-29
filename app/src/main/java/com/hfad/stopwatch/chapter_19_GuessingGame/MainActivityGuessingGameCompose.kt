package com.hfad.stopwatch.chapter_19_GuessingGame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.stopwatch.R

class MainActivityGuessingGameCompose: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guessing_game_activity_main_19)
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, MainActivityGuessingGameCompose::class.java)
        }
    }
}