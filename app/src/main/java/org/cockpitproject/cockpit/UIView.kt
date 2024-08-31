package org.cockpitproject.cockpit

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import org.cockpitproject.cockpit.PrefsHelper.getGlobalVariable
//import android.view.ViewGroup
//import androidx.activity.compose.setContent
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView

// Custom WebView client to allow insecure websites
class CustomWebViewClient : WebViewClient() {
    @SuppressLint("WebViewClientOnReceivedSslError")
    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        handler?.proceed()
    }
}

// Also jetpack compose code
//@Composable
//fun UIView() {
//    val context = LocalContext.current
//    val url = PrefsHelper.getGlobalVariable(context)
//    AndroidView(factory = {
//        WebView(it).apply {
//            layoutParams = ViewGroup.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT
//            )}
//        }, update = {
//            it.webViewClient = CustomWebViewClient()
//            it.settings.javaScriptEnabled = true
//            it.loadUrl(url)
//        })
//}

class UIViewActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled") // Prevent the Android Studio lint from yelling at us for enabling JS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uiview = WebView(this)
        uiview.webViewClient = CustomWebViewClient()
        uiview.settings.javaScriptEnabled = true
        uiview.loadUrl(getGlobalVariable(this))
        setContentView(uiview   )
        // Also jetpack compose code
//        setContent {
//            UIView()
//        }
    }
}