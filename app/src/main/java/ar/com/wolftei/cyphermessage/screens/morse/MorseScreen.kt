package ar.com.wolftei.cyphermessage.screens.morse

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypher

@Composable
fun MorseScreen(
    morseViewModel: MorseViewModel = hiltViewModel()
) {
    Mascara(titulo = "Morse") {
        Content(morseViewModel)
    }
}

@Composable
fun Content(morseViewModel: MorseViewModel) {
    val context = LocalContext.current
    val message by morseViewModel.message.observeAsState("")
    val cipher by morseViewModel.cipher.observeAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        onValueChange = { morseViewModel.onValueChange(it) },
        onCipher = { morseViewModel.onCipher() },
        onDecipher = { morseViewModel.onDecipher() }
    )

    FormCypher(contenido, context)
}
