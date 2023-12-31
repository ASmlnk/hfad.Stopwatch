package com.hfad.stopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.stopwatch.chapter_11_GuessingGame.MainActivityGuessingGame
import com.hfad.stopwatch.chapter_13_GuessingGame.MainActivityGuessingGameDataBinding
import com.hfad.stopwatch.chapter_14_Tasks.MainActivityTasks
import com.hfad.stopwatch.chapter_18_TemteratureConverter.ui.MainActivityTemperatureConverter
import com.hfad.stopwatch.chapter_19_GuessingGame.MainActivityGuessingGameCompose
import com.hfad.stopwatch.chapter_5_Stopwatch.MainActivityStopwatch
import com.hfad.stopwatch.chapter_6_SecretMessage.MainActivitySecretMessage
import com.hfad.stopwatch.chapter_8_CatChat.MainActivityCatChat
import com.hfad.stopwatch.chapter_9_BitsAndPizzas.MainActivityBitsAndPizzas
import com.hfad.stopwatch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            chapter5.setOnClickListener {
                val intent = MainActivityStopwatch.newIntent(this@MainActivity)
                startActivity(intent)
            }
            chapter6.setOnClickListener {
                val intent = MainActivitySecretMessage.newIntent(this@MainActivity)
                startActivity(intent)
            }
            chapter8.setOnClickListener {
                val intent = MainActivityCatChat.newIntent(this@MainActivity)
                startActivity(intent)
            }
            chapter9.setOnClickListener {
                val intent = MainActivityBitsAndPizzas.newIntent(this@MainActivity)
                startActivity(intent)
            }
            chapter11.setOnClickListener {
                val intent = MainActivityGuessingGame.newIntent(this@MainActivity)
                startActivity(intent)
            }
            chapter13.setOnClickListener {
                val intent = MainActivityGuessingGameDataBinding.newIntent(this@MainActivity)
                startActivity(intent)
            }
            chapter14.setOnClickListener {
                val intent = MainActivityTasks.newIntent(this@MainActivity)
                startActivity(intent)
            }
            chapter18.setOnClickListener {
                val intent = MainActivityTemperatureConverter.newIntent(this@MainActivity)
                startActivity(intent)
            }
            chapter19.setOnClickListener {
                val intent = MainActivityGuessingGameCompose.newIntent(this@MainActivity)
                startActivity(intent)
            }
        }
    }
}