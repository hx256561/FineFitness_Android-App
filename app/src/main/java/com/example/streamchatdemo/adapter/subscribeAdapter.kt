package com.example.streamchatdemo.adapter

import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.streamchatdemo.databinding.UserRowLayoutBinding
import com.example.streamchatdemo.studentSystem.studentSubscribe1Chat_fragment
import com.example.streamchatdemo.studentSystem.studentSubscribe1Chat_fragmentDirections
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User

class subscribeAdapter: RecyclerView.Adapter<subscribeAdapter.MyViewHolder>() {
    private val client = ChatClient.instance()
    private var userList = emptyList<User>()

    class MyViewHolder(val binding: UserRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return subscribeAdapter.MyViewHolder(
            UserRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.binding.avatarImageView.setUserData(currentUser)
        holder.binding.usernameTextView.text = currentUser.id
        holder.binding.lastActiveTextView.text = convertDate(currentUser.lastActive!!.time)
        holder.binding.rootLayout.setOnClickListener {
            createNewChannel(currentUser.id, holder)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(newList: List<User>) {
        userList = newList
        notifyDataSetChanged()
    }

    private fun convertDate(milliseconds: Long): String {
        return DateFormat.format("dd/MM/yyyy hh:mm a", milliseconds).toString()
    }

    private fun createNewChannel(selectedUser: String, holder: subscribeAdapter.MyViewHolder) {
        client.createChannel(
            channelType = "messaging",
            members = listOf(client.getCurrentUser()!!.id, selectedUser)
        ).enqueue { result ->
            if (result.isSuccess) {
                navigateToChatFragment(holder, result.data().cid)
            } else {
                Log.e("UsersAdapter", result.error().message.toString())
            }
        }
    }

    private fun navigateToChatFragment(holder: subscribeAdapter.MyViewHolder, cid: String) {
        val action = studentSubscribe1Chat_fragmentDirections.actionStudentSubscribe1ChatFragmentToChatFragment3(cid)
        holder.itemView.findNavController().navigate(action)
    }
}