package com.lh1110642.gymgenie

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.lh1110642.gymgenie.databinding.ActivityBodyCalculationBinding
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.checkerframework.checker.units.qual.m

class BodyCalculationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBodyCalculationBinding
    private lateinit var bodyStats: BodyStats
    var auth = Firebase.auth
    var weight = ""
    var height = ""
    var bmi = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyCalculationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        databaseRead()


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


        binding.bodyFatTextView.visibility = View.INVISIBLE
        binding.healthTextView.visibility = View.INVISIBLE
        binding.normRangeTextView.visibility = View.INVISIBLE


        binding.bodyFatResultTextView.visibility = View.INVISIBLE
        binding.healthResultTextView.visibility = View.INVISIBLE
        binding.normRangeResultTextView.visibility = View.INVISIBLE

        val weightText = findViewById<EditText>(R.id.wtText)
        val heightText = findViewById<EditText>(R.id.htText)
        val calcButton = findViewById<Button>(R.id.calcBtn)

        calcButton.setOnClickListener {
            weight = weightText.text.toString()
            height = heightText.text.toString()
            if(validateInput(weight,height)) {
                bmi = (0.45*weight.toFloat())/((height.toFloat()/100)*(height.toFloat()/100))
                val bmiFinal = String.format("%.2f", bmi).toFloat()
                displayResult(bmiFinal)
             }
        }

        binding.saveBtn.setOnClickListener {
            bodyStats =  BodyStats(height,weight,bmi.toString())
            database(bodyStats)
            Toast.makeText(this,  "Successfully added to the database", Toast.LENGTH_LONG).show()
        }

        binding.btnSearch.setOnClickListener{

            val uri: Uri =
                Uri.parse("https://www.cdc.gov/healthyweight/assessing/bmi/index.html") // missing 'http://' will cause crashed

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        /*height = cmToM(height)
        weight = kgToLb(weight)
        bmi = calculateBMI(weight,height)

        println("The height is $height m and the weight is $weight pound. The BMI is $bmi%.")

    }

    fun cmToM(cm: Double): Double {
        return cm / 100.0
    }

    fun kgToLb(kg: Double): Double {
        return kg * 2.20462
    }

    fun calculateBMI(weight: Double, height: Double): Double {
        return weight / (height * height)
    }*/

        //test

        val currentWeight = 70.0 // kilograms
        val desiredBodyFatPercentage = 20.0 // percent
        val desiredWeight = calculateDesiredWeight(currentWeight, desiredBodyFatPercentage)
        println("Desired weight: $desiredWeight kilograms")


}

    private fun validateInput(weight: String?, height:String?):Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,  "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,  "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun calculateDesiredWeight(currentWeight: Double, desiredBodyFatPercentage: Double): Double {
        return (desiredBodyFatPercentage / 100) * currentWeight / (1 - (desiredBodyFatPercentage / 100))
    }

    private fun displayResult(bmi: Float) {
        binding.bodyFatResultTextView.text = bmi.toString()
        binding.normRangeResultTextView.text= "18.5-24.9"

        var resultText = ""

        when {
            bmi < 18.50 -> {
                resultText = "Under Weight"
            }
            bmi in 18.50..24.99-> {
                resultText= "Normal"
            }
            bmi in 25.00..29.99 -> {
                resultText = "Overweight"
            }
            bmi > 29.99 -> {
                resultText = "Obese"
            }
        }
        binding.healthResultTextView.text = resultText

        binding.bodyFatTextView.visibility = View.VISIBLE
        binding.healthTextView.visibility = View.VISIBLE
        binding.normRangeTextView.visibility = View.VISIBLE


        binding.bodyFatResultTextView.visibility = View.VISIBLE
        binding.healthResultTextView.visibility = View.VISIBLE
        binding.normRangeResultTextView.visibility = View.VISIBLE
    }


    fun database(stats: BodyStats){

        var userId = Firebase.auth.currentUser?.uid
        var db = FirebaseFirestore.getInstance().collection("bmi")
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


        db = FirebaseFirestore.getInstance().collection("bmi")

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
        val db = FirebaseFirestore.getInstance().collection("bmi").whereEqualTo("uid", userId)
        db.get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    var valueHolder = document.toObject(BodyStats::class.java)
                    binding.wtText.setText( valueHolder.getstatTwo())
                    binding.htText.setText(valueHolder.getstatOne())


                }
            }



    }

}