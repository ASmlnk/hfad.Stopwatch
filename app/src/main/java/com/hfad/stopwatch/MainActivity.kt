package com.hfad.stopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.stopwatch.chapter5Stopwatch.MainActivityStopwatch
import com.hfad.stopwatch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            chapter5.setOnClickListener {
                val intent =MainActivityStopwatch.newIntent(this@MainActivity)
                startActivity(intent)
            }
        }

    }

}