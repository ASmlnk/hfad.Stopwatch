package com.hfad.stopwatch.chapter_8_CatChat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.stopwatch.R

class MainActivityCatChat: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_chat_activity_main)
    }

    companion object {
        fun newIntent(packageContext: Context) : Intent {
            return Intent(packageContext, MainActivityCatChat::class.java)
        }
    }
}