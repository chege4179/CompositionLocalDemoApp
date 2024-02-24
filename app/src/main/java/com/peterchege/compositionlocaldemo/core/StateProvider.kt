package com.peterchege.compositionlocaldemo.core

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StateProvider @Inject constructor(
    private val dataStore:DataStore<Preferences>
){

    private val stateProviderKey = stringPreferencesKey(name ="string_provider")
    suspend fun setState(themeValue: String) {
        dataStore.edit { preferences ->
            preferences[stateProviderKey] = themeValue
        }
    }
    fun getState(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[stateProviderKey] ?: "empty_string"
        }
    }
}