package ar.com.wolftei.cyphermessage.screens.vigenere

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class VigenereViewModel @Inject constructor(): ViewModel() {
    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _desp: MutableStateFlow<String> = MutableStateFlow("")
    val desp: StateFlow<String> = _desp

    private val _cipher: MutableStateFlow<String> = MutableStateFlow("")
    val cipher: StateFlow<String> = _cipher

    fun onValueChange(newValue: String){
        _message.value = newValue
    }

    fun onValueChangeDesp(newValue: String){
        _desp.value = newValue
    }

    val alphabet = "abcdefghijklmnopqrstuvwxyz"

    fun onCipher(){
        _cipher.value = ""
        val desp = _desp.value.lowercase().trim()
        val message = _message.value.lowercase().trim()

        if( desp.isEmpty() || desp.toIntOrNull() != null ){
            Log.d("Vigenere", "Clave vacia")
            _cipher.value = message
            _message.value = ""
            _desp.value = ""
            return
        }

        // cifrado vigenere
        var i = 0
        for (char in message){
            val index = alphabet.indexOf(char)
            if (index == -1){
                _cipher.value += char
            } else {
                val despIndex = alphabet.indexOf(desp[i % desp.length])
                _cipher.value += alphabet[(index + despIndex) % alphabet.length]
                i++
            }
        }
        _message.value = ""
        _desp.value = ""
    }

    fun onDecipher(){
        _cipher.value = ""
        val desp = _desp.value.lowercase().trim()
        val cipherText = _message.value.lowercase().trim()

        if( desp.isEmpty() || desp.toIntOrNull() != null ){
            Log.d("Vigenere", "Clave vacia")
            _cipher.value = cipherText
            _message.value = ""
            _desp.value = ""
            return
        }

        var i = 0
        for (char in cipherText){
            val index = alphabet.indexOf(char)
            if (index == -1){
                _cipher.value += char
            } else {
                val despIndex = alphabet.indexOf(desp[i % desp.length])
                var newIndex = (index - despIndex) % alphabet.length
                if (newIndex < 0) newIndex += alphabet.length
                _cipher.value += alphabet[newIndex]
                i++
            }

        }
        _message.value = ""
        _desp.value = ""
    }
}