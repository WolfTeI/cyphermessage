package ar.com.wolftei.cyphermessage.screens.ascii

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypher

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AsciiScreen(
    asciiViewModel: AsciiViewModel = hiltViewModel()
) {
    Mascara("ASCII", "ca-app-pub-6498019412327819/3782868039") { padding ->
        Content(asciiViewModel, padding)
    }
}

@Composable
fun Content(asciiViewModel: AsciiViewModel, padding: PaddingValues) {
    val context = LocalContext.current
    val message by asciiViewModel.message.collectAsState("")
    val cipher by asciiViewModel.cipher.collectAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        onValueChange = { asciiViewModel.onValueChange(it) },
        onCipher = { asciiViewModel.onCipher() },
        onDecipher = { asciiViewModel.onDecipher() }
    )
    FormCypher(contenido, context, contentPadding = padding)
}


@Preview(showSystemUi = true)
@Composable
fun PreviewAsciiScreen() {
    AsciiScreen()
}
