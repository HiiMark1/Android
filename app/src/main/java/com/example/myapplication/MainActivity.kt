package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity() {

    private var service: NotificationService? = null
    private var alarmManager: AlarmManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        service = NotificationService()

        findViewById<Button>(R.id.start).setOnClickListener{
            val intent = Intent(this, NotificationService::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                this,
                111,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, findViewById<EditText>(R.id.set_hours).text.toString().toInt())
            calendar.set(Calendar.MINUTE, findViewById<EditText>(R.id.set_minutes).text.toString().toInt())

            if (calendar.timeInMillis - System.currentTimeMillis() < 0) {
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
            }

            alarmManager?.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )

            Snackbar.make(
                findViewById(android.R.id.content),
                "Будильник успешно установлен",
                Snackbar.LENGTH_LONG
            ).show()
        }
        findViewById<Button>(R.id.cancel).setOnClickListener {
            val intent = Intent(this, NotificationService::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                this,
                111,
                intent,
                0
            )
            alarmManager?.cancel(pendingIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service = null
    }
}