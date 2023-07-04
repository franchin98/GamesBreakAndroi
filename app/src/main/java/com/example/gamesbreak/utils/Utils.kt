package com.example.gamesbreak.utils

import android.app.Activity
import android.content.Intent

fun <A : Activity> Activity.startNewActivity(activity: Class<A>, params: Array<Pair<String, String>>?) {
    val intent = Intent(this, activity).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        params?.forEach { (key, value) ->
            putExtra(key, value)
        }
    }
    startActivity(intent)
}