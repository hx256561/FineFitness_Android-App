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
import com.example.streamchatdemo.databinding.CoachAnswerQuestion1FragmentBinding
import com.example.streamchatdemo.ui.channel.ChannelFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.client.models.name
import io.getstream.chat.android.livedata.ChatDomain
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel
import io.getstream.chat.android.ui.channel.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

class coach_answer_question1_fragment: Fragment() {

    private var _binding:CoachAnswerQuestion1FragmentBinding?=null
    private val binding get()=_binding!!

    private val client = ChatClient.instance()
    private lateinit var user: User

    private val db = FirebaseFirestore.getInstance()

    private val authUser= FirebaseAuth.getInstance()
    private val currentAuthUser=authUser.currentUser
    private var authUid=currentAuthUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= CoachAnswerQuestion1FragmentBinding.inflate(inflater, container, false)

        setupChannels()

        binding.answerChannelsView.setChannelDeleteClickListener { channel ->
            deleteChannel(channel)
        }

        binding.answerChannelsView.setChannelItemClickListener { channel ->
            val action= coach_answer_question1_fragmentDirections.actionCoachAnswerQuestion1FragmentToChatFragment4(channel.cid)
            findNavController().navigate(action)
        }

        db.collection("QuestionList").document(authUid.toString()).get()
            .addOnCompleteListener {
                var ques:String=String()
                if(it.isSuccessful){
                    ques=it.result!!.data!!.getValue("question").toString()
                }
                binding.quess.setText(ques)
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
       // val listHeaderViewModel: ChannelListHeaderViewModel by viewModels()

        //listHeaderViewModel.bindView(binding.answerChannelListHeaderView, viewLifecycleOwner)
        listViewModel.bindView(binding.answerChannelsView, viewLifecycleOwner)
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