package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_real)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //different menu options
        return when (item.itemId) {
            R.id.anatomy -> {
                startActivity(Intent(this,BrowsingActivity::class.java))
                return true
            }
            R.id.workout -> {
                startActivity(Intent(this,WorkoutActivity::class.java))
                return true
            }
            R.id.profile -> {
                startActivity(Intent(this,ProfileActivity::class.java))
                return true
            }
            R.id.signOut -> {
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        startActivity(Intent(this,LoginActivity::class.java))
                    }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }
}