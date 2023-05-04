package com.example.easyworks.activities

import android.app.*
import android.content.Intent
import android.os.Bundle
import android.content.Context
import android.os.Build
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.easyworkscrud.R

import com.example.easyworkscrud.databinding.ActivityServiceNotificationBinding
import java.util.*

class Service_Notification : AppCompatActivity()
{



    private lateinit var binding : ActivityServiceNotificationBinding




    override fun onCreate(savedInstanceState: Bundle?)
    {



        super.onCreate(savedInstanceState)
        binding = ActivityServiceNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        binding.notifysendbtn.setOnClickListener { scheduleNotification() }



    }



    private fun scheduleNotification()
    {
        val intent = Intent(applicationContext, Notification::class.java)
        val title = binding.notifyTitle.text.toString()
        val category = binding.notifyCat.text.toString()
        val notifyDescrip = binding.notifydesc.text.toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, category)
        intent.putExtra(descriptionExtra,notifyDescrip)

        val pendingIntent = PendingIntent.getBroadcast(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.SECOND, 5)

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        showAlert(title, category,notifyDescrip)
    }

    private fun showAlert(title: String, category: String, notifyDescrip: String)
    {

        AlertDialog.Builder(this)
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: " + title +
                        "\nMessage: " + category +"\nDescription: " + notifyDescrip )
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }





    private fun createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notif Channel"
            val desc = "A Description of the Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelID, name, importance)
            channel.description = desc
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        var backbtn = findViewById<ImageButton>(R.id.backbtn)
        backbtn.setOnClickListener({
            val intent = Intent(this, addService::class.java)
            startActivity(intent)
        })

    }
}

