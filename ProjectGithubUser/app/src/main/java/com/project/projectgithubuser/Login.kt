package com.project.projectgithubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity(), View.OnClickListener {
    private lateinit var email: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.inputemail)
        password = findViewById(R.id.inputpassword)

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
                val inputEmail = email.text.toString().trim()
                val inputPassword = password.text.toString().trim()

                var isEmptyFields = false

                if (inputEmail.isEmpty()) {
                    isEmptyFields = true
                    email.error = "Harap Isi Email/Username Anda"
                }
                if (inputPassword.isEmpty()) {
                    isEmptyFields = true
                    password.error = "Harap Isi Password Anda"
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