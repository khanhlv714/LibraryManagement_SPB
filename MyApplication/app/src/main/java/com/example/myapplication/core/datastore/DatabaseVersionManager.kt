package com.example.myapplication.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton


private val Context.dataStore by preferencesDataStore(name = "update_database_last")

@Singleton
class DatabaseVersionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {

        private val TIME_UPDATE =
            stringPreferencesKey("time_update")

    }

    suspend fun saveUpdateTime(time : String) {

        context.dataStore.edit { preferences ->

            preferences[TIME_UPDATE] = time
        }
    }

    suspend fun getUpdateTime(): String? {

        val preferences = context.dataStore.data.first()

        val time = preferences[TIME_UPDATE] ?: return null

        return time
    }

    suspend fun clear() {

        context.dataStore.edit {

            it.clear()

        }
    }
}