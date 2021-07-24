package com.example.streamchatdemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class myViewPagerAdapter(manager: FragmentManager): FragmentPagerAdapter(manager){

    private val fragmentlist:MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentlist[position]
    }
    override fun getCount(): Int {
        return fragmentlist.size
    }

    fun addFragment(fragment: Fragment){
        fragmentlist.add(fragment)
    }
}