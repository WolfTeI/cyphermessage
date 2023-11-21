package ar.com.wolftei.cyphermessage.model.funtions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import java.util.Date

fun clipBoard(context: Context, text: String){
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip: ClipData = ClipData.newPlainText("copy", text)
    clipboard.setPrimaryClip(clip)
}

fun shareMessage(message: String, context: Context){
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = "text/plain"
    }
    val shared = Intent.createChooser(sendIntent, null)
    startActivity(context, shared, Bundle.EMPTY)
}

fun fechaActual(): String? {
    val sdf = SimpleDateFormat("dd/M/yyyy")
    return sdf.format(Date())
}
fun horaActual(): String? {
    val sdf = SimpleDateFormat("HH:mm")
    return sdf.format(Date())
}