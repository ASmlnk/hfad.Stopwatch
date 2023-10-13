package com.hfad.stopwatch.chapter_5_Stopwatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import com.hfad.stopwatch.R
import com.hfad.stopwatch.databinding.StopwatchActivityMainBinding

class MainActivityStopwatch : AppCompatActivity() {

    private lateinit var binding: StopwatchActivityMainBinding

    //lateinit var stopwatch: Chronometer
    var running = false  // Хронометр работает?
    var offset: Long = 0  //Базовое смещение

    //Добавление строк для ключей, используемых с Bundle
    val OFFSET_KEY = "offset"
    val RUNNING_KEY = "running"
    val BASE_KEY = "base"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.stopwatch_activity_main)

        binding = StopwatchActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Получение ссылки на секундомер
        //stopwatch = findViewById<Chronometer>(R.id.stopwatch)

        //Восстановление предыдущего состояния
        if (savedInstanceState != null) {
            offset = savedInstanceState.getLong(OFFSET_KEY)
            running = savedInstanceState.getBoolean(RUNNING_KEY)
            if (running) {
                binding.apply {
                    stopwatch.base = savedInstanceState.getLong(BASE_KEY)
                    stopwatch.start()
                }
            } else setBaseTime()
        }

        //Кнопка start запускает секундомер, если он не работал
        //val startButton = findViewById<Button>(R.id.start_button)
        binding.startButton.setOnClickListener {
            if (!running) {
                setBaseTime()
                binding.stopwatch.start()
                running = true
            }
        }

        //Кнопка pause останавливает секундомер, если он работал
        //val pauseButton = findViewById<Button>(R.id.pause_button)
        binding.pauseButton.setOnClickListener {
            if (running) {
                saveOffset()
                binding.stopwatch.stop()
                running = false
            }
        }

        //Кнопка reset обнуляет offset и базовое время
        // val resetButton = findViewById<Button>(R.id.reset_button)
        binding.resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }

    override fun onPause() {
        super.onPause()
        if (running) {
            saveOffset()
            binding.stopwatch.stop()
        }
    }

    override fun onResume() {
        super.onResume()
        if (running) {
            setBaseTime()
            binding.stopwatch.start()
            offset = 0
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putLong(OFFSET_KEY, offset)
        outState.putBoolean(RUNNING_KEY, running)
        outState.putLong(BASE_KEY, binding.stopwatch.base)
        super.onSaveInstanceState(outState)
    }

    //Обновляет время stopwatch.base
    fun setBaseTime() {
        binding.stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    //Сохраняет offset
    fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - binding.stopwatch.base
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, MainActivityStopwatch::class.java)
        }
    }
}