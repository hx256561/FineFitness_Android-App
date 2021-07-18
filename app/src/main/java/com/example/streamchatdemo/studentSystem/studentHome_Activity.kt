package com.example.streamchatdemo.studentSystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        var action_1=studentHome_fragmentDirections.actionStudentHomeFragmentToStudentProfileFragment(args.chatUser)
        var action_2=studentHome_fragmentDirections.actionStudentHomeFragmentSelf(args.chatUser)

        val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.toProfile -> {
                    navController.navigate(action_1)
                    action_2=studentProfile_fragmentDirections.actionStudentProfileFragmentToStudentHomeFragment(args.chatUser)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.toHome -> {
                    navController.navigate(action_2)
                    return@OnNavigationItemSelectedListener true
                }

            }
            false
        }
        //----------------------------------------------------

        studentBottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        studentBottomNav.setOnNavigationItemReselectedListener(BottomNavigationView.OnNavigationItemReselectedListener {
            Log.e("bottomMenuView:", it.itemId.toString())
        })

        var action1=studentEmpty_fragmentDirections.actionStudentEmptyFragmentToStudentHomeFragment(args.chatUser)
        navController.navigate(action1)



    }

}