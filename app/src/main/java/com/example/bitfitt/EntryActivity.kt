package com.example.bitfitt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryActivity : AppCompatActivity() {

    private lateinit var dateEv: EditText
    private lateinit var hoursEv: EditText
    private lateinit var commentsEv: EditText
    private lateinit var ratingEv: EditText
    private lateinit var subButton: Button
    private lateinit var communicator: Communicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        dateEv = findViewById(R.id.dateInput)
        hoursEv = findViewById(R.id.hoursInput)
        commentsEv = findViewById(R.id.commentsInput)
        ratingEv = findViewById(R.id.ratingInput)
        subButton = findViewById(R.id.addNewDateButton)

//        communicator = activity as Communicator


        subButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            val hours1 = hoursEv.text.toString().toInt()
            val date1 = dateEv.text.toString()
            val comments1 = commentsEv.text.toString()
            val rating1 = ratingEv.text.toString().toInt()
            val day = DAY(date1, hours1, comments1, rating1)
            lifecycleScope.launch(Dispatchers.IO) {
                (application as BitFitApplication).db.dayDao().insert(
                    DayEntity(
                        date = day.date,
                        hours = day.hours,
                        comments = day.comments,
                        rating = day.rating
                    )
                )
//            val day = DAY(dateEv.text.toString(), hoursEv.text.toString().toInt(), commentsEv.text.toString(), ratingEv.text.toString().toInt())
        }
            intent.putExtra("extra_entry", day)
            this.startActivity(intent)
    }
}}

