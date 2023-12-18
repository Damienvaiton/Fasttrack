package com.iut.app.android.fasttrack.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import com.iut.app.android.fasttrack.view.Fragements.Shop.Shop
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        executeCall()
        replaceFragment(Home())


        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> replaceFragment(Home())
                R.id.calendar -> replaceFragment(Schedule())
                R.id.stat -> replaceFragment(Ranking())
                R.id.shop -> replaceFragment(Shop())
                R.id.account -> connectedChooseFragement()

                else -> {


                }

            }


            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun connectedChooseFragement() {
        if (CacheDataSource.connected == true) {
            replaceFragment(Account())
        } else {
            replaceFragment(Login())
        }
    }


    private fun executeCall() {
        MainScope().launch {
            ScheduleRepository.getCurrentSeason().collect {
                Timber.tag("MainActivity").d("Current season : %s", it.body())
            }
            ScheduleRepository.getNextRace().collect {
                Timber.tag("MainActivity").d("Next Race : %s", it.body())
            }
            DriverRankingRepository.getCurrentDriverRanking().collect {
                Timber.tag("MainActivity").d("Current driver ranking : %s", it.body())
            }
            ConstructorRankingRepository.getCurrentConstructorRanking().collect {
                Timber.tag("MainActivity").d("Current constructor ranking : %s", it.body())
            }


        }
    }
}

