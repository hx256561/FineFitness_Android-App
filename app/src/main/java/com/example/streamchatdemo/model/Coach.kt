package com.example.streamchatdemo.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coach(
    val firstName: String="",
    val phone: String="",
    var exp: Number=0,
    var balance:Number=0,
    var isCoach:Number=0,
    //var skills:Array<String>,
    val username:String=""
) : Parcelable