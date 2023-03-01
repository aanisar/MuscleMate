package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lh1110642.gymgenie.databinding.ActivityBrowsingBinding


class BrowsingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrowsingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowsingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.abs.setOnClickListener{
            Toast.makeText(baseContext, "Abs is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftShoulder.setOnClickListener{
            Toast.makeText(baseContext, "Left Shoulder is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightShoulder.setOnClickListener{
            Toast.makeText(baseContext, "Right Shoulder is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightTrap.setOnClickListener{
            Toast.makeText(baseContext, "Right Trap is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftTrap.setOnClickListener{
            Toast.makeText(baseContext, "Left Trap is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftChest.setOnClickListener{
            Toast.makeText(baseContext, "Left Chest is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightChest.setOnClickListener{
            Toast.makeText(baseContext, "Right Chest is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.frontLeftForearm.setOnClickListener{
            Toast.makeText(baseContext, "Front Left Forearm is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.frontRightForearm.setOnClickListener{
            Toast.makeText(baseContext, "Front Right Forearm is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightBicep.setOnClickListener{
            Toast.makeText(baseContext, "Right Bicep is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftBicep.setOnClickListener{
            Toast.makeText(baseContext, "Left Bicep is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftOblique.setOnClickListener{
            Toast.makeText(baseContext, "Left Oblique is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightOblique.setOnClickListener{
            Toast.makeText(baseContext, "Right Oblique is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.rightQuad.setOnClickListener{
            Toast.makeText(baseContext, "Right Quad is been clicked",
                Toast.LENGTH_SHORT).show()
        }

        binding.leftQuad.setOnClickListener{
            Toast.makeText(baseContext, "Left Quad is been clicked",
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
            startActivity(Intent(this, BrowsingBackActivity::class.java))
        }
    }
}