package com.ssverma.core.navigation.dispatcher

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.dispatchImplicitIntent(
    intent: Intent,
    onDestinationNotFound: () -> Unit = {}
) {
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        onDestinationNotFound()
    }
}

object IntentDispatcher {
    fun Context.dispatchBrowserIntent(
        webUrl: String,
        onNoDestinationFound: () -> Unit = {}
    ) {
        dispatchImplicitIntent(
            intent = CommonIntent.browserIntent(webUrl = webUrl),
            onDestinationNotFound = onNoDestinationFound
        )
    }
}

object CommonIntent {
    fun browserIntent(webUrl: String): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse(webUrl)
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }

    fun shareTextIntent(text: String): Intent {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        return Intent.createChooser(sendIntent, null)
    }
}