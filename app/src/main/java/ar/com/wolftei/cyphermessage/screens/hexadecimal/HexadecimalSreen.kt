package ar.com.wolftei.cyphermessage.screens.hexadecimal

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
fun HexadecimalScreen(
    hexadecimalViewModel: HexadecimalViewModel = hiltViewModel()
) {
    Mascara(titulo = "Hexadecimal") {
        Content(hexadecimalViewModel)
    }
}

@Composable
fun Content(hexadecimalViewModel: HexadecimalViewModel) {

    val context = LocalContext.current
    val message by hexadecimalViewModel.message.observeAsState("")
    val cipher by hexadecimalViewModel.cipher.observeAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        onValueChange = { hexadecimalViewModel.onValueChange(it) },
        onCipher = { hexadecimalViewModel.onCipher() },
        onDecipher = { hexadecimalViewModel.onDecipher() }
    )
    FormCypher(contenido, context)
}
