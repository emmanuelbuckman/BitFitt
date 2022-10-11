package com.example.bitfitt

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val logFragment: Fragment = LogFragment()
        val dashboardFragment: Fragment = DashboardFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.logTab -> fragment = logFragment
                R.id.dashboardTab -> fragment = dashboardFragment
            }
            replaceFragment(fragment)
            true
        }

        val fragmentA = LogFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragmentA).commit()


        bottomNavigationView.selectedItemId = R.id.logTab

        submitButton = findViewById(R.id.addNewDateButton)
        submitButton.setOnClickListener {
            val intent = Intent(this, EntryActivity::class.java)
            this.startActivity(intent)
        }

    }

    private fun replaceFragment(newFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, newFragment)
        fragmentTransaction.commit()
    }
/*
    var builder = NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
        .setContentTitle("Missing Data")
        .setContentText("Please fill in your sleep data for today!")
        .setStyle(NotificationCompat.BigTextStyle()
            .bigText("Please fill in your sleep data for today!"))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel_name"
            val descriptionText = "channel_description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    val intentt = Intent(this, AlertDetails::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val builder = NotificationCompat.Builder(this, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
        .setContentTitle("My notification")
        .setContentText("Hello World!")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        // Set the intent that will fire when the user taps the notification
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    with(NotificationManagerCompat.from(this)) {
        // notificationId is a unique int for each notification that you must define
        notify(notificationId, builder.build())
    }
*/
}
