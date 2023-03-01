package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lh1110642.gymgenie.databinding.ActivityBrowsingBackBinding



class BrowsingBackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBrowsingBackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowsingBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backTrap.setOnClickListener{
            Toast.makeText(baseContext, "Back Trap is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.trapMidBack.setOnClickListener{
            Toast.makeText(baseContext, "Trap Mid-Back is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.lowerBack.setOnClickListener{
            Toast.makeText(baseContext, "Lower Back is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftLat.setOnClickListener{
            Toast.makeText(baseContext, "Left Lat is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightLat.setOnClickListener{
            Toast.makeText(baseContext, "Right Lat is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftCheek.setOnClickListener{
            Toast.makeText(baseContext, "Left Cheek is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightCheek.setOnClickListener{
            Toast.makeText(baseContext, "Right Cheek is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.backLeftShoulder.setOnClickListener{
            Toast.makeText(baseContext, "Back Left Shoulder is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.backRightShoulder.setOnClickListener{
            Toast.makeText(baseContext, "Back Right Shoulder is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightTricep.setOnClickListener{
            Toast.makeText(baseContext, "Right Tricep is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftTricep.setOnClickListener{
            Toast.makeText(baseContext, "Left Tricep is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.backLeftForearm.setOnClickListener{
            Toast.makeText(baseContext, "Back Left Forearm is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.backRightForearm.setOnClickListener{
            Toast.makeText(baseContext, "Back Right Forearm is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftHamstring.setOnClickListener{
            Toast.makeText(baseContext, "Left Hamstring is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightHamstring.setOnClickListener{
            Toast.makeText(baseContext, "Right Hamstring is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftCalf.setOnClickListener{
            Toast.makeText(baseContext, "Left Calf is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightCalf.setOnClickListener{
            Toast.makeText(baseContext, "Right Calf is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.swap.setOnClickListener{
            startActivity(Intent(this, BrowsingActivity::class.java))
        }
    }
}