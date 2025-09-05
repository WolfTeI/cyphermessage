package ar.com.wolftei.cyphermessage.screens.vigenere

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.platform.LocalContext
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypherDesp

@Composable
fun VigenereScreen(
    vigenereViewModel: VigenereViewModel = hiltViewModel()
) {
    Mascara("Vigenere", "ca-app-pub-6498019412327819/2689278215") { padding ->
        Content(vigenereViewModel, padding)

    }
}

@Composable
fun Content(vigenereViewModel: VigenereViewModel, padding: PaddingValues) {
    val context = LocalContext.current
    val message by vigenereViewModel.message.collectAsState("")
    val cipher by vigenereViewModel.cipher.collectAsState("")
    val desp by vigenereViewModel.desp.collectAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        desp = desp,
        onValueChange = { vigenereViewModel.onValueChange(it) },
        onValueChangeDesp = { vigenereViewModel.onValueChangeDesp(it) },
        onCipher = { vigenereViewModel.onCipher() },
        onDecipher = { vigenereViewModel.onDecipher() }
    )

    FormCypherDesp(contenido, context, "Clave", false, contentPadding = padding)
}
