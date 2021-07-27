package com.project.githubuserproject.view.settings

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.project.githubuserproject.R
import com.project.githubuserproject.view.settings.AlarmHelper.yulisShowAlarm

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val YULIS_CHANNEL_ID = "Channel_1"
        const val YULIS_CHANNEL_NAME = "Alarm Manager Channel"
        const val YULIS_NOTIFICATION_ID = 1
    }

    override fun onReceive(context: Context, intent: Intent) {
        val title = context.resources.getString(R.string.app_name)
        val message = context.resources.getString(R.string.daily_message)
        yulisShowAlarm(context, title, message)
    }
}