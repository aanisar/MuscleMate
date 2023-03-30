package com.lh1110642.gymgenie

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lh1110642.gymgenie.databinding.ActivityOneRepMaxCalcBinding

class OneRepMaxCalcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOneRepMaxCalcBinding
    private lateinit var oneRepMax: OneRepMax
    var auth = Firebase.auth
    var weight = ""
    var reps = ""
    //this is the result of oneRepMax, its in double
    var resultWt = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOneRepMaxCalcBinding.inflate(layoutInflater)
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

        val weightText = findViewById<EditText>(R.id.wtLiftedText)
        val repsText = findViewById<EditText>(R.id.repsText)
        val calculateButton = findViewById<Button>(R.id.onerepmaxCalcBtn)
        val resultOneRepTextView = findViewById<TextView>(R.id.onerepmaxResult)

        calculateButton.setOnClickListener {
            reps = repsText.text.toString()
            weight = weightText.text.toString()

            if(validateInput(reps, weight)) {
                resultWt = (weight.toFloat()*(1+(reps.toFloat()/30))).toDouble()
                val resultFinal = String.format("%.2f", resultWt).toFloat()

                binding.onerepmaxResult.text = resultFinal.toString()

            }
        }

    }

    private fun validateInput(reps:String?, weight: String?):Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,  "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            reps.isNullOrEmpty() -> {
                Toast.makeText(this,  "Reps is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }


}