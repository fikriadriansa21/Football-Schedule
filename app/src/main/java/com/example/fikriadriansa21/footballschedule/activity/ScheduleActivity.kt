package com.example.fikriadriansa21.footballschedule.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.fikriadriansa21.footballschedule.R

class ScheduleActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID_LEAGUE = "intent get id"
    }

    private lateinit var idLeague: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)


    }
}
