package ar.com.wolftei.cyphermessage.screens.cesar

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
fun CesarScreen(
    cesarViewModel: CesarViewModel = hiltViewModel()
) {
    Mascara(titulo = "Cesar") {
        Content(cesarViewModel)
    }
}

@Composable
fun Content(cesarViewModel: CesarViewModel) {
    val context = LocalContext.current
    val message by cesarViewModel.message.observeAsState("")
    val cipher by cesarViewModel.cipher.observeAsState("")
    val desp by cesarViewModel.desp.observeAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        desp = desp,
        onValueChange = { cesarViewModel.onValueChange(it) },
        onValueChangeDesp = { cesarViewModel.onValueChangeDesp(it) },
        onCipher = { cesarViewModel.onCipher() },
        onDecipher = { cesarViewModel.onDecipher() }
    )

    FormCypherDesp(contenido, context, "Desplazamiento", true)
}
