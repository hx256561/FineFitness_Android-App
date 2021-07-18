package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.streamchatdemo.databinding.StudentProfileFragmentBinding

class studentProfile_fragment:Fragment() {

    private val args: studentProfile_fragmentArgs by navArgs()
    private var _binding:StudentProfileFragmentBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding= StudentProfileFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }
}