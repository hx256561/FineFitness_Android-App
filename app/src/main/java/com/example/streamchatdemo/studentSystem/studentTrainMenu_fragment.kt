package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.streamchatdemo.adapter.matchAdapter
import com.example.streamchatdemo.adapter.trainMenuAdapter
import com.example.streamchatdemo.databinding.StudentTrainMenuFragmentBinding
import com.example.streamchatdemo.model.trainMenuItem

class studentTrainMenu_fragment: Fragment() {

    private var _binding:StudentTrainMenuFragmentBinding?=null
    private val binding get()=_binding!!
    private val trainMenuAdapter by lazy { trainMenuAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentTrainMenuFragmentBinding.inflate(inflater,container,false)

        var item1=trainMenuItem("Push up * 100")
        var item2=trainMenuItem("Push up * 50")
        trainMenuAdapter.addTrainMenuItem(item1)
        trainMenuAdapter.addTrainMenuItem(item2)

        setUpRecyclerView()

        return binding.root
    }

    private fun setUpRecyclerView(){
        binding.trainMenu.layoutManager=LinearLayoutManager(requireContext())
        binding.trainMenu.adapter=trainMenuAdapter
    }

}