package ar.com.wolftei.cyphermessage.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ar.com.wolftei.cyphermessage.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { Toolbar() },
        content = { Content(navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(){
    TopAppBar(title = { Text(text = "Cypher Message") })
}

@Composable
fun Content(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 80.dp)
        .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonNavigate("ASCII") { navController.navigate(AppScreens.Ascii.route) }
        Divider(modifier = Modifier.padding(vertical = 16.dp), color = MaterialTheme.colorScheme.surface)
        ButtonNavigate("Binary") { navController.navigate(AppScreens.Binary.route) }
        Divider(modifier = Modifier.padding(vertical = 16.dp), color = MaterialTheme.colorScheme.surface)
        ButtonNavigate("Hexadecimal") { navController.navigate(AppScreens.Hexadecimal.route) }
        Divider(modifier = Modifier.padding(vertical = 16.dp), color = MaterialTheme.colorScheme.surface)
        ButtonNavigate("Vigenere") { navController.navigate(AppScreens.Vigenere.route) }
        Divider(modifier = Modifier.padding(vertical = 16.dp), color = MaterialTheme.colorScheme.surface)
        ButtonNavigate("Caesar") { navController.navigate(AppScreens.Caesar.route) }
        Divider(modifier = Modifier.padding(vertical = 16.dp), color = MaterialTheme.colorScheme.surface)
        ButtonNavigate("Morse") { navController.navigate(AppScreens.Morse.route) }

    }
}

//creaci�n de bot�n navegable
@Composable
fun ButtonNavigate(name:String, navigate: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable { navigate() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            )
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = NavController(LocalContext.current))
}