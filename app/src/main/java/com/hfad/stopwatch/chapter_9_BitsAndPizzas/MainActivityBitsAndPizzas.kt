package com.hfad.stopwatch.chapter_9_BitsAndPizzas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.stopwatch.R

class MainActivityBitsAndPizzas: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bits_and_pizzas_activity_main)
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, MainActivityBitsAndPizzas::class.java)
        }
    }
}