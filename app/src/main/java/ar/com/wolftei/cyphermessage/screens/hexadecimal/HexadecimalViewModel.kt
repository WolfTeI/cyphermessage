package ar.com.wolftei.cyphermessage.screens.hexadecimal

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HexadecimalViewModel @Inject constructor(): ViewModel() {
    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _cipher: MutableStateFlow<String> = MutableStateFlow("")
    val cipher: StateFlow<String> = _cipher

    fun onValueChange(newValue: String){
        _message.value = newValue
    }

    fun onCipher(){
        _cipher.value = ""
        for (char in _message.value.lowercase().trim()){
            val binary = char.code.toString(16).uppercase()
            _cipher.value += "$binary "
        }
        _message.value = ""
    }

    fun onDecipher(){
        _cipher.value = ""
        for (num in _message.value.lowercase().trim().split(" ")){
            val binary = num.toInt(16).toChar()
            _cipher.value += binary
        }
        _message.value = ""
    }
}