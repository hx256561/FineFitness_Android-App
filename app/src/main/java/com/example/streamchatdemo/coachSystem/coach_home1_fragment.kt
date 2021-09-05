package com.example.streamchatdemo.coachSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streamchatdemo.databinding.CoachHomeFragmentBinding

class coach_home1_fragment: Fragment() {

    private var _binding: CoachHomeFragmentBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= CoachHomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}