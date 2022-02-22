package com.example.pullrequest

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {

    /**
     * Method used to check whether internet connection is available or not.
     */
    fun isNetworkAvailable(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }

}