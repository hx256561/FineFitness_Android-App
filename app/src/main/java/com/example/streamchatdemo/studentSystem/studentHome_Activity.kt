package com.example.streamchatdemo.studentSystem

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager
import com.example.streamchatdemo.R
import com.example.streamchatdemo.model.ChatUser
import com.example.streamchatdemo.muscleFlow.newLoginArgs
import com.example.streamchatdemo.muscleFlow.newLoginDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.models.User

class studentHome_Activity : AppCompatActivity() {


    private val args: studentHome_ActivityArgs by navArgs()
    private lateinit var navController: NavController

    private val client = ChatClient.instance()
    private lateinit var user: User

    val authUser= FirebaseAuth.getInstance()
    val currentAuthUser=authUser.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_home)

        navController = findNavController(R.id.student_navHostFragment)
        setUpNavigation()

        setupUser()


    }

    private fun setUpNavigation(){
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.student_bottomNavigationView)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)
    }

    var hahaha = "https://image.u-car.com.tw/theme/5E0A5AE9-9A99-491A-A1E6-0159BDFC3E1E.jpg"

    private fun setupUser() {
        if (client.getCurrentUser() == null) {
            user = if (args.chatUser.firstName.contains("Stefan")) {
                User(
                    id = args.chatUser.username,
                    extraData = mutableMapOf(
                        "name" to args.chatUser.firstName,
                        "county" to "Serbia",
                        //"image" to "https://image.u-car.com.tw/theme/5E0A5AE9-9A99-491A-A1E6-0159BDFC3E1E.jpg"
                        "image" to hahaha
                    )
                )
            } else {
                User(
                    //id = args.chatUser.username,
                    //下面這行: 利用google帳戶的uid來建立新使用者id(但是待改，
                    // google帳戶的id會是一串獨特亂碼，原本sample的id設定顯得相當多餘，
                    // 看要不要拔掉原本的id設定，或是移作其他用途)
                    id=currentAuthUser?.uid.toString(),
                    extraData = mutableMapOf(
                        //下面這行: 利用google帳戶的displayName來建立新使用者名字
                        "name" to currentAuthUser?.displayName.toString()
                    )
                )
            }
            val token = client.devToken(user.id)
            client.connectUser(
                user = user,
                token = token
            ).enqueue { result ->
                if (result.isSuccess) {
                    Log.d("ChannelFragment", "Success Connecting the User")
                } else {
                    Log.d("ChannelFragment", result.error().message.toString())
                }
            }
        }
    }


}