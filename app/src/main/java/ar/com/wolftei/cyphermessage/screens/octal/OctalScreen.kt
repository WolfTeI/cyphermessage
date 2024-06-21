package ar.com.wolftei.cyphermessage.screens.octal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.banner.BannerAd
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypher

@Composable
fun OctalScreen(
    octalViewModel: OctalViewModel = OctalViewModel()
) {
    Mascara("Octal") {
        Content(octalViewModel)
    }
}

@Composable
fun Content(octalViewModel: OctalViewModel) {
    val context = LocalContext.current
    val message by octalViewModel.message.observeAsState("")
    val cipher by octalViewModel.cipher.observeAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        onValueChange = { octalViewModel.onValueChange(it) },
        onCipher = { octalViewModel.onCipher() },
        onDecipher = { octalViewModel.onDecipher() }
    )

    FormCypher(contenido, context)
    BannerAd("ca-app-pub-6498019412327819/9916444816")
}
