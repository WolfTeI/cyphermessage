package ar.com.wolftei.cyphermessage.model.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import ar.com.wolftei.cyphermessage.model.adMob.BannerAd

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mascara(titulo: String, bannerAdUnit: String? = null, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        topBar = { Toolbar(titulo) },
        bottomBar = {
            if (bannerAdUnit != null) {
                BannerAd(bannerAdUnit)
            }
        },
        content = { padding -> content(padding) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(titulo: String) {
    TopAppBar(title = { Text(text = titulo) })
}