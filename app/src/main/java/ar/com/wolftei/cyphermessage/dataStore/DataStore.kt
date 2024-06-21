package ar.com.wolftei.cyphermessage.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    companion object {
        val IS_DARK_THEME_KEY = booleanPreferencesKey("is_dark_theme")
    }

    suspend fun saveThemePreference(isDarkTheme: Boolean) {
        dataStore.edit { preferences ->preferences[IS_DARK_THEME_KEY] = isDarkTheme
        }
    }

    fun getThemePreference(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_DARK_THEME_KEY] ?: false
        }
    }
}