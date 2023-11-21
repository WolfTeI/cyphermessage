package ar.com.wolftei.cyphermessage.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.com.wolftei.cyphermessage.screens.HomeScreen
import ar.com.wolftei.cyphermessage.screens.ascii.AsciiScreen
import ar.com.wolftei.cyphermessage.screens.binary.BinaryScreen
import ar.com.wolftei.cyphermessage.screens.cesar.CesarScreen
import ar.com.wolftei.cyphermessage.screens.hexadecimal.HexadecimalScreen
import ar.com.wolftei.cyphermessage.screens.morse.MorseScreen
import ar.com.wolftei.cyphermessage.screens.octal.OctalScreen
import ar.com.wolftei.cyphermessage.screens.vigenere.VigenereScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Home.route) {

        composable(route = AppScreens.Home.route) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.Ascii.route) {
            AsciiScreen()
        }
        composable(route = AppScreens.Binary.route) {
            BinaryScreen()
        }
        composable(route = AppScreens.Hexadecimal.route) {
            HexadecimalScreen()
        }
        composable(route = AppScreens.Octal.route) {
            OctalScreen()
        }
        composable(route = AppScreens.Vigenere.route) {
            VigenereScreen()
        }
        composable(route = AppScreens.Caesar.route) {
            CesarScreen()
        }
        composable(route = AppScreens.Morse.route) {
            MorseScreen()
        }
    }

}