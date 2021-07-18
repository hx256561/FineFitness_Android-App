package com.example.streamchatdemo.studentSystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
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
        var studentBottomNav=findViewById<BottomNavigationView>(R.id.student_bottomNavigationView)
        studentBottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)



        var action1=studentEmpty_fragmentDirections.actionStudentEmptyFragmentToStudentHomeFragment(args.chatUser)
        navController.navigate(action1)



    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.toProfile -> {
                val action= studentHome_fragmentDirections.actionStudentHomeFragmentToStudentProfileFragment(args.chatUser)
                navController.navigate(action)
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
}