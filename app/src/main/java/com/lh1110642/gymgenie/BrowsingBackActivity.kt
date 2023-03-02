package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lh1110642.gymgenie.databinding.ActivityBrowsingBackBinding
import com.lh1110642.gymgenie.databinding.ActivityExerciseListBinding


class BrowsingBackActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBrowsingBackBinding
    private var muscle = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowsingBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backTrap.setOnClickListener{
//            Toast.makeText(baseContext, "Back Trap is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "traps"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.trapMidBack.setOnClickListener{
//            Toast.makeText(baseContext, "Trap Mid-Back is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "lowerback middleback"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.lowerBack.setOnClickListener{
//            Toast.makeText(baseContext, "Lower Back is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "lowerback middleback"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.leftLat.setOnClickListener{
//            Toast.makeText(baseContext, "Left Lat is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "lats"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.rightLat.setOnClickListener{
//            Toast.makeText(baseContext, "Right Lat is been clicked",
//                Toast.LENGTH_SHORT).show()
            //            Toast.makeText(baseContext, "Left Lat is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "lats"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.leftCheek.setOnClickListener{
//            Toast.makeText(baseContext, "Left Cheek is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "glutes"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.rightCheek.setOnClickListener{
//            Toast.makeText(baseContext, "Right Cheek is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "glutes"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.backLeftShoulder.setOnClickListener{
//            Toast.makeText(baseContext, "Back Left Shoulder is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "neck"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.backRightShoulder.setOnClickListener{
//            Toast.makeText(baseContext, "Back Right Shoulder is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "neck"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.rightTricep.setOnClickListener{
//            Toast.makeText(baseContext, "Right Tricep is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "triceps"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.leftTricep.setOnClickListener{
//            Toast.makeText(baseContext, "Left Tricep is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "triceps"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.backLeftForearm.setOnClickListener{
//            Toast.makeText(baseContext, "Back Left Forearm is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "forearms"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.backRightForearm.setOnClickListener{
//            Toast.makeText(baseContext, "Back Right Forearm is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "forearms"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.leftHamstring.setOnClickListener{
//            Toast.makeText(baseContext, "Left Hamstring is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "hamstrings"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.rightHamstring.setOnClickListener{
//            Toast.makeText(baseContext, "Right Hamstring is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "hamstrings"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.leftCalf.setOnClickListener{
//            Toast.makeText(baseContext, "Left Calf is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "calves"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.rightCalf.setOnClickListener{
//            Toast.makeText(baseContext, "Right Calf is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "calves"
            startActivity(Intent(this, ActivityExerciseListBinding::class.java))
        }

        binding.swap.setOnClickListener{
            startActivity(Intent(this, OtherFiltersActivity::class.java))
        }
    }
}