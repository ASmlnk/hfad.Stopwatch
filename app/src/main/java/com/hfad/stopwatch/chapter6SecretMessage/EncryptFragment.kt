package com.hfad.stopwatch.chapter6SecretMessage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hfad.stopwatch.R

class EncryptFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_encrypt, container, false)

        val message = EncryptFragmentArgs.fromBundle(requireArguments()).message
        val encryptView = view.findViewById<TextView>(R.id.encrypt_message)
        encryptView.text = message.reversed()

        return view
    }
}