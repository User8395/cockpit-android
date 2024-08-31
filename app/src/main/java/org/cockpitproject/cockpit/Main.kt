package org.cockpitproject.cockpit

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import com.google.android.material.color.DynamicColors
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// This project uses Jetpack Compose
// idk why i switched, i think it gives more features

// Used to pass url to UIView.kt
object PrefsHelper {
    private const val PREFS_NAME = "MyAppPrefs"
    private const val KEY_GLOBAL_VARIABLE = "global_variable"

    fun setGlobalVariable(context: Context, value: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_GLOBAL_VARIABLE, value).apply()
    }

    fun getGlobalVariable(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_GLOBAL_VARIABLE, "") ?: ""
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use xml
        setContentView(R.layout.main)
        findViewById<Button>(R.id.connect).setOnClickListener {
            PrefsHelper.setGlobalVariable(this,
                findViewById<EditText>(R.id.address).text.toString()
            )
            startActivity(Intent(this, UIViewActivity::class.java))
        }

        // Use jetpack compose code
//        setContent {
//            Main()
//        }
    }
}


// Jetpack Compose code, if XML doesn't work then I'll use this
//@Composable
//fun Main() {
//    var url by remember { mutableStateOf("") }
//    val context = LocalContext.current
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        TextField(
//            value = url,
//            onValueChange = { url = it },
//            label = { Text("Label") }
//        )
//
//        Button(onClick = {
//            PrefsHelper.setGlobalVariable(context, url)
//            context.startActivity(Intent(context, UIViewActivity::class.java).putExtra("url", url))
//        }) {
//            Text("Connect")
//        }
//    }
//}

// This is supposed to make the app use the color scheme used by Android
class Cockpit : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}