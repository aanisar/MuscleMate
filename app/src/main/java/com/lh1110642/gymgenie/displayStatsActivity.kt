package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1110642.gymgenie.databinding.ActivityDisplayStatsBinding
import com.lh1110642.gymgenie.databinding.ActivityProfileRealBinding


private val authDb = FirebaseAuth.getInstance()
class displayStatsActivity : AppCompatActivity() {
    var auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityDisplayStatsBinding
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

        //database call
        var userId = Firebase.auth.currentUser?.uid
        var db = FirebaseFirestore.getInstance().collection("bmi").whereEqualTo("uid", userId)
        db.get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    var valueHolder = document.toObject(BodyStats::class.java)
                    Log.w("DB_Mason", valueHolder.getStat())
                    binding.lblBMI.text = valueHolder.getStat().take(5)

                }
            }
         userId = Firebase.auth.currentUser?.uid
        db = FirebaseFirestore.getInstance().collection("1RM").whereEqualTo("uid", userId)
        db.get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    var valueHolder = document.toObject(BodyStats::class.java)
                    Log.w("DB_Mason", valueHolder.getStat())
                    binding.lbl1RM.text = valueHolder.getStat().take(3)

                }
            }
        userId = Firebase.auth.currentUser?.uid
        db = FirebaseFirestore.getInstance().collection("BMR").whereEqualTo("uid", userId)
        db.get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    var valueHolder = document.toObject(BodyStats::class.java)
                    Log.w("DB_Mason", valueHolder.getStat())
                    binding.lblBMR.text = valueHolder.getStat().take(4)

                }
            }

                binding.btnBMI.setOnClickListener {
                    startActivity(Intent(this, BodyCalculationActivity::class.java))
                }

                binding.btn1RM.setOnClickListener {
                    startActivity(Intent(this, OneRepMaxCalcActivity::class.java))
                }

                binding.btnBMR.setOnClickListener {
                    startActivity(Intent(this, bmrActivity::class.java))
                }

    }
}