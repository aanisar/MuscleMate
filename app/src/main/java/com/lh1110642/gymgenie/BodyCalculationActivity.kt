package com.lh1110642.gymgenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.lh1110642.gymgenie.databinding.ActivityBodyCalculationBinding
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast
import org.checkerframework.checker.units.qual.m

class BodyCalculationActivity : AppCompatActivity() {
    /*rivate EditText weight;
    private EditText height;
    private Button bmi;
    private TextView bmiVal;*/

    private lateinit var binding: ActivityBodyCalculationBinding
    //var height = 200.0
    //var weight = 60.0
    //var bmi = 0.0

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

}