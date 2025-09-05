package ar.com.wolftei.cyphermessage.screens.railfence

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RailFenceViewModel @Inject constructor(): ViewModel() {
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
        val rails = _key.value.trim().toIntOrNull()
        val message = _message.value.trim()
        if (rails == null || rails < 2) {
            Log.d("RailFence", "Número de rieles inválido")
            _cipher.value = message
            _message.value = ""
            _key.value = ""
            return
        }
        val fence = Array(rails) { StringBuilder() }
        var rail = 0
        var dir = 1
        for (char in message) {
            fence[rail].append(char)
            rail += dir
            if (rail == 0 || rail == rails - 1) dir *= -1
        }
        _cipher.value = fence.joinToString("")
        _message.value = ""
        _key.value = ""
    }

    fun onDecipher(){
        _cipher.value = ""
        val rails = _key.value!!.trim().toIntOrNull()
        val cipherText = _message.value!!.trim()
        if (rails == null || rails < 2) {
            Log.d("RailFence", "Número de rieles inválido")
            _cipher.value = cipherText
            _message.value = ""
            _key.value = ""
            return
        }
        val len = cipherText.length
        val fence = Array(rails) { CharArray(len) { '\u0000' } }
        var idx = 0
        for (r in 0 until rails) {
            var rail = 0
            var dir = 1
            for (i in 0 until len) {
                if (rail == r) {
                    fence[rail][i] = '*'
                }
                rail += dir
                if (rail == 0 || rail == rails - 1) dir *= -1
            }
        }
        for (r in 0 until rails) {
            for (i in 0 until len) {
                if (fence[r][i] == '*') {
                    fence[r][i] = cipherText[idx++]
                }
            }
        }
        var result = StringBuilder()
        var rail = 0
        var dir = 1
        for (i in 0 until len) {
            result.append(fence[rail][i])
            rail += dir
            if (rail == 0 || rail == rails - 1) dir *= -1
        }
        _cipher.value = result.toString()
        _message.value = ""
        _key.value = ""
    }
}
