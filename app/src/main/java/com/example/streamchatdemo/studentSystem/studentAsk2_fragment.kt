package com.example.streamchatdemo.studentSystem

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streamchatdemo.adapter.matchAdapter
import com.example.streamchatdemo.databinding.StudentAsk2FragmentBinding
import com.example.streamchatdemo.databinding.StudentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User

class studentAsk2_fragment: Fragment() {

    var _binding:StudentAsk2FragmentBinding?=null
    val binding get()=_binding!!


    private val matchAdapter by lazy { matchAdapter() }
    private val client = ChatClient.instance()
    private val db = FirebaseFirestore.getInstance()

    //取得現在登入的google帳號
    private val authUser= FirebaseAuth.getInstance()
    private val currentAuthUser=authUser.currentUser
    private var authUid=currentAuthUser?.uid

    private var idList= mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentAsk2FragmentBinding.inflate(inflater,container,false)

        setupRecyclerView()

        randomPick()

        //queryAllUsers()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.studentAsk2RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.studentAsk2RecyclerView.adapter = matchAdapter
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
                matchAdapter.setData(users)
            } else {
                Log.e("UsersFragment", result.error().message.toString())
            }
        }
    }

    //實作隨機配對機制
    private fun randomPick(){
        //取出isCoach=1的使用者
        db.collection("Userlist").whereEqualTo("isCoach",1).get()
            .addOnSuccessListener {
                    documents->
                for(document in documents){
                    //把isCoach=1的使用者id加入idList
                    idList.add(document.data.getValue("id").toString())
                }
                //從idList移除自己的id避免配到自己
                idList.remove(authUid)
                //從idList中隨機挑選
                var randomNum=(0..idList.size-1).random()
                var result=idList[randomNum]
                //將隨機挑選的字串傳入queryAllUsers裡面
                queryAllUsers(result)
            }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}