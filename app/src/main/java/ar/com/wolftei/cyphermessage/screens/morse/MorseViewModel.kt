package ar.com.wolftei.cyphermessage.screens.morse

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MorseViewModel @Inject constructor(): ViewModel() {
    private val _message: MutableLiveData<String> = MutableLiveData("")
    val message: LiveData<String> = _message

    private val _cipher: MutableLiveData<String> = MutableLiveData("")
    val cipher: LiveData<String> = _cipher

    fun onValueChange(newValue: String){
        _message.value = newValue
    }

    fun onCipher(){
        _cipher.value = ""
        _cipher.value = _message.value!!.morse()
        _message.value = ""

    }

    private fun String.morse(): String{
        val mapCode = mapOf(
            "a" to ".-", "b" to "-...", "c" to "-.-.", "d" to "-..", "e" to ".",
            "f" to "..-.","g" to "--.", "h" to "....", "i" to "..", "j" to "·---",
            "k" to "-.-", "l" to ".-..", "m" to "--", "n" to "-.", "ñ" to "--.--",
            "o" to "---", "p" to ".__.", "q" to "--.-", "r" to ".-.", "s" to "...",
            "t" to "-", "u" to "..-", "v" to "...-", "w" to ".--", "x" to "-..-",
            "y" to "-.--", "z" to "--..",
            "0" to "-----", "1" to ".----", "2" to "..---", "3" to "...--",
            "4" to "....-", "5" to ".....", "6" to "-....", "7" to "--...",
            "8" to "---..", "9" to "----.",
            "." to ".-.-.-", "," to "-.-.--", "?" to "..--..", "\"" to ".-..-."
        )
        var aux = ""
        this.lowercase().forEach {
            Log.d("Morse", it.toString())
            aux += mapCode.getOrDefault(it.toString(), it.toString()) + " "
            Log.d("Morse", aux)
        }
        return aux
    }

    fun onDecipher(){
        _cipher.value = ""
        _cipher.value = _message.value!!.plain()
        _message.value = ""
    }

    private fun String.plain(): String{
        val mapCode = mapOf(
            ".-" to "a", "-..." to "b", "-.-." to "c", "-.." to "d", "." to "e",
            "..-." to "f","--." to "g", "...." to "h", ".." to "i", "·---" to "j",
            "-.-" to "k", ".-.." to "l", "--" to "m", "-." to "n", "--.--" to "ñ",
            "---" to "o", ".__." to "p", "--.-" to "q", ".-." to "r", "..." to "s",
            "-" to "t", "..-" to "u", "...-" to "v", ".--" to "w", "-..-" to "x",
            "-.--" to "y", "--.." to "z",
            "-----" to "0", ".----" to "1", "..---" to "2", "...--" to "3",
            "....-" to "4", "....." to "5", "-...." to "6", "--..." to "7",
            "---.." to "8", "----." to "9",
            ".-.-.-" to ".", "-.-.--" to ",", "..--.." to "?", ".-..-." to "\""
        )
        var aux = ""
        this.lowercase().split(" ").forEach {
            aux += mapCode.getOrDefault(it, it) + ""
        }
        return aux
    }
}