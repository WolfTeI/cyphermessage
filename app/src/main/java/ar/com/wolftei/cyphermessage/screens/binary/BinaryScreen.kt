package ar.com.wolftei.cyphermessage.screens.binary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypher

@Composable
fun BinaryScreen(
    binaryViewModel: BinaryViewModel = hiltViewModel()
) {
    Mascara("Binary") {
        Content(binaryViewModel)
    }
}

@Composable
fun Content(binaryViewModel: BinaryViewModel) {
    val context = LocalContext.current
    val message by binaryViewModel.message.observeAsState("")
    val cipher by binaryViewModel.cipher.observeAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        onValueChange = { binaryViewModel.onValueChange(it) },
        onCipher = { binaryViewModel.onCipher() },
        onDecipher = { binaryViewModel.onDecipher() }
    )
    FormCypher(contenido, context)
}
