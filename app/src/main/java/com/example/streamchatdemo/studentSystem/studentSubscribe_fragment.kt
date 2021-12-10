package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streamchatdemo.adapter.myViewPagerAdapter
import com.example.streamchatdemo.databinding.StudentSubscribeFragmentBinding
// 這個fragment裝著studentSubscibe1_fragment和2
class studentSubscribe_fragment: Fragment() {
    private var _binding:StudentSubscribeFragmentBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if(_binding==null){
            _binding= StudentSubscribeFragmentBinding.inflate(inflater,container,false)
            val adapter= myViewPagerAdapter(childFragmentManager)
            adapter.addFragment(studentSubscribe2_fragment())
            adapter.addFragment(studentSubscribe1_fragment())
            //adapter.addFragment(studentSubscribe2_fragment())
            val viewPager=binding.viewPager1
            viewPager.adapter=adapter
        }

        return binding.root
    }
}