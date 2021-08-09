package com.example.streamchatdemo.studentSystem

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
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.models.Filters
import io.getstream.chat.android.client.models.User

class studentAsk2_fragment: Fragment() {

    var _binding:StudentAsk2FragmentBinding?=null
    val binding get()=_binding!!


    private val matchAdapter by lazy { matchAdapter() }
    private val client = ChatClient.instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentAsk2FragmentBinding.inflate(inflater,container,false)

        setupRecyclerView()
        queryAllUsers()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.studentAsk2RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.studentAsk2RecyclerView.adapter = matchAdapter
    }


    private fun queryAllUsers() {
        val request = QueryUsersRequest(
            //filter = Filters.ne("id", client.getCurrentUser()!!.id),
            filter = Filters.eq("id", "qqqq"),
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}