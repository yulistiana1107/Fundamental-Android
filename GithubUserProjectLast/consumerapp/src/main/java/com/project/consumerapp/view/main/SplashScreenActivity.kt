package com.project.consumerapp.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.project.consumerapp.R

class SplashScreenActivity : AppCompatActivity() {
    lateinit var yulishandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        yulishandler = Handler(mainLooper)
        yulishandler.postDelayed({

            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}