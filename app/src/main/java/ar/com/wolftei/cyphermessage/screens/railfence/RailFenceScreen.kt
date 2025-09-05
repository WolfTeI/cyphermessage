package ar.com.wolftei.cyphermessage.screens.railfence

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.platform.LocalContext
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.adMob.BannerAd
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypherDesp
import ar.com.wolftei.cyphermessage.screens.playfair.Content

@Composable
fun RailFenceScreen(viewModel: RailFenceViewModel = hiltViewModel()) {
    Mascara("Rail Fence", "ca-app-pub-6498019412327819/3028835355") { padding ->
        Content(viewModel, padding)
    }
}

@Composable
fun Content(viewModel: RailFenceViewModel, padding: PaddingValues) {
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
    FormCypherDesp(contenido, context, "Rieles", true, contentPadding = padding)
}
