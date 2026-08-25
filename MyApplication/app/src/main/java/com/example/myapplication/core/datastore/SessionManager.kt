package com.example.myapplication.core.datastore

import android.content.Context
import android.content.Intent
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.myapplication.feature.auth.LoginActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "session")

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {

        private val USERNAME =
            stringPreferencesKey("username")

        private val ROLE =
            stringPreferencesKey("role")

    }

    suspend fun saveSession(session: Session) {

        context.dataStore.edit { preferences ->

            preferences[USERNAME] = session.username

            preferences[ROLE] = session.role


        }
    }

    suspend fun getSession(): Session? {

        val preferences = context.dataStore.data.first()

        val username = preferences[USERNAME] ?: return null

        val role = preferences[ROLE] ?: return null

        return Session(
            username = username,
            role = role,
        )
    }

    fun closeSession(){
        runBlocking {
            clear()
        }
        goToLogin()
    }

    fun goToLogin() {
        val intent = Intent(context, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        context.startActivity(intent)
    }

    suspend fun clear() {

        context.dataStore.edit {

            it.clear()

        }
    }
}