package ar.com.wolftei.cyphermessage.screens.xor

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class XorViewModel @Inject constructor(): ViewModel() {
    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _key: MutableStateFlow<String> = MutableStateFlow("")
    val key: StateFlow<String> = _key

    private val _cipher: MutableStateFlow<String> = MutableStateFlow("")
    val cipher: StateFlow<String> = _cipher

    fun onValueChange(newValue: String){
        _message.value = newValue
    }

    fun onValueChangeKey(newValue: String){
        _key.value = newValue
    }

    fun onCipher(){
        _cipher.value = ""
        val key = _key.value.trim()
        val message = _message.value.trim()
        if (key.isEmpty()) {
            Log.d("XOR", "Clave vacía")
            _cipher.value = message
            _message.value = ""
            _key.value = ""
            return
        }
        val keyBytes = key.toByteArray()
        val msgBytes = message.toByteArray()
        val cipherBytes = ByteArray(msgBytes.size)
        for (i in msgBytes.indices) {
            cipherBytes[i] = (msgBytes[i].toInt() xor keyBytes[i % keyBytes.size].toInt()).toByte()
        }
        _cipher.value = cipherBytes.joinToString(separator = " ") { it.toUByte().toString() }
        _message.value = ""
        _key.value = ""
    }

    fun onDecipher(){
        _cipher.value = ""
        val key = _key.value.trim()
        val cipherText = _message.value.trim().split(" ").map { it.toUByte().toByte() }.toByteArray()
        if (key.isEmpty()) {
            Log.d("XOR", "Clave vacía")
            _cipher.value = cipherText.toString(Charsets.UTF_8)
            _message.value = ""
            _key.value = ""
            return
        }
        val keyBytes = key.toByteArray()
        val msgBytes = ByteArray(cipherText.size)
        for (i in cipherText.indices) {
            msgBytes[i] = (cipherText[i].toInt() xor keyBytes[i % keyBytes.size].toInt()).toByte()
        }
        _cipher.value = msgBytes.toString(Charsets.UTF_8)
        _message.value = ""
        _key.value = ""
    }
}
