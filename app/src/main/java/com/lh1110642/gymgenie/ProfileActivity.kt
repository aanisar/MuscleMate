package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.lh1110642.gymgenie.databinding.ActivityProfileRealBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileRealBinding
    private val authDb = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityProfileRealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_profile_real)

        authDb.currentUser?.let { user->
            binding.userNameTextView.text = user.displayName
            binding.emailTextView.text = user.email
        }
        binding.calculateButton.setOnClickListener{
            startActivity(Intent(this, BodyCalculationActivity::class.java))
        }

        binding.viewWorkoutButton.setOnClickListener {
        startActivity(Intent(this,WorkoutActivity::class.java))
        }
        binding.viewStatsButton.setOnClickListener {
            startActivity(Intent(this,displayStatsActivity::class.java))
        }

        binding.oneMaxRepBtn.setOnClickListener {
            startActivity(Intent(this,OneRepMaxCalcActivity::class.java))
        }

        binding.bmr.setOnClickListener {
            startActivity(Intent(this,bmrActivity::class.java))
        }

        binding.signOutButton.setOnClickListener {
            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
        }

        binding.bottomNavigator.selectedItemId = R.id.profile
        binding.bottomNavigator.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.anatomy -> {
                    startActivity(Intent(this, BrowsingActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.workout -> {
                    startActivity(Intent(this, WorkoutActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener super.onOptionsItemSelected(menuItem)
            }
        }

    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        //different menu options
//        return when (item.itemId) {
//            R.id.anatomy -> {
//                startActivity(Intent(this,BrowsingActivity::class.java))
//                return true
//            }
//            R.id.workout -> {
//                startActivity(Intent(this,WorkoutActivity::class.java))
//                return true
//            }
//            R.id.profile -> {
//                startActivity(Intent(this,ProfileActivity::class.java))
//                return true
//            }
//
//            else -> super.onOptionsItemSelected(item)
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
}