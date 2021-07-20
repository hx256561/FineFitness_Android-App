package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.streamchatdemo.databinding.StudentHomeFragmentBinding

class studentHome_fragment: Fragment() {

    private val args: studentHome_fragmentArgs by navArgs()
    private var _binding:StudentHomeFragmentBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentHomeFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun changeMonster(){

    }
}