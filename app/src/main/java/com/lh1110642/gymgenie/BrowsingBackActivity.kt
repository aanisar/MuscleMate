package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
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
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))
        }

        binding.trapMidBack.setOnClickListener{
//            Toast.makeText(baseContext, "Trap Mid-Back is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "lowerback middleback"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.lowerBack.setOnClickListener{
//            Toast.makeText(baseContext, "Lower Back is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "lowerback middleback"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.leftLat.setOnClickListener{
//            Toast.makeText(baseContext, "Left Lat is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "lats"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.rightLat.setOnClickListener{
//            Toast.makeText(baseContext, "Right Lat is been clicked",
//                Toast.LENGTH_SHORT).show()
            //            Toast.makeText(baseContext, "Left Lat is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "lats"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.leftCheek.setOnClickListener{
//            Toast.makeText(baseContext, "Left Cheek is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "glutes"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.rightCheek.setOnClickListener{
//            Toast.makeText(baseContext, "Right Cheek is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "glutes"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.backLeftShoulder.setOnClickListener{
//            Toast.makeText(baseContext, "Back Left Shoulder is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "neck"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.backRightShoulder.setOnClickListener{
//            Toast.makeText(baseContext, "Back Right Shoulder is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "neck"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.rightTricep.setOnClickListener{
//            Toast.makeText(baseContext, "Right Tricep is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "triceps"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.leftTricep.setOnClickListener{
//            Toast.makeText(baseContext, "Left Tricep is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "triceps"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.backLeftForearm.setOnClickListener{
//            Toast.makeText(baseContext, "Back Left Forearm is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "forearms"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.backRightForearm.setOnClickListener{
//            Toast.makeText(baseContext, "Back Right Forearm is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "forearms"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.leftHamstring.setOnClickListener{
//            Toast.makeText(baseContext, "Left Hamstring is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "hamstrings"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.rightHamstring.setOnClickListener{
//            Toast.makeText(baseContext, "Right Hamstring is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "hamstrings"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.leftCalf.setOnClickListener{
//            Toast.makeText(baseContext, "Left Calf is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "calves"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.rightCalf.setOnClickListener{
//            Toast.makeText(baseContext, "Right Calf is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "calves"
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        }

        binding.swap.setOnClickListener{
            startActivity(Intent(this, BrowsingActivity::class.java))
        }

        binding.bottomNavigator.selectedItemId = R.id.anatomy
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
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return true
//    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        //different menu options
//        return when (item.itemId) {
//            R.id.anatomy -> {
//                startActivity(Intent(this,BrowsingActivity::class.java))
//                return true
//            }
//            R.id.workout -> {
//                startActivity(Intent(this,WorkoutActivity::class.java))
//                return true
//            }
//            R.id.profile -> {
//                startActivity(Intent(this,ProfileActivity::class.java))
//                return true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//
//        return super.onOptionsItemSelected(item)
//    }

}