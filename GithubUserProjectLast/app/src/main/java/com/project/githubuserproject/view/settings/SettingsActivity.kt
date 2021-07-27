package com.project.githubuserproject.view.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import com.project.githubuserproject.R
import com.project.githubuserproject.view.settings.AlarmHelper.yulisCancelAlarm
import com.project.githubuserproject.view.settings.AlarmHelper.yulisRepeatingAlarm
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        private const val YULIS_REMINDER = "yulis_pref"
    }
    private lateinit var yulispreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setReminderMode(this)

        val changeLanguage: TextView = findViewById(R.id.textView3)
        changeLanguage.setOnClickListener(this)
    }

    private fun setReminderMode(context: Context) {
        yulispreference = getSharedPreferences(YULIS_REMINDER, Context.MODE_PRIVATE)
        swDaily.apply {
            isChecked = yulispreference.getBoolean(YULIS_REMINDER, false)
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    yulisRepeatingAlarm(context,resources.getString(R.string.daily_message))
                    yulispreference.edit { putBoolean(YULIS_REMINDER, true) }
                    Toast.makeText(context,
                            resources.getString(R.string.reminder_activated),
                            Toast.LENGTH_LONG).show()
                } else {
                    yulisCancelAlarm(context)
                    yulispreference.edit { putBoolean(YULIS_REMINDER, false) }
                    Toast.makeText(context,
                            resources.getString(R.string.reminder_deactivated),
                            Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onClick (v: View?) {
        when (v?.id) {
            R.id.textView3 -> {
                val moveIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(moveIntent)
            }
        }
    }
}