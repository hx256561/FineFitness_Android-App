package com.example.streamchatdemo.muscleFlow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.streamchatdemo.databinding.FragmentLoginBinding
import com.example.streamchatdemo.databinding.NewLoginBinding
import com.example.streamchatdemo.model.ChatUser
import com.example.streamchatdemo.ui.channel.ChannelFragmentArgs
import com.example.streamchatdemo.ui.login.LoginFragmentDirections
import com.google.android.material.textfield.TextInputLayout


class newLogin : Fragment() {


    private val args: newLoginArgs by navArgs()
    private var _binding: NewLoginBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = NewLoginBinding.inflate(inflater, container, false)

        binding.newLoginToChannel.setOnClickListener {
            goToChannel()
        }

        return binding.root
    }

    private fun goToChannel(){
        val action=newLoginDirections.actionNewLoginToChannelFragment(args.chatUser)
        findNavController().navigate(action)
    }

}