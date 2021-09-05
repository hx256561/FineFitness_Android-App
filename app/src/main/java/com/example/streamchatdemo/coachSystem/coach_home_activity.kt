package com.example.streamchatdemo.coachSystem

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.streamchatdemo.R

class coach_home_activity: AppCompatActivity()  {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coach_home_activity)

        navController = findNavController(R.id.coach_navHostFragment)
    }
}