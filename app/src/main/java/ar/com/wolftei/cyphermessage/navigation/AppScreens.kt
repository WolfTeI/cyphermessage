package ar.com.wolftei.cyphermessage.navigation

sealed class AppScreens(val route: String) {
    object Splash : AppScreens("splash")
    object Home : AppScreens("home")

//    Cypher Screens
    object Ascii : AppScreens("ascii")
    object Binary : AppScreens("binary")
    object Hexadecimal : AppScreens("hexadecimal")
    object Octal : AppScreens("octal")
    object Caesar : AppScreens("caesar")
    object Vigenere : AppScreens("vigenere")
    object Morse : AppScreens("morse")

}