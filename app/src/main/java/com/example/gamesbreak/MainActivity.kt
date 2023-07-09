package com.example.gamesbreak


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesbreak.data.UserPreferences
import com.example.gamesbreak.ui.authentication.AuthenticationActivity
import com.example.gamesbreak.activities.HomeActivity
import com.example.gamesbreak.utils.startNewActivity
import androidx.lifecycle.asLiveData
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val storage = UserPreferences(this)

        storage.userCredentials.asLiveData().observe(this, Observer {
            val activity = if(it == null) AuthenticationActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }

}