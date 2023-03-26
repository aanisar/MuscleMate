package com.lh1110642.gymgenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyCalculationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weightText = findViewById<EditText>(R.id.wtText)
        val heightText = findViewById<EditText>(R.id.htText)
        val calcButton = findViewById<Button>(R.id.calcBtn)

        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if(validateInput(weight,height)) {
                val bmi = (0.45*weight.toFloat())/((height.toFloat()/100)*(height.toFloat()/100))
                val bmiFinal = String.format("%.2f", bmi).toFloat()
                displayResult(bmiFinal)
             }
        }

        binding.saveBtn.setOnClickListener {
            database(bodyStats)
        }

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

    private fun displayResult(bmi: Float) {
        val resultIndex = findViewById<TextView>(R.id.bmiValTextView)
        val resultDescription = findViewById<TextView>(R.id.bmiResultTextView)
        val info = findViewById<TextView>(R.id.moreInfoTextView)

        resultIndex.text = bmi.toString()
        info.text= "Normal range is 18.5-24.9"

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
        resultDescription.text = resultText
    }




    fun database(stats: BodyStats){
        val db = FirebaseFirestore.getInstance().collection("bodystats")

        val id = bodyStats.height+Firebase.auth.currentUser?.uid

        Log.w("DB_Parth", auth.currentUser!!.uid)
        if (stats != null) { //Gets the users ID
            stats.setId(auth.currentUser!!.uid)
        }
        //Writes to the database
        if (stats != null) {
            bodyStats.setHeight(bodyStats.height)
            bodyStats.setWeight(bodyStats.weight)
            bodyStats.setBmi(bodyStats.bmi)
            db.document(id).set(stats)
                .addOnSuccessListener {  Log.w("DB_Parth", "ADDED") }
                .addOnFailureListener{ Log.w("DB_Fail", it.localizedMessage)}
        }


    }

}