package ar.com.wolftei.cyphermessage.screens.vigenere

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class VigenereViewModel @Inject constructor(): ViewModel() {
    private val _message: MutableLiveData<String> = MutableLiveData("")
    val message: LiveData<String> = _message

    private val _desp: MutableLiveData<String> = MutableLiveData("")
    val desp: LiveData<String> = _desp

    private val _cipher: MutableLiveData<String> = MutableLiveData("")
    val cipher: LiveData<String> = _cipher

    fun onValueChange(newValue: String){
        _message.value = newValue
    }

    fun onValueChangeDesp(newValue: String){
        _desp.value = newValue
    }

    val alphabet = "abcdefghijklmnopqrstuvwxyz"

    fun onCipher(){
        _cipher.value = ""
        val desp = _desp.value!!.lowercase().trim()
        val message = _message.value!!.lowercase().trim()

        if( desp.isEmpty() || desp.toIntOrNull() != null ){
            Log.d("Vigenere", "Clave vacia")
            _cipher.value = message

            _message.value = ""
            _desp.value = ""
            return
        }


//        cifrado vigenere
        var i = 0
        for (char in message){
            val index = alphabet.indexOf(char)
            if (index == -1){
                _cipher.value += char
            } else {
                if (i >= desp.length -1){
                    i = 0
                }
                val keyIndex = alphabet.indexOf(desp[i % desp.length])
                var newIndex = (index + keyIndex) % alphabet.length
                //Manejo del desborde:
                if (newIndex >= alphabet.length) {
                    newIndex -= alphabet.length
                }
                _cipher.value += alphabet[newIndex]
                i++
            }
        }

        _message.value = ""
        _desp.value = ""

    }

    fun onDecipher(){
        _cipher.value = ""
        val desp = _desp.value!!.lowercase().trim()
        val message = _message.value!!.lowercase().trim()

//        descifrado vigenere
        var i = 0
        for (char in message){
            val index = alphabet.indexOf(char)
            if (index == -1){
                _cipher.value += char
            } else {
                if (i >= desp.length -1){
                    i = 0
                }
                var newIndex = (index - alphabet.indexOf(desp[i])) % alphabet.length
                if (newIndex < 0){
                    newIndex += alphabet.length
                }
                _cipher.value += alphabet[newIndex]
                i++
            }

        }
        _message.value = ""
        _desp.value = ""
    }
}