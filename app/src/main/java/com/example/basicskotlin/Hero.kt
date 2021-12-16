package com.example.basicskotlin

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Hero(val name:String,val alter:String,val bio:String,val power:Float) : Parcelable