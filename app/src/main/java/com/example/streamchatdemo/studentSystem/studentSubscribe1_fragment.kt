package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.streamchatdemo.databinding.StudentSubscribe1FragmentBinding

class studentSubscribe1_fragment: Fragment() {
    private var _binding:StudentSubscribe1FragmentBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentSubscribe1FragmentBinding.inflate(inflater,container,false)
        binding.buttonClsroom.setOnClickListener {
            goToSubscribeChat()
        }
        return binding.root
    }

    private fun goToSubscribeChat(){
        var action1 = studentSubscribe_fragmentDirections.actionStudentSubscribeFragmentToStudentSubscribe1ChatFragment()
        findNavController().navigate(action1)
    }
}