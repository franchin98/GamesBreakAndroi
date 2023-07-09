package com.example.gamesbreak.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gamesbreak.ui.authentication.LoginViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserPreferences(context: Context){
    private val appContext = context.applicationContext

    val userCredentials: Flow<UserCredentials?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[USER_CREDENTIALS]?.let { serializedUserCredentials ->
                Json.decodeFromString<UserCredentials>(serializedUserCredentials)
            }
        }
// TO-DO Fix USerCredentials Decoding
    suspend fun saveUserCredentials(userCredentials: UserCredentials) {
        print(userCredentials)
        appContext.dataStore.edit { preferences ->
            preferences[USER_CREDENTIALS] = "Hole"//Json.encodeToString(userCredentials)
        }
    }
    companion object {
        private const val USER_PREFERENCES_NAME = "user_preferences"
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)
        private val USER_CREDENTIALS = stringPreferencesKey("key_user_credentials")
    }
}