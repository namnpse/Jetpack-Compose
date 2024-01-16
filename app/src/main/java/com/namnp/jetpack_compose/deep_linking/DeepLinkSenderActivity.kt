package com.namnp.jetpack_compose.deep_linking

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.namnp.jetpack_compose.ui.theme.JetpackComposeTheme

@ExperimentalPermissionsApi
class DeepLinkSenderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://namnpse.com/123")
                        )
                        val pendingIntent = TaskStackBuilder.create(applicationContext).run {
                            addNextIntent(intent)
                            getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                                )
                        }
                        pendingIntent.send()
                    }) {
                        Text(text = "Trigger deeplink")
                    }
                }
            }
        }
    }
}