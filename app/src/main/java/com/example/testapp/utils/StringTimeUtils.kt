package com.example.testapp.utils

import java.security.MessageDigest

object StringTimeUtils {

    fun String.toMD5(): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(this.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }

    fun getTimeStamp() = "" + System.currentTimeMillis()/1000


    fun String.addHttpsUrl(): String{
        return "https" + this.substring(4, this.length)
    }
}