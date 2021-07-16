package com.example.streamchatdemo.muscleFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.streamchatdemo.databinding.StudentAsking1Binding
import com.example.streamchatdemo.databinding.StudentHomeBinding

class student_asking1: Fragment() {

    private var _binding:StudentAsking1Binding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentAsking1Binding.inflate(inflater,container,false)

        binding.btnAsk.setOnClickListener {
            goToAsking2()
        }

        return binding.root
    }

    private fun goToAsking2(){
        var action=student_asking1Directions.actionStudentAsking1ToStudentAsking2()
        findNavController().navigate(action)
    }
}