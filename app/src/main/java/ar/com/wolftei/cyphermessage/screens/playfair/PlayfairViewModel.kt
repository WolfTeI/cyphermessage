package ar.com.wolftei.cyphermessage.screens.playfair

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlayfairViewModel @Inject constructor(): ViewModel() {
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

    private fun generateMatrix(key: String): Array<CharArray> {
        val alphabet = "abcdefghiklmnopqrstuvwxyz" // sin la j
        val used = mutableSetOf<Char>()
        val matrix = Array(5) { CharArray(5) }
        var idx = 0
        for (c in key + alphabet) {
            val ch = if (c == 'j') 'i' else c
            if (ch in alphabet && ch !in used) {
                matrix[idx / 5][idx % 5] = ch
                used.add(ch)
                idx++
            }
            if (idx >= 25) break
        }
        return matrix
    }

    private fun findPosition(matrix: Array<CharArray>, c: Char): Pair<Int, Int> {
        for (i in 0..4) for (j in 0..4) if (matrix[i][j] == c) return i to j
        return 0 to 0
    }

    fun onCipher(){
        _cipher.value = ""
        val key = _key.value!!.lowercase().replace("j", "i").filter { it in 'a'..'z' }
        val message = _message.value!!.lowercase().replace("j", "i").filter { it in 'a'..'z' }
        if (key.isEmpty()) {
            Log.d("Playfair", "Clave vacía")
            _cipher.value = message
            _message.value = ""
            _key.value = ""
            return
        }
        val matrix = generateMatrix(key)
        var msg = message.chunked(2).map {
            if (it.length == 1) it + "x"
            else if (it[0] == it[1]) "${it[0]}x" else it
        }.joinToString("")
        val pairs = msg.chunked(2)
        for (pair in pairs) {
            val (a, b) = pair[0] to pair[1]
            val (rowA, colA) = findPosition(matrix, a)
            val (rowB, colB) = findPosition(matrix, b)
            when {
                rowA == rowB -> {
                    _cipher.value += matrix[rowA][(colA + 1) % 5].toString() + matrix[rowB][(colB + 1) % 5]
                }
                colA == colB -> {
                    _cipher.value += matrix[(rowA + 1) % 5][colA].toString() + matrix[(rowB + 1) % 5][colB]
                }
                else -> {
                    _cipher.value += matrix[rowA][colB].toString() + matrix[rowB][colA]
                }
            }
        }
        _message.value = ""
        _key.value = ""
    }

    fun onDecipher(){
        _cipher.value = ""
        val key = _key.value!!.lowercase().replace("j", "i").filter { it in 'a'..'z' }
        val cipherText = _message.value!!.lowercase().replace("j", "i").filter { it in 'a'..'z' }
        if (key.isEmpty()) {
            Log.d("Playfair", "Clave vacía")
            _cipher.value = cipherText
            _message.value = ""
            _key.value = ""
            return
        }
        val matrix = generateMatrix(key)
        val pairs = cipherText.chunked(2)
        for (pair in pairs) {
            val (a, b) = pair[0] to pair[1]
            val (rowA, colA) = findPosition(matrix, a)
            val (rowB, colB) = findPosition(matrix, b)
            when {
                rowA == rowB -> {
                    _cipher.value += matrix[rowA][(colA + 4) % 5].toString() + matrix[rowB][(colB + 4) % 5]
                }
                colA == colB -> {
                    _cipher.value += matrix[(rowA + 4) % 5][colA].toString() + matrix[(rowB + 4) % 5][colB]
                }
                else -> {
                    _cipher.value += matrix[rowA][colB].toString() + matrix[rowB][colA]
                }
            }
        }
        _message.value = ""
        _key.value = ""
    }
}
