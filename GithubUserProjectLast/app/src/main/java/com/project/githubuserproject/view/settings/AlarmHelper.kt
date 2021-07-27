package com.project.githubuserproject.view.settings

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.provider.AlarmClock
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.project.githubuserproject.R
import com.project.githubuserproject.view.main.MainActivity
import java.util.*

object AlarmHelper {
    fun yulisRepeatingAlarm(context: Context, message: String) {
        val alarmManager =
                context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, message)
            action.equals(Intent.ACTION_BOOT_COMPLETED)
        }
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }
        val pendingIntent =
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent)
    }

    fun yulisCancelAlarm(context: Context) {
        val alarmManager =
                context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }

    fun yulisShowAlarm(context: Context, title: String, message: String) {
        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT
        val pendingIntent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0)
        val notificationManagerCompat =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val vibrationPattern = longArrayOf(500, 500, 500, 500, 500)
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, AlarmReceiver.YULIS_CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_notification_white)
                .setContentTitle(title)
                .setContentText(message)
                .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                .setVibrate(vibrationPattern)
                .setSound(alarmSound)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    AlarmReceiver.YULIS_CHANNEL_ID,
                    AlarmReceiver.YULIS_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT)
            channel.apply {
                enableVibration(true)
                this.vibrationPattern = vibrationPattern
            }
            builder.setChannelId(AlarmReceiver.YULIS_CHANNEL_ID)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManagerCompat.notify(AlarmReceiver.YULIS_NOTIFICATION_ID, notification)
    }
}