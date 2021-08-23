package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streamchatdemo.databinding.StudentSubscribe2FragmentBinding

class studentSubscribe2_fragment: Fragment() {
    private var _binding:StudentSubscribe2FragmentBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentSubscribe2FragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}