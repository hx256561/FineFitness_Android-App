package com.example.streamchatdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.streamchatdemo.R
import com.example.streamchatdemo.model.ChatUser
import com.example.streamchatdemo.ui.login.LoginFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.name

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val client = ChatClient.instance()

    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)

        mAuth= FirebaseAuth.getInstance()
        var authUser=mAuth.currentUser

        /*
        //Code below are firebase test(success)
        //firebase object
        val db = FirebaseFirestore.getInstance()
        //create a user and upload the data to firebase
        var user1=ChatUser("Bobbb10","Bobby10",0)
        db.collection("Userlist").document("user10").set(user1)
         */





        // below was from sample code，一開app會被呼叫一次，用意是(猜)如果手機自動填寫了帳密就直接登入到channelFragment-->目前可忽略
        if (navController.currentDestination?.label.toString().contains("login")) {
            val currentUser = client.getCurrentUser()
            if (currentUser != null) {
                val user = ChatUser(currentUser.name, currentUser.id,0)
                //val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(user)
                //navController.navigate(action)
            }
        }

    }


}