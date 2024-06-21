package ar.com.wolftei.cyphermessage

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CypherMessage: Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {} // Inicializa AdMob
    }
}