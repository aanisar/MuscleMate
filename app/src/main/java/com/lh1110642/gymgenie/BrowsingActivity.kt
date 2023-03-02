package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.lh1110642.gymgenie.databinding.ActivityBrowsingBinding
import com.lh1110642.gymgenie.databinding.ActivityExerciseListBinding
import java.util.ArrayList


class BrowsingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrowsingBinding
    private lateinit var adapterExercise: Adapter
    var exerciseList: ArrayList<Exercise> = ArrayList()
    private var muscle = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowsingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.abs.setOnClickListener{
//            Toast.
//            makeText(baseContext, "Abs is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "abdominals"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.leftShoulder.setOnClickListener{
//            Toast.makeText(baseContext, "Left Shoulder is been clicked",
//                Toast.LENGTH_SHORT).show()

            muscle = "neck"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.rightShoulder.setOnClickListener{
//            Toast.makeText(baseContext, "Right Shoulder is been clicked",
//                Toast.LENGTH_SHORT).show()

            muscle = "neck"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.rightTrap.setOnClickListener{
//            Toast.makeText(baseContext, "Right Trap is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "traps"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.leftTrap.setOnClickListener{
//            Toast.makeText(baseContext, "Left Trap is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "traps"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.leftChest.setOnClickListener{
//            Toast.makeText(baseContext, "Left Chest is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "chest"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.rightChest.setOnClickListener{
//            Toast.makeText(baseContext, "Right Chest is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "chest"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.frontLeftForearm.setOnClickListener{
//            Toast.makeText(baseContext, "Front Left Forearm is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "forearms"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.frontRightForearm.setOnClickListener{
//            Toast.makeText(baseContext, "Front Right Forearm is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "forearms"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.rightBicep.setOnClickListener{
//            Toast.makeText(baseContext, "Right Bicep is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "biceps"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.leftBicep.setOnClickListener{
//            Toast.makeText(baseContext, "Left Bicep is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "biceps"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.leftOblique.setOnClickListener{
//            Toast.makeText(baseContext, "Left Oblique is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "abdominals"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.rightOblique.setOnClickListener{
//            Toast.makeText(baseContext, "Right Oblique is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "abdominals"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.rightQuad.setOnClickListener{
//            Toast.makeText(baseContext, "Right Quad is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "quadriceps"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.leftQuad.setOnClickListener{
//            Toast.makeText(baseContext, "Left Quad is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "quadriceps"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.leftCalf.setOnClickListener{
//            Toast.makeText(baseContext, "Left Calf is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "calves"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.rightCalf.setOnClickListener{
//            Toast.makeText(baseContext, "Right Calf is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "calves"
            startActivity(Intent(this,ActivityExerciseListBinding::class.java))
        }

        binding.swap.setOnClickListener{
            startActivity(Intent(this, BrowsingBackActivity::class.java))
        }
    }

}