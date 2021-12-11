package com.example.streamchatdemo.studentSystem

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streamchatdemo.adapter.subscribeAdapter
import com.example.streamchatdemo.databinding.StudentSubscribe1ChatFragmentBinding
import com.google.firebase.firestore.FirebaseFirestore
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User

class studentSubscribe1Chat_fragment: Fragment() {
    var _binding:StudentSubscribe1ChatFragmentBinding?=null
    val binding get()=_binding!!
    private val db = FirebaseFirestore.getInstance()

    private val subscribeAdapter by lazy { subscribeAdapter() }
    private val client = ChatClient.instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentSubscribe1ChatFragmentBinding.inflate(inflater,container,false)

        setupRecyclerView()
        randomPick()
        //queryAllUsers()

        return binding.root
    }

    private fun setupRecyclerView(){
        binding.studentSubChatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.studentSubChatRecyclerView.adapter = subscribeAdapter
    }

    private fun queryAllUsers(id:String) {
        val request = QueryUsersRequest(
            //filter = Filters.ne("id", client.getCurrentUser()!!.id),
            filter = Filters.eq("id", id),
            offset = 0,
            limit = 100
        )
        client.queryUsers(request).enqueue { result ->
            if (result.isSuccess) {
                val users: List<User> = result.data()
                subscribeAdapter.setData(users)
            } else {
                Log.e("UsersFragment", result.error().message.toString())
            }
        }
    }

    private fun randomPick(){
        var getCoachId: String = null.toString()
        db.collection("Userlist").whereEqualTo("isCoach",1).get().addOnSuccessListener{ documents ->
            for((idx, document) in documents.withIndex()){
                if(idx == 0){
                    getCoachId = document.data.getValue("id").toString()
                }
            }
            queryAllUsers(getCoachId)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}