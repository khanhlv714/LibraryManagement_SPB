package com.example.myapplication.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
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
            role = role
        )
    }

    suspend fun clear() {

        context.dataStore.edit {

            it.clear()

        }
    }
}