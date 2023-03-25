package com.lh1110642.gymgenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lh1110642.gymgenie.databinding.ActivityBodyCalculationBinding

class BodyCalculationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBodyCalculationBinding
    var height = 200.0
    var weight = 60.0
    var bmi = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyCalculationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        height = cmToM(height)
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
    }


}