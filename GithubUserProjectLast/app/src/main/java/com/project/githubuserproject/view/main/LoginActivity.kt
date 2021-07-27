package com.project.githubuserproject.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.project.githubuserproject.R

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var yulisemail: EditText
    private lateinit var yulispassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        yulisemail = findViewById(R.id.inputemail)
        yulispassword = findViewById(R.id.inputpassword)

        val login: Button = findViewById(R.id.btnLogin)
        login.setOnClickListener(this)

        val forgot: TextView = findViewById(R.id.forgotPassword)
        forgot.setOnClickListener(this)

        val register: TextView = findViewById(R.id.gotoRegister)
        register.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnLogin ->{
                val inputEmail = yulisemail.text.toString().trim()
                val inputPassword = yulispassword.text.toString().trim()

                var isEmptyFields = false

                if (inputEmail.isEmpty()) {
                    isEmptyFields = true
                    yulisemail.error = "Harap Isi Email/Username Anda"
                }
                if (inputPassword.isEmpty()) {
                    isEmptyFields = true
                    yulispassword.error = "Harap Isi Password Anda"
                }

                if (!isEmptyFields) {
                    val moveIntent= Intent(this, MainActivity::class.java)
                    startActivity(moveIntent)
                    finish()
                }
            }
            R.id.forgotPassword ->{
                Toast.makeText(this, "Forgot Password", Toast.LENGTH_SHORT).show()
            }
            R.id.gotoRegister -> {
                Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show()
            }
        }
    }
}