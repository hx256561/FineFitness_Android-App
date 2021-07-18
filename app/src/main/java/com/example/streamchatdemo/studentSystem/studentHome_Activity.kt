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

        //----------------------------------------------------
        var action2=studentHome_fragmentDirections.actionStudentHomeFragmentSelf(args.chatUser)

        val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.toProfile -> {
                    var action1=studentHome_fragmentDirections.actionStudentHomeFragmentToStudentProfileFragment(args.chatUser)
                    navController.navigate(action1)
                    action2=studentProfile_fragmentDirections.actionStudentProfileFragmentToStudentHomeFragment(args.chatUser)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.toHome -> {
                    navController.navigate(action2)
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }
        //----------------------------------------------------

        studentBottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        var action1=studentEmpty_fragmentDirections.actionStudentEmptyFragmentToStudentHomeFragment(args.chatUser)
        navController.navigate(action1)



    }

}