package com.iut.app.android.fasttrack

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.iut.app.android.fasttrack.databinding.ActivityMainBinding
import com.iut.app.android.fasttrack.model.manager.ApiManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executeCall()
        replaceFragment(Home())


        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.home -> replaceFragment(Home())
                R.id.calendar -> replaceFragment(Schedule())
                R.id.stat -> replaceFragment(Ranking())
                R.id.shop -> replaceFragment(Shop())
                R.id.account -> replaceFragment(Account())

                else ->{




                }

            }

            true
        }
    }

    private fun replaceFragment(fragment : Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
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

