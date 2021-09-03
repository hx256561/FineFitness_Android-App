package com.example.streamchatdemo.studentSystem

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.example.streamchatdemo.R
import com.example.streamchatdemo.databinding.StudentHomeFragmentBinding
import com.example.streamchatdemo.databinding.StudentProfileFragmentBinding
import com.example.streamchatdemo.model.ChatUser
import com.example.streamchatdemo.ui.login.LoginFragment
import com.getstream.sdk.chat.ChatUI.Companion.instance
import com.google.firebase.auth.FirebaseAuth
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

    private lateinit var mAuth:FirebaseAuth

    val loginF=LoginFragment()

    val authUser=FirebaseAuth.getInstance()
    val currentAuthUser=authUser.currentUser


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentProfileFragmentBinding.inflate(inflater,container,false)
        mAuth= FirebaseAuth.getInstance()

        //binding.textviewUserName.setText(client.getCurrentUser()?.id)
        binding.googleLogOut.setOnClickListener {
            mAuth.signOut()
        }
        binding.userUid.setText(currentAuthUser?.uid)

        //下面這坨: 利用google的id從firestore抓下資料們
        db.collection("Userlist").document(currentAuthUser?.uid.toString())
            .get()
            .addOnCompleteListener {
                var resultName:String= String()
                var resultPhone:String= String()
                var resultBalance:String= String()
                if(it.isSuccessful){
                    resultName=it.result!!.data!!.getValue("firstname").toString()
                    resultPhone=it.result!!.data!!.getValue("phone").toString()
                    resultBalance=it.result!!.data!!.getValue("balance").toString()

                    binding.textviewUserName.setText(resultName)
                    binding.textviewUserTel.setText(resultPhone)
                    binding.textviewUserBalance.setText(resultBalance)
                }
            }

        binding.btnBeCoach.setOnClickListener {
            goToSubscribeChat()
        }

        return binding.root
    }

    private fun goToSubscribeChat(){
        var action1 = studentProfile_fragmentDirections.actionStudentProfileFragmentToStudentProfileBeCoachFragent()
        findNavController().navigate(action1)
    }

}

/*
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
 */