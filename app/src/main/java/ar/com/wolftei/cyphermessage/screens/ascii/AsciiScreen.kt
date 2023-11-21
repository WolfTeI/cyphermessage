package ar.com.wolftei.cyphermessage.screens.ascii

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.livedata.observeAsState
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
    Mascara("ASCII"){
        Content(asciiViewModel)
    }
}



@Composable
fun Content(asciiViewModel: AsciiViewModel) {
    val context = LocalContext.current
    val message by asciiViewModel.message.observeAsState("")
    val cipher by asciiViewModel.cipher.observeAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        onValueChange = { asciiViewModel.onValueChange(it) },
        onCipher = { asciiViewModel.onCipher() },
        onDecipher = { asciiViewModel.onDecipher() }
    )
    FormCypher(contenido, context)
}


@Preview(showSystemUi = true)
@Composable
fun PreviewAsciiScreen() {
    AsciiScreen()
}
