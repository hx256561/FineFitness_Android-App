package com.example.streamchatdemo.studentSystem

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.streamchatdemo.databinding.StudentProfileBcoachFragmentBinding

class studentProfileBeCoach_fragent: Fragment() {
    private var _binding:StudentProfileBcoachFragmentBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentProfileBcoachFragmentBinding.inflate(inflater,container,false)

        binding.btnApplyBCoach.setOnClickListener {
            val t= Toast.makeText(this.context,"Success! Pending approval...", Toast.LENGTH_SHORT)
            t.setGravity(Gravity.CENTER_VERTICAL,0,0)
            t.show()
        }

        binding.btnChooseFile.setOnClickListener {
            openGallery()
        }

        return binding.root
    }

    private fun openGallery(){
        // 謎之竟然成功打開檔案夾: https://camposha.info/android-examples/android-capture-pick-image/
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, 2)
    }
}