package ar.com.wolftei.cyphermessage.screens.cesar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CesarViewModel @Inject constructor() : ViewModel() {
    private val _message: MutableLiveData<String> = MutableLiveData("")
    val message: LiveData<String> = _message

    private val _desp: MutableLiveData<String> = MutableLiveData("")
    val desp: LiveData<String> = _desp

    private val _cipher: MutableLiveData<String> = MutableLiveData("")
    val cipher: LiveData<String> = _cipher

    fun onValueChange(newValue: String) {
        _message.value = newValue
    }

    fun onValueChangeDesp(newValue: String) {
        _desp.value = newValue
    }

    val alphabet = "abcdefghijklmnopqrstuvwxyz0123456789"

    fun onCipher() {
        _cipher.value = ""
        Log.d("desp", _desp.value!!)
        if (_desp.value!! != "") {
            val desp = _desp.value!!.trim().toInt()
            val message = _message.value!!.lowercase().trim()
            for (char in message) {
                val index = alphabet.indexOf(char)
                if (index == -1) {
                    _cipher.value += char
                } else {
                    val newIndex = (index + desp) % alphabet.length
                    _cipher.value += alphabet[newIndex]
                }
            }
            _message.value = ""
            _desp.value = ""
        }

    }

    fun onDecipher() {
        _cipher.value = ""
        Log.d("desp", _desp.value!!)
        if (_desp.value!! != "") {
            val desp = _desp.value!!.trim().toInt()
            val message = _message.value!!.lowercase().trim()
            for (char in message) {
                val index = alphabet.indexOf(char)
                if (index == -1) {
                    _cipher.value += char
                } else {
                    var newIndex = (index - desp) % alphabet.length
                    if (newIndex < 0){
                        newIndex += alphabet.length
                    }
                    _cipher.value += alphabet[newIndex]
                }
            }
            _message.value = ""
            _desp.value = ""
        }
    }
}