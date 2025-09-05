package ar.com.wolftei.cyphermessage.model.adMob

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@SuppressLint("VisibleForTests")
@Composable
fun BannerAd(adUnitId: String) {
    AndroidView(
    modifier = Modifier.fillMaxWidth(),
    factory = { context ->
        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            setAdUnitId(adUnitId)
            loadAd(AdRequest.Builder().build())
        }
    }
)
}