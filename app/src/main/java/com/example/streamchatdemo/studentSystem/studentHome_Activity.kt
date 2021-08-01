package com.example.streamchatdemo.studentSystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.example.streamchatdemo.R
import com.example.streamchatdemo.muscleFlow.newLoginArgs
import com.example.streamchatdemo.muscleFlow.newLoginDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class studentHome_Activity : AppCompatActivity() {


    private val args: studentHome_ActivityArgs by navArgs()
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_home)


        navController = findNavController(R.id.student_navHostFragment)
        setUpNavigation()
    }

    private fun setUpNavigation(){
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.student_bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)
    }

}