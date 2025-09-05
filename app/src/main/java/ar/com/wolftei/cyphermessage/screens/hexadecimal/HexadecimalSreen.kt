package ar.com.wolftei.cyphermessage.screens.hexadecimal

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
fun HexadecimalScreen(
    hexadecimalViewModel: HexadecimalViewModel = hiltViewModel()
) {
    Mascara(titulo = "Hexadecimal", bannerAdUnit = "ca-app-pub-6498019412327819/7530541358") { padding ->
        Content(hexadecimalViewModel, padding)
    }
}

@Composable
fun Content(hexadecimalViewModel: HexadecimalViewModel, padding: PaddingValues) {

    val context = LocalContext.current
    val message by hexadecimalViewModel.message.collectAsState("")
    val cipher by hexadecimalViewModel.cipher.collectAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        onValueChange = { hexadecimalViewModel.onValueChange(it) },
        onCipher = { hexadecimalViewModel.onCipher() },
        onDecipher = { hexadecimalViewModel.onDecipher() }
    )
    FormCypher(contenido, context, contentPadding = padding)
}
