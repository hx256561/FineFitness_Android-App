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
import com.example.streamchatdemo.databinding.CoachChatroom1FragmentBinding
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.models.name
import io.getstream.chat.android.livedata.ChatDomain
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory


//教練的聊聊清單頁面，退出聊天室後不會刪除
class coach_chatroom1_fragment: Fragment() {

    private var _binding:CoachChatroom1FragmentBinding?=null
    private val binding get()=_binding!!

    private val client = ChatClient.instance()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= CoachChatroom1FragmentBinding.inflate(inflater,container,false)

        setupChannels()

        binding.chatroomChannelsView.setChannelDeleteClickListener { channel ->
            deleteChannel(channel)
        }

        binding.chatroomChannelsView.setChannelItemClickListener{ channel->
            val action= coach_chatroom1_fragmentDirections.actionCoachChatroom1FragmentToChatFragment4(channel.cid)
            findNavController().navigate(action)
        }

        return binding.root
    }

    private fun setupChannels() {
        val filters = Filters.and(
            Filters.eq("type", "messaging"),
            Filters.`in`("members", listOf(client.getCurrentUser()!!.id))
        )
        val viewModelFactory = ChannelListViewModelFactory(
            filters,
            ChannelListViewModel.DEFAULT_SORT
        )
        val listViewModel: ChannelListViewModel by viewModels { viewModelFactory }

        listViewModel.bindView(binding.chatroomChannelsView, viewLifecycleOwner)
    }

    private fun deleteChannel(channel: Channel) {
        ChatDomain.instance().useCases.deleteChannel(channel.cid).enqueue { result ->
            if (result.isSuccess) {
                showToast("Channel: ${channel.name} removed!")
            } else {
                Log.e("ChannelFragment", result.error().message.toString())
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}