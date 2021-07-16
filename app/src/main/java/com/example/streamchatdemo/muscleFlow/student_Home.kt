package com.example.streamchatdemo.muscleFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streamchatdemo.databinding.StudentHomeBinding

class student_Home: Fragment() {

    private var _binding: StudentHomeBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentHomeBinding.inflate(inflater,container,false)

        return binding.root
    }
}