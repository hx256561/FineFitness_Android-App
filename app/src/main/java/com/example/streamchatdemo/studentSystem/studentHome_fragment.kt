package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.viewpager.widget.ViewPager
import com.example.streamchatdemo.R
import com.example.streamchatdemo.adapter.myViewPagerAdapter
import com.example.streamchatdemo.databinding.StudentHomeFragmentBinding
import com.google.firebase.firestore.FirebaseFirestore

class studentHome_fragment: Fragment() {

    private val args: studentHome_ActivityArgs by navArgs()
    private var _binding:StudentHomeFragmentBinding?=null
    private val binding get()=_binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentHomeFragmentBinding.inflate(inflater,container,false)

        val adapter= myViewPagerAdapter(childFragmentManager)
        adapter.addFragment(studentHome2_fragment())
        adapter.addFragment(studentTrainMenu_fragment())
        val viewPager=binding.viewPager
        viewPager.adapter=adapter

        return binding.root
    }


}