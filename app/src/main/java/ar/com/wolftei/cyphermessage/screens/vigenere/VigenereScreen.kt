package ar.com.wolftei.cyphermessage.screens.vigenere

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypherDesp

@Composable
fun VigenereScreen(
    vigenereViewModel: VigenereViewModel = hiltViewModel()
) {
    Mascara("Vígenere") {
        Content(vigenereViewModel)

    }
}

@Composable
fun Content(vigenereViewModel: VigenereViewModel) {

    val context = LocalContext.current
    val message by vigenereViewModel.message.observeAsState("")
    val cipher by vigenereViewModel.cipher.observeAsState("")
    val desp by vigenereViewModel.desp.observeAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        desp = desp,
        onValueChange = { vigenereViewModel.onValueChange(it) },
        onValueChangeDesp = { vigenereViewModel.onValueChangeDesp(it) },
        onCipher = { vigenereViewModel.onCipher() },
        onDecipher = { vigenereViewModel.onDecipher() }
    )

    FormCypherDesp(contenido, context, "Clave",false)

}
