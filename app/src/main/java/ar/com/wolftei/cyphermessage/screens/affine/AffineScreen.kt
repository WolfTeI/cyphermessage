package ar.com.wolftei.cyphermessage.screens.affine

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.com.wolftei.cyphermessage.model.composables.Mascara
import androidx.compose.foundation.layout.PaddingValues

@Composable
fun AffineScreen(viewModel: AffineViewModel = hiltViewModel()) {
    Mascara("Affine", "ca-app-pub-6498019412327819/3220407049") { padding ->
        AffineContent(viewModel, padding)
    }
}

@Composable
fun AffineContent(viewModel: AffineViewModel, padding: PaddingValues) {
    val message by viewModel.message.collectAsState("")
    val a by viewModel.a.collectAsState("")
    val b by viewModel.b.collectAsState("")
    val cipher by viewModel.cipher.collectAsState("")

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Cifrado Affine", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = message,
            onValueChange = { viewModel.onValueChange(it) },
            label = { Text("Mensaje") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = a,
            onValueChange = { viewModel.onValueChangeA(it) },
            label = { Text("Clave a (coprimo con 26)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = b,
            onValueChange = { viewModel.onValueChangeB(it) },
            label = { Text("Clave b") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { viewModel.onCipher() }, modifier = Modifier.weight(1f)) {
                Text("Cifrar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { viewModel.onDecipher() }, modifier = Modifier.weight(1f)) {
                Text("Descifrar")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Resultado:", style = MaterialTheme.typography.titleMedium)
        Text(text = cipher, style = MaterialTheme.typography.bodyLarge)
    }
}
