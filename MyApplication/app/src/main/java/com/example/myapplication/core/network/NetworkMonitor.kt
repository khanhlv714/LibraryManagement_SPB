package com.example.myapplication.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

@Singleton
class NetworkMonitor {
    var isSynced: Boolean = false
    private set
    var isOnline = MutableStateFlow(false)
    private set
    val connectivityManager: ConnectivityManager;

    @Inject
    constructor(@ApplicationContext context: Context) {
        connectivityManager = context.getSystemService(
            ConnectivityManager::class.java
        )

        val callback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(
                network: Network
            ) {
                isOnline.value = checkInternet()
            }

            override fun onLost(
                network: Network
            ) {
                isOnline.value = checkInternet()
            }

            override fun onCapabilitiesChanged(
                network: Network, networkCapabilities: NetworkCapabilities
            ) {
                isOnline.value = checkInternet()
            }
        }

        val request =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

        connectivityManager.registerNetworkCallback(
            request, callback
        )
        isOnline.value = checkInternet()
    }

    fun checkInternet(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false

        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        ) && capabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_VALIDATED
        )
    }

    fun markSynced() {
       isSynced = true;
    }
}
