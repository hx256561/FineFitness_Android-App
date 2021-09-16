package com.example.streamchatdemo.studentSystem


import android.app.usage.UsageEvents
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent.KEYCODE_BACK
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.streamchatdemo.databinding.FragmentChatBinding
import com.example.streamchatdemo.databinding.StudentChatFragmentBinding
import com.example.streamchatdemo.ui.chat.ChatFragmentArgs
import com.getstream.sdk.chat.viewmodel.MessageInputViewModel
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel
import io.getstream.chat.android.client.models.Channel
import io.getstream.chat.android.client.models.name
import io.getstream.chat.android.livedata.ChatDomain
import io.getstream.chat.android.ui.message.input.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.header.viewmodel.MessageListHeaderViewModel
import io.getstream.chat.android.ui.message.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory
//專用於學生配對的chatFragment，偵測到退出聊天室窗後刪除聊天室(尚未實作)
class student_chat_fragment : Fragment() {

    private val args: ChatFragmentArgs by navArgs()

    private var _binding: StudentChatFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = StudentChatFragmentBinding.inflate(inflater, container, false)

        setupMessages()

        binding.messagesHeaderView.setBackButtonClickListener {
            requireActivity().onBackPressed()
        }


        return binding.root
    }

    private fun setupMessages() {
        val factory = MessageListViewModelFactory(cid = args.channelId)

        val messageListHeaderViewModel: MessageListHeaderViewModel by viewModels { factory }
        val messageListViewModel: MessageListViewModel by viewModels { factory }
        val messageInputViewModel: MessageInputViewModel by viewModels { factory }

        messageListHeaderViewModel.bindView(binding.messagesHeaderView, viewLifecycleOwner)
        messageListViewModel.bindView(binding.messageList, viewLifecycleOwner)
        messageInputViewModel.bindView(binding.messageInputView, viewLifecycleOwner)
    }

    //將原本傳入的channel 改成 cid
    private fun deleteChannel(cid: String) {
        ChatDomain.instance().useCases.deleteChannel(cid).enqueue { result ->
            if (result.isSuccess) {
                Log.d(TAG, "deleteChannel: Success")
            } else {
                Log.e("ChannelFragment", result.error().message.toString())
            }
        }
    }


    //在fragment的lifecycle結束後會自動把channel刪除
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "onDestroyView: "+"& delete channel")
        deleteChannel(args.channelId)
    }
}