package ar.com.wolftei.cyphermessage.screens.xor

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
fun XorScreen(viewModel: XorViewModel = hiltViewModel()) {
    Mascara("XOR", "ca-app-pub-6498019412327819/6619777769") { padding ->
        Content(viewModel, padding)
    }
}

@Composable
fun Content(viewModel: XorViewModel, padding: PaddingValues) {
    val context = LocalContext.current
    val message by viewModel.message.collectAsState("")
    val key by viewModel.key.collectAsState("")
    val cipher by viewModel.cipher.collectAsState("")

    val contenido = FormDataClass(
        message = message,
        desp = key,
        cipher = cipher,
        onValueChange = { viewModel.onValueChange(it) },
        onValueChangeDesp = { viewModel.onValueChangeKey(it) },
        onCipher = { viewModel.onCipher() },
        onDecipher = { viewModel.onDecipher() }
    )
    FormCypherDesp(contenido, context, "Clave", false, contentPadding = padding)
}
