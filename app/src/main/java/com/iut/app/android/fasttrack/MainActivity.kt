package com.iut.app.android.fasttrack

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.iut.app.android.fasttrack.model.manager.ApiManager
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        executeCall()

    }

    private fun executeCall() {
        GlobalScope.launch {
            try {
                val response = ApiManager.apiService.getCurrentSeason()
                if (response.isSuccessful && response.body() != null) {
                    Log.e("Testapi", "response : ${response.body()}")

                } else {
                    Log.e("Testapi", "response invalide")
                }

                } catch (e: Exception) {
                    Log.e("Testapi", "erreur : ${e.message}")
            }






        }
    }
}


