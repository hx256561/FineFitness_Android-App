package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.streamchatdemo.databinding.StudentAsk1FragmentBinding

class studentAsk1_fragment: Fragment() {

    var _binding:StudentAsk1FragmentBinding?=null
    val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=StudentAsk1FragmentBinding.inflate(inflater,container,false)



        binding.sendQuestion.setOnClickListener {
            var question=binding.questionInput.getText().toString()
            goToAsk2(question)
        }

        return binding.root
    }

    private fun goToAsk2(ques:String){
        var action1=studentAsk1_fragmentDirections.actionStudentAsk1FragmentToStudentAsk2Fragment(ques)
        findNavController().navigate(action1)
    }

}