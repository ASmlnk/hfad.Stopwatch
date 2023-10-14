package com.hfad.stopwatch.chapter_11_GuessingGame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.stopwatch.R

class MainActivityGuessingGame: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guessing_game_activity_main)
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, MainActivityGuessingGame::class.java)
        }
    }
}