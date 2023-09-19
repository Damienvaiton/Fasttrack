package com.iut.app.android.fasttrack

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_page)

        val signUpFragment : Button = findViewById(R.id.sign_up_btn)
        val signInFragment : Button = findViewById(R.id.sign_in_btn)

        signUpFragment.setOnClickListener {
            setContentView(R.layout.sign_up_page)
        }
    }
}