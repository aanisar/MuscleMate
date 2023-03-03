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
import com.google.gson.Gson
import com.lh1110642.gymgenie.databinding.ActivityBrowsingBinding
import com.lh1110642.gymgenie.databinding.ActivityExerciseListBinding
import okhttp3.OkHttpClient
import okhttp3.Request
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
            Log.i("Muscle Picked: ", muscle)

             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.leftShoulder.setOnClickListener{
//            Toast.makeText(baseContext, "Left Shoulder is been clicked",
//                Toast.LENGTH_SHORT).show()

            muscle = "neck"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.rightShoulder.setOnClickListener{
//            Toast.makeText(baseContext, "Right Shoulder is been clicked",
//                Toast.LENGTH_SHORT).show()

            muscle = "neck"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.rightTrap.setOnClickListener{
//            Toast.makeText(baseContext, "Right Trap is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "traps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.leftTrap.setOnClickListener{
//            Toast.makeText(baseContext, "Left Trap is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "traps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.leftChest.setOnClickListener{
//            Toast.makeText(baseContext, "Left Chest is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "chest"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.rightChest.setOnClickListener{
//            Toast.makeText(baseContext, "Right Chest is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "chest"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.frontLeftForearm.setOnClickListener{
//            Toast.makeText(baseContext, "Front Left Forearm is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "forearms"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.frontRightForearm.setOnClickListener{
//            Toast.makeText(baseContext, "Front Right Forearm is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "forearms"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.rightBicep.setOnClickListener{
//            Toast.makeText(baseContext, "Right Bicep is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "biceps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.leftBicep.setOnClickListener{
//            Toast.makeText(baseContext, "Left Bicep is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "biceps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.leftOblique.setOnClickListener{
//            Toast.makeText(baseContext, "Left Oblique is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "abdominals"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.rightOblique.setOnClickListener{
//            Toast.makeText(baseContext, "Right Oblique is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "abdominals"
            Log.i("Muscle Picked: ", muscle)

            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.rightQuad.setOnClickListener{
//            Toast.makeText(baseContext, "Right Quad is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "quadriceps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.leftQuad.setOnClickListener{
//            Toast.makeText(baseContext, "Left Quad is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "quadriceps"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.leftCalf.setOnClickListener{
//            Toast.makeText(baseContext, "Left Calf is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "calves"
            Log.i("Muscle Picked: ", muscle)
            
             startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))          }

        binding.rightCalf.setOnClickListener{
//            Toast.makeText(baseContext, "Right Calf is been clicked",
//                Toast.LENGTH_SHORT).show()
            muscle = "calves"
            Log.i("Muscle Picked: ", muscle)
            
            
            startActivity(Intent(this, ExerciseListActivity::class.java).putExtra("muscle", muscle).putExtra("difficulty","").putExtra("type", ""))        
        }

        binding.swap.setOnClickListener{
            startActivity(Intent(this, BrowsingBackActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this,LoginActivity::class.java))
            }
        return true

        return super.onOptionsItemSelected(item)
    }

    

}