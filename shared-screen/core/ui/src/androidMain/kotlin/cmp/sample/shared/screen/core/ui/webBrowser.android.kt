package cmp.sample.shared.screen.core.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

actual class WebBrowser(private val context: Context) {
  actual fun open(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
      data = Uri.parse(url)
    }
    ContextCompat.startActivity(context, intent, null)
  }
}
