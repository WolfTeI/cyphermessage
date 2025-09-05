package ar.com.wolftei.cyphermessage.screens.binary

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.wolftei.cyphermessage.dataClass.FormDataClass
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import ar.com.wolftei.cyphermessage.model.formcypher.FormCypher

@Composable
fun BinaryScreen(
    binaryViewModel: BinaryViewModel = hiltViewModel()
) {
    Mascara("Binary", "ca-app-pub-6498019412327819/3866628518") { padding ->
        Content(binaryViewModel, padding)
    }
}

@Composable
fun Content(binaryViewModel: BinaryViewModel, padding: PaddingValues) {
    val context = LocalContext.current
    val message by binaryViewModel.message.collectAsState("")
    val cipher by binaryViewModel.cipher.collectAsState("")

    val contenido = FormDataClass(
        message = message,
        cipher = cipher,
        onValueChange = { binaryViewModel.onValueChange(it) },
        onCipher = { binaryViewModel.onCipher() },
        onDecipher = { binaryViewModel.onDecipher() }
    )
    FormCypher(contenido, context, contentPadding = padding)
}

@Preview(showSystemUi = true)
@Composable
fun PreviewBinaryScreen() {
    BinaryScreen()
}
