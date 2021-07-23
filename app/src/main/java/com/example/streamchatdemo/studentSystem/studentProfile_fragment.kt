package com.example.streamchatdemo.studentSystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.streamchatdemo.R
import com.example.streamchatdemo.databinding.StudentHomeFragmentBinding
import com.example.streamchatdemo.databinding.StudentProfileFragmentBinding
import com.example.streamchatdemo.model.ChatUser
import com.getstream.sdk.chat.ChatUI.Companion.instance
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.getstream.chat.android.client.ChatClient

class studentProfile_fragment:Fragment() {

    private val args: studentProfile_fragmentArgs by navArgs()
    private var _binding:StudentProfileFragmentBinding?=null
    private val binding get()=_binding!!
    private val db = FirebaseFirestore.getInstance()
    lateinit var initialExp:Number
    private var newExp:Int=0
    private val client = ChatClient.instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentProfileFragmentBinding.inflate(inflater,container,false)

        val currentUser = client.getCurrentUser()
        binding.textviewUserName.text = currentUser?.id

        db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9")
            .get()
            .addOnCompleteListener {
                var result:String= String()
                if(it.isSuccessful){
                    result=it.result!!.data!!.getValue("exp").toString()
                }
                initialExp=result.toInt()
                binding.showExp.setText(result)
            }

        binding.addExpBtn.setOnClickListener {
            db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9")
                .get()
                .addOnCompleteListener {
                    var result:String= String()
                    if(it.isSuccessful){
                        result=it.result!!.data!!.getValue("exp").toString()
                    }
                    initialExp=result.toInt()
                    binding.showExp.setText(result)
                }
            db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9").update("exp",initialExp.toInt()+100)
        }

        return binding.root
    }



    private fun getExp(){
        db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9")
            .get()
            .addOnCompleteListener {
                var result:String= String()
                if(it.isSuccessful){
                    result=it.result!!.data!!.getValue("exp").toString()
                }
                binding.showExp.setText(result)
            }
    }

}