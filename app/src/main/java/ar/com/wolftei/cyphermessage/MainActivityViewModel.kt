package ar.com.wolftei.cyphermessage

import androidx.lifecycle.ViewModel
import ar.com.wolftei.cyphermessage.dataStore.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dataStore: DataStore
): ViewModel() {

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    fun updateThemePreference(isDarkTheme: Boolean) {
        _isDarkTheme.value = isDarkTheme
    }


    suspend fun saveThemePreference(isDarkTheme: Boolean) {
        dataStore.saveThemePreference(isDarkTheme)
    }

    //DataStore
    fun getThemePreference(): Flow<Boolean> {
        return dataStore.getThemePreference()
    }
}