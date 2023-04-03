package com.lh1110642.gymgenie

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1110642.gymgenie.databinding.ActivityOneRepMaxCalcBinding

class OneRepMaxCalcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOneRepMaxCalcBinding
    private lateinit var bodyStats: BodyStats
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

        var weightText = findViewById<EditText>(R.id.wtLiftedText)
        var repsText = findViewById<EditText>(R.id.repsText)
        var calculateButton = findViewById<Button>(R.id.onerepmaxCalcBtn)
        val resultOneRepTextView = findViewById<TextView>(R.id.onerepmaxResult)
        databaseRead()
        calculateButton.setOnClickListener {
            reps = repsText.text.toString()
            weight = weightText.text.toString()

            if(validateInput(reps, weight)) {
                resultWt = (weight.toFloat()*(1+(reps.toFloat()/30))).toDouble()
                val resultFinal = String.format("%.2f", resultWt).toFloat()

                binding.onerepmaxResult.text = resultFinal.toString()

                bodyStats = BodyStats(reps,weight,resultFinal.toString())


            }
        }

        binding.onerepmaxSaveBtn.setOnClickListener {
            if(bodyStats.statOne.isNotEmpty()) {
                database(bodyStats)
                Toast.makeText(this,  "Successfully added to the database", Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this,  "Fields are empty", Toast.LENGTH_LONG).show()
        }

        binding.btnSearch2.setOnClickListener{

            val uri: Uri =
                Uri.parse("https://www.verywellfit.com/what-is-repetition-maximum-and-1rm-3498379") // missing 'http://' will cause crashed

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
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

    fun database(stats: BodyStats){

        var userId = Firebase.auth.currentUser?.uid
        var db = FirebaseFirestore.getInstance().collection("1RM")
        if (userId != null) {
            db.document(userId)
                .delete()
                .addOnSuccessListener { Log.d(ContentValues.TAG, "DB_DELETE COMPLETE")

                }
                .addOnFailureListener { e ->
                    Log.w(
                        ContentValues.TAG,
                        "Error deleting document",
                        e
                    )


                }
        }


        db = FirebaseFirestore.getInstance().collection("1RM")

        var id = Firebase.auth.currentUser?.uid

        Log.w("DB_Parth", auth.currentUser!!.uid)
        if (stats != null) { //Gets the users ID
            stats.setuid(auth.currentUser!!.uid)
        }
        //Writes to the database
        if (stats != null) {
            bodyStats.setstatOne(bodyStats.statOne)
            bodyStats.setstatTwo(bodyStats.statTwo)
            bodyStats.setStat(bodyStats.stat)
            if (id != null) {
                db.document(id).set(stats)
                    .addOnSuccessListener {  Log.w("DB_Parth", "ADDED") }
                    .addOnFailureListener{ Log.w("DB_Fail", it.localizedMessage)}
            }
        }


    }
    fun databaseRead(){
        //database call
        val userId = Firebase.auth.currentUser?.uid
        val db = FirebaseFirestore.getInstance().collection("1RM").whereEqualTo("uid", userId)
        db.get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    var valueHolder = document.toObject(BodyStats::class.java)
                    binding.wtLiftedText.setText( valueHolder.statTwo)
                    binding.repsText.setText(valueHolder.statOne)


                }
            }



    }


}