package com.iut.app.android.fasttrack

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.iut.app.android.fasttrack.model.manager.ApiManager
import com.iut.app.android.fasttrack.model.repository.ScheduleRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        executeCall()


    }

    private fun executeCall() {
        GlobalScope.launch {
            ScheduleRepository.getCurrentSeason().asLiveData().observe(this@MainActivity, {
                Log.d("TAG", "executeCall: ${it.body()}")
            })
        }
    }
}


