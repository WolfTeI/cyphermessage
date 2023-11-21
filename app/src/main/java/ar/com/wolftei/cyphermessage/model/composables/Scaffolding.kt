package ar.com.wolftei.cyphermessage.model.composables

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mascara(titulo: String, content: @Composable () -> Unit) {
    Scaffold(
        topBar = { Toolbar(titulo) },
        content = { content() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(titulo: String) {
    TopAppBar(title = { Text(text = titulo) })
}