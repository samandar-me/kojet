package com.sdk.data.local.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("DataStore")
    companion object{
        val lan = stringPreferencesKey("language")
        val cl = intPreferencesKey("color")
    }
    suspend fun saveTheme(index: Int) {
        context.dataStore.edit {
            it[cl] = index
        }
    }
    fun getTheme(): Flow<Int> = context.dataStore.data.map {
        it[cl] ?: 0
    }

    suspend fun saveLanguage(language: String) {
        context.dataStore.edit {
            it[lan] = language
        }
    }
    fun getLanguage(): Flow<String> = context.dataStore.data.map {
        it[lan] ?: "en"
    }
}