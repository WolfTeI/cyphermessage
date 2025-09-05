package ar.com.wolftei.cyphermessage.screens.cesar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypherDesp

@Composable
fun CesarScreen(
    cesarViewModel: CesarViewModel = hiltViewModel()
) {
    Mascara(titulo = "Cesar", bannerAdUnit = "ca-app-pub-6498019412327819/3141253096") { padding ->
        Content(cesarViewModel, padding)
    }
}

@Composable
fun Content(cesarViewModel: CesarViewModel, padding: PaddingValues) {
    val context = LocalContext.current
    val message by cesarViewModel.message.collectAsState("")
    val cipher by cesarViewModel.cipher.collectAsState("")
    val desp by cesarViewModel.desp.collectAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        desp = desp,
        onValueChange = { cesarViewModel.onValueChange(it) },
        onValueChangeDesp = { cesarViewModel.onValueChangeDesp(it) },
        onCipher = { cesarViewModel.onCipher() },
        onDecipher = { cesarViewModel.onDecipher() }
    )

    FormCypherDesp(contenido, context, "Desplazamiento", true, contentPadding = padding)
}
