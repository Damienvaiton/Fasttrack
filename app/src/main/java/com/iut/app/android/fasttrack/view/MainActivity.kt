package com.iut.app.android.fasttrack.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.iut.app.android.fasttrack.R
import com.iut.app.android.fasttrack.view.Fragements.Profil.Login
import com.iut.app.android.fasttrack.view.Fragements.Profil.Account
import com.iut.app.android.fasttrack.view.Fragements.Rankings.Ranking
import com.iut.app.android.fasttrack.view.Fragements.Schedule.Schedule
import com.iut.app.android.fasttrack.databinding.ActivityMainBinding
import com.iut.app.android.fasttrack.model.dataclass.CacheDataSource
import com.iut.app.android.fasttrack.model.repository.ConstructorRankingRepository
import com.iut.app.android.fasttrack.model.repository.DriverRankingRepository
import com.iut.app.android.fasttrack.model.repository.ScheduleRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    val scope = CoroutineScope(Dispatchers.IO + CoroutineName("MainScope"))

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
                R.id.account -> connectedChooseFragement()

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

    private fun connectedChooseFragement(){
        if(CacheDataSource.connected == true){
            replaceFragment(Account())
        }else{
            replaceFragment(Login())
        }
    }


    private fun executeCall() {
        MainScope().launch {
            ScheduleRepository.getCurrentSeason().collect {
                Log.d("MainActivity", "Current season : ${it.body()}")
            }
            ScheduleRepository.getNextRace().collect{
                Log.d("MainActivity", "Next Race : ${it.body()}")
            }
            DriverRankingRepository.getCurrentDriverRanking().collect {
                Log.d("MainActivity", "Current driver ranking : ${it.body()}")
            }
            ConstructorRankingRepository.getCurrentConstructorRanking().collect {
                Log.d("MainActivity", "Current constructor ranking : ${it.body()}")
            }


        }
    }
}

