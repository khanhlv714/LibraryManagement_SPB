package com.example.myapplication.core.network

import android.net.ConnectivityManager

class NetworkMonitor(
    private val connectivityManager: ConnectivityManager
) {

    fun isConnected(): Boolean {
        return true;
    }

}