package com.example.streamchatdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.streamchatdemo.R
import com.example.streamchatdemo.model.ChatUser
import com.example.streamchatdemo.ui.login.LoginFragmentDirections
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.name

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val client = ChatClient.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.navHostFragment)

        /*
        //Code below are firebase test(success)
        //firebase object
        val db = FirebaseFirestore.getInstance()
        //create a user and upload the data to firebase
        var user1=ChatUser("Bobbb9","Bobby9",0)
        db.collection("Userlist").document("user9").set(user1)

         */



        if (navController.currentDestination?.label.toString().contains("login")) {
            val currentUser = client.getCurrentUser()
            if (currentUser != null) {
                val user = ChatUser(currentUser.name, currentUser.id,0)
                val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(user)
                navController.navigate(action)
            }
        }

    }


}