package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.streamchatdemo.R
import com.example.streamchatdemo.databinding.StudentHomeFragmentBinding
import com.example.streamchatdemo.databinding.StudentProfileFragmentBinding
import com.example.streamchatdemo.model.ChatUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class studentProfile_fragment:Fragment() {

    private val args: studentProfile_fragmentArgs by navArgs()
    private var _binding:StudentProfileFragmentBinding?=null
    private var _binding2:StudentHomeFragmentBinding?=null
    private val binding get()=_binding!!
    private val binding2 get()=_binding2!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentProfileFragmentBinding.inflate(inflater,container,false)

        addExp()

        return binding.root
    }

    private fun addExp(){
        binding.addExpBtn.setOnClickListener {
            db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9").update("exp",200)
        }
    }

}