package com.example.streamchatdemo.studentSystem

import com.google.firebase.firestore.FirebaseFirestore

class dataBase {
    private val db = FirebaseFirestore.getInstance()

    fun getExp():Int{
        var result:String= String()
        var mid:Int=0
        db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9")
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    result=it.result!!.data!!.getValue("exp").toString()
                }
                mid=result.toInt()
            }
        return mid
    }

    fun addExp(exp:Int){
        db.collection("Userlist").document("0bwQiauYGM8699qnhEZ9").update("exp",exp+10)
    }
}