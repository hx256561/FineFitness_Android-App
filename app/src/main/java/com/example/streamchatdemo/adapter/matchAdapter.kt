package com.example.streamchatdemo.adapter

import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.streamchatdemo.databinding.MatchRowLayoutBinding
import com.example.streamchatdemo.databinding.UserRowLayoutBinding
//import com.example.streamchatdemo.muscleFlow.student_asking2Directions
import com.example.streamchatdemo.studentSystem.studentAsk2_fragment
import com.example.streamchatdemo.studentSystem.studentAsk2_fragmentDirections
import com.example.streamchatdemo.ui.users.UsersFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User

class matchAdapter : RecyclerView.Adapter<matchAdapter.MyViewHolder>() {

    private val client = ChatClient.instance()
    private var userList = emptyList<User>()

    private val db = FirebaseFirestore.getInstance()
    private lateinit var mAuth: FirebaseAuth
    private val authUser=FirebaseAuth.getInstance()
    private val currentAuthUser=authUser.currentUser

    class MyViewHolder(val binding: MatchRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    //var randomNum=(0..2).random()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            MatchRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //var randomNum=(0..2).random()
        //val currentUser = userList[randomNum]

        val currentUser = userList[position]

        holder.binding.avatarImageView.setUserData(currentUser)
        //holder.binding.usernameTextView.text = currentUser.id

        db.collection("Userlist").document(currentUser.id.toString())
            .get()
            .addOnCompleteListener {
                var resultName:String= String()
                if(it.isSuccessful){
                    resultName=it.result!!.data!!.getValue("firstname").toString()
                    holder.binding.usernameTextView.text=resultName
                }
            }

        //holder.binding.lastActiveTextView.text = convertDate(currentUser.lastActive!!.time)
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

    private fun createNewChannel(selectedUser: String, holder: MyViewHolder) {
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

    private fun navigateToChatFragment(holder: MyViewHolder, cid: String) {
        val action = studentAsk2_fragmentDirections.actionStudentAsk2FragmentToStudentChatFragment(cid)
        holder.itemView.findNavController().navigate(action)
    }


}