package com.example.gamesbreak.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.gamesbreak.R
import com.example.gamesbreak.activities.ProfileHome.ProfileFragment
import com.example.gamesbreak.activities.StoreHome.StoreFragment
import com.example.gamesbreak.data.UserCredentials
import com.example.gamesbreak.data.UserPreferences
import com.example.gamesbreak.databinding.ActivityHomeBinding
import com.example.gamesbreak.ui.authentication.AuthenticationActivity
import com.example.gamesbreak.utils.startNewActivity
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var user: UserCredentials? = null
    private lateinit var fragmentManager: FragmentManager
    private lateinit var currentFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager = supportFragmentManager
        showFragment(StoreFragment())


        val storage = UserPreferences(this)
        storage.userCredentials.asLiveData().observe(this, Observer { credentials ->
            user = credentials
            if (credentials != null) {
                binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.Store -> {
                            showFragment(StoreFragment())
                            true
                        }
                        R.id.Profile -> {
                            showFragment(ProfileFragment())
                            true
                        }
                        else -> false
                    }
                }
            } else {
                startNewActivity(AuthenticationActivity::class.java)
            }
        })
    }
    private fun showFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()
        currentFragment = fragment
    }
}




//    }
//    private fun setUpClickListeners() {
//        val userId = this.user?.id
//
//        binding.cvMyGames.setOnClickListener {
//            val intentMyGames = Intent(this, GameOfUserActivity::class.java)
//            intentMyGames.putExtra("ID_USER", userId)
//            startActivity(intentMyGames)
//        }
//
//        binding.cvBuyGames.setOnClickListener {
//            val intentGameRecyclerActivity = Intent(this, GameToSaleActivity::class.java)
//            intentGameRecyclerActivity.putExtra("ID_USER", userId)
//            startActivity(intentGameRecyclerActivity)
//        }
//
//    }
//}