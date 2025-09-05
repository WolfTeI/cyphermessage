package ar.com.wolftei.cyphermessage.screens.affine

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AffineViewModel @Inject constructor(): ViewModel() {
    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _a: MutableStateFlow<String> = MutableStateFlow("")
    val a: StateFlow<String> = _a

    private val _b: MutableStateFlow<String> = MutableStateFlow("")
    val b: StateFlow<String> = _b

    private val _cipher: MutableStateFlow<String> = MutableStateFlow("")
    val cipher: StateFlow<String> = _cipher

    val alphabet = "abcdefghijklmnopqrstuvwxyz"

    fun onValueChange(newValue: String){
        _message.value = newValue
    }

    fun onValueChangeA(newValue: String){
        _a.value = newValue
    }

    fun onValueChangeB(newValue: String){
        _b.value = newValue
    }

    private fun modInverse(a: Int, m: Int): Int? {
        for (x in 1 until m) {
            if ((a * x) % m == 1) return x
        }
        return null
    }

    fun onCipher(){
        _cipher.value = ""
        val a = _a.value.trim().toIntOrNull()
        val b = _b.value.trim().toIntOrNull()
        val message = _message.value.lowercase().trim()
        if (a == null || b == null || gcd(a, alphabet.length) != 1) {
            Log.d("Affine", "Clave inválida")
            _cipher.value = message
            _message.value = ""
            _a.value = ""
            _b.value = ""
            return
        }
        for (char in message) {
            val idx = alphabet.indexOf(char)
            _cipher.value += if (idx == -1) char else alphabet[(a * idx + b) % alphabet.length]
        }
        _message.value = ""
        _a.value = ""
        _b.value = ""
    }

    fun onDecipher(){
        _cipher.value = ""
        val a = _a.value.trim().toIntOrNull()
        val b = _b.value.trim().toIntOrNull()
        val cipherText = _message.value.lowercase().trim()
        val aInv = if (a != null) modInverse(a, alphabet.length) else null
        if (a == null || b == null || aInv == null) {
            Log.d("Affine", "Clave inválida")
            _cipher.value = cipherText
            _message.value = ""
            _a.value = ""
            _b.value = ""
            return
        }
        for (char in cipherText) {
            val idx = alphabet.indexOf(char)
            _cipher.value += if (idx == -1) char else alphabet[(aInv * (idx - b + alphabet.length)) % alphabet.length]
        }
        _message.value = ""
        _a.value = ""
        _b.value = ""
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}
