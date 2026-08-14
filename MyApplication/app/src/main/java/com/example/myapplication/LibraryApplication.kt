package com.example.myapplication

import android.app.Application
import com.example.myapplication.core.datastore.TokenManager
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class LibraryApplication : Application() {

    @Inject
    lateinit var tokenManager : TokenManager
    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            tokenManager.loadToken()
        }
    }
}