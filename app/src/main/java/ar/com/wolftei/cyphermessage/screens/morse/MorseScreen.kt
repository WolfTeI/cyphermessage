package ar.com.wolftei.cyphermessage.screens.morse

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypher

@Composable
fun MorseScreen(
    morseViewModel: MorseViewModel = hiltViewModel()
) {
    Mascara(titulo = "Morse", bannerAdUnit = "ca-app-pub-6498019412327819/5315441553") { padding ->
        Content(morseViewModel, padding)
    }
}

@Composable
fun Content(morseViewModel: MorseViewModel, padding: PaddingValues) {
    val context = LocalContext.current
    val message by morseViewModel.message.collectAsState("")
    val cipher by morseViewModel.cipher.collectAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        onValueChange = { morseViewModel.onValueChange(it) },
        onCipher = { morseViewModel.onCipher() },
        onDecipher = { morseViewModel.onDecipher() }
    )

    FormCypher(contenido, context, contentPadding = padding)
}
