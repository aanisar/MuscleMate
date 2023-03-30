package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.auth.AuthUI
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.lh1110642.gymgenie.databinding.ActivityBrowsingBinding
import com.lh1110642.gymgenie.databinding.ActivityExerciseListBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import org.checkerframework.checker.nullness.qual.NonNull
import java.util.*


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
            Log.i("Muscle Picked: ", muscle)

             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.leftShoulder.setOnClickListener{
//            Toast.makeText(baseContext, "Left Shoulder is been clicked",
//                Toast.LENGTH_SHORT).show()

            muscle = "neck"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.rightShoulder.setOnClickListener{
//            Toast.makeText(baseContext, "Right Shoulder is been clicked",
//                Toast.LENGTH_SHORT).show()

            muscle = "neck"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.rightTrap.setOnClickListener{
//            Toast.makeText(baseContext, "Right Trap is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "traps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.leftTrap.setOnClickListener{
//            Toast.makeText(baseContext, "Left Trap is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "traps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.leftChest.setOnClickListener{
//            Toast.makeText(baseContext, "Left Chest is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "chest"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.rightChest.setOnClickListener{
//            Toast.makeText(baseContext, "Right Chest is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "chest"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.frontLeftForearm.setOnClickListener{
//            Toast.makeText(baseContext, "Front Left Forearm is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "forearms"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.frontRightForearm.setOnClickListener{
//            Toast.makeText(baseContext, "Front Right Forearm is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "forearms"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.rightBicep.setOnClickListener{
//            Toast.makeText(baseContext, "Right Bicep is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "biceps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.leftBicep.setOnClickListener{
//            Toast.makeText(baseContext, "Left Bicep is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "biceps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.leftOblique.setOnClickListener{
//            Toast.makeText(baseContext, "Left Oblique is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "abdominals"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.rightOblique.setOnClickListener{
//            Toast.makeText(baseContext, "Right Oblique is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "abdominals"
            Log.i("Muscle Picked: ", muscle)

            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.rightQuad.setOnClickListener{
//            Toast.makeText(baseContext, "Right Quad is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "quadriceps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.leftQuad.setOnClickListener{
//            Toast.makeText(baseContext, "Left Quad is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "quadriceps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.leftCalf.setOnClickListener{
//            Toast.makeText(baseContext, "Left Calf is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "calves"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))             }

        binding.rightCalf.setOnClickListener{
//            Toast.makeText(baseContext, "Right Calf is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "calves"
            Log.i("Muscle Picked: ", muscle)
            
            
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", "").putExtra("equipment", ""))        
        }

        binding.swap.setOnClickListener{
            startActivity(Intent(this, BrowsingBackActivity::class.java))
// startActivity(Intent(this, WorkoutActivity::class.java))
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
//
//            else -> super.onOptionsItemSelected(item)
//        }
//
//        return super.onOptionsItemSelected(item)
//    }






}