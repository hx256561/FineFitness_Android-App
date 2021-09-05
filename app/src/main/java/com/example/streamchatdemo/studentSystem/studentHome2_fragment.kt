package com.example.streamchatdemo.studentSystem

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.streamchatdemo.R
import com.example.streamchatdemo.databinding.StudentHome2FragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class studentHome2_fragment: Fragment() {

    private var _binding: StudentHome2FragmentBinding?=null
    private val binding get()=_binding!!
    private val db = FirebaseFirestore.getInstance()

    val authUser= FirebaseAuth.getInstance()
    val currentAuthUser=authUser.currentUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= StudentHome2FragmentBinding.inflate(inflater,container,false)

        changeMonsterImage()

        binding.goToCoachPage.setOnClickListener {
            goToCoachPage()
        }

        return binding.root
    }

    private fun goToCoachPage(){
        db.collection("Userlist").document(currentAuthUser?.uid.toString())
            .get()
            .addOnCompleteListener {
                var result:String= String()
                if(it.isSuccessful){
                    result=it.result!!.data!!.getValue("isCoach").toString()
                }
                if(result=="1"){
                    //var action=studentHome2_fragmentDirections.actionStudentHome2FragmentToCoachHome1Fragment()
                    var action=studentHome_fragmentDirections.actionStudentHomeFragmentToCoachHomeActivity()
                    findNavController().navigate(action)
                }else{
                    Log.d(TAG, "goToCoachPage: "+"failllllllllllllll")
                    val t=Toast.makeText(context,"You are not a Coach",Toast.LENGTH_SHORT)
                    t.setGravity(Gravity.CENTER_VERTICAL,0,0)
                    t.show()
                }
            }
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