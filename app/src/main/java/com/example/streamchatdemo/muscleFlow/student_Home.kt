package com.example.streamchatdemo.muscleFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.streamchatdemo.R
import com.example.streamchatdemo.databinding.StudentHomeBinding
import com.example.streamchatdemo.ui.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class student_Home: Fragment() {

    private var _binding: StudentHomeBinding?=null
    private val binding get()=_binding!!
    
    //------------------------------------
    /*
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.toAsk -> {
                val action=student_HomeDirections.actionStudentHomeToStudentAsking1()
                findNavController().navigate(action)
                //return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
     */

    //------------------------------------


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding= StudentHomeBinding.inflate(inflater,container,false)

        //binding.stuBottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        
        return binding.root
    }

}