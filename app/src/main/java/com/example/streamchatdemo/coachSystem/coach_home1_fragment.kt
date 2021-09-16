package com.example.streamchatdemo.coachSystem

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.streamchatdemo.databinding.CoachHomeFragmentBinding
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.models.name
import io.getstream.chat.android.livedata.ChatDomain
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

class coach_home1_fragment: Fragment() {

    private var _binding: CoachHomeFragmentBinding?=null
    private val binding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= CoachHomeFragmentBinding.inflate(inflater,container,false)

        binding.answerQuestion.setOnClickListener {
            var action=coach_home1_fragmentDirections.actionCoachHome1Fragment2ToCoachAnswerQuestion1Fragment()
            findNavController().navigate(action)
        }

        binding.toCoachChatroom.setOnClickListener {
            var action=coach_home1_fragmentDirections.actionCoachHome1Fragment2ToCoachChatroom1Fragment()
            findNavController().navigate(action)
        }

        return binding.root
    }


}