package com.hfad.stopwatch.chapter_6_SecretMessage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.stopwatch.R

class MainActivitySecretMessage: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secret_message_activity_main)
    }
    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, MainActivitySecretMessage::class.java)
        }
    }
}