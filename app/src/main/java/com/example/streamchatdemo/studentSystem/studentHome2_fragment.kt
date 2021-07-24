package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.streamchatdemo.R
import com.example.streamchatdemo.databinding.StudentHome2FragmentBinding
import com.google.firebase.firestore.FirebaseFirestore

class studentHome2_fragment: Fragment() {

    private val args: studentHome_ActivityArgs by navArgs()
    private var _binding: StudentHome2FragmentBinding?=null
    private val binding get()=_binding!!
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentHome2FragmentBinding.inflate(inflater,container,false)

        changeMonsterImage()

        return binding.root
    }

    private fun changeMonsterImage(){
        db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9")
            .get()
            .addOnCompleteListener {
                var result:String= String()
                if(it.isSuccessful){
                    result=it.result!!.data!!.getValue("exp").toString()
                }
                var expValue=result.toInt()
                if(expValue < 400){
                    binding.monsterImage.setBackgroundResource(R.drawable.monster_1)
                }else{
                    binding.monsterImage.setBackgroundResource(R.drawable.test_monster1)
                }
            }
    }
}