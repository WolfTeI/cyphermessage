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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.com.wolftei.cyphermessage.MainActivityViewModel
import ar.com.wolftei.cyphermessage.navigation.AppScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainActivityViewModel = hiltViewModel()

) {
    val isDark = viewModel.isDarkTheme.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getThemePreference().collect{
            viewModel.updateThemePreference(it)
        }
    }
    Scaffold(
        topBar = { Toolbar(isDark.value, viewModel) },
        content = { Content(navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    isDark: Boolean,
    viewModel: MainActivityViewModel
    ){

    val scope = rememberCoroutineScope()

    var isDarkTheme by remember { mutableStateOf(isDark) }

    TopAppBar(
        title = { Text(text = "Cypher Message") },
        actions = {
            Icon(
                if(isDark) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                "Dark Mode",
                modifier = Modifier.clickable {
                    isDarkTheme = !isDarkTheme
                    scope.launch(Dispatchers.IO) {
                        viewModel.saveThemePreference(isDarkTheme)
                    }
                }
            )
        }
    )
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
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.surface
        )
        ButtonNavigate("Binary") { navController.navigate(AppScreens.Binary.route) }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.surface
        )
        ButtonNavigate("Hexadecimal") { navController.navigate(AppScreens.Hexadecimal.route) }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.surface
        )
        ButtonNavigate(name = "Ocatal") { navController.navigate(AppScreens.Octal.route) }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.surface
        )
        ButtonNavigate("Vigenere") { navController.navigate(AppScreens.Vigenere.route) }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.surface
        )
        ButtonNavigate("Caesar") { navController.navigate(AppScreens.Caesar.route) }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.surface
        )
        ButtonNavigate("Morse") { navController.navigate(AppScreens.Morse.route) }

    }
}

//creaci�n de bot�n navegable
@Composable
fun ButtonNavigate(name:String, navigate: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
            .clickable { navigate() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            color = MaterialTheme.colorScheme.surfaceVariant,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            )
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = NavController(LocalContext.current))
}