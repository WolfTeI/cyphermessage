package ar.com.wolftei.cyphermessage.screens.octal

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypher

@Composable
fun OctalScreen(
    octalViewModel: OctalViewModel = OctalViewModel()
) {
    Mascara("Octal", "ca-app-pub-6498019412327819/9515089751") { padding ->
        Content(octalViewModel, padding)
    }
}

@Composable
fun Content(octalViewModel: OctalViewModel, padding: PaddingValues) {
    val context = LocalContext.current
    val message by octalViewModel.message.collectAsState("")
    val cipher by octalViewModel.cipher.collectAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        onValueChange = { octalViewModel.onValueChange(it) },
        onCipher = { octalViewModel.onCipher() },
        onDecipher = { octalViewModel.onDecipher() }
    )

    FormCypher(contenido, context, contentPadding = padding)
}
