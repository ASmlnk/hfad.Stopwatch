package com.hfad.stopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        }
    }
}