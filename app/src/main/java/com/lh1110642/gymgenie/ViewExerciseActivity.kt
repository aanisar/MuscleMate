package com.lh1110642.gymgenie

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1110642.gymgenie.databinding.ActivityViewExerciseBinding


var sets = 0;
var reps = 0;
    class ViewExerciseActivity : AppCompatActivity() {

        private lateinit var binding: ActivityViewExerciseBinding
        private lateinit var exercise: Exercise
        var auth = Firebase.auth
        var workoutGroup = ""

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityViewExerciseBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.description.setMovementMethod(ScrollingMovementMethod())

//            exercise = intent.getSerializableExtra(KEY_EXERCISE_NAME) as Exercise
//
//            binding.name.text = exercise.name
//            binding.equipment.text = exercise.equipment
//            binding.difficulty.text = exercise.difficulty
//            binding.description.text = exercise.instructions


            //reads the intent for the values, then stores them in excercise, and sets text
           var muscle = intent.getStringExtra("muscle").toString()
           var diff = intent.getStringExtra("difficulty").toString()
           var type = intent.getStringExtra("type").toString()
           var equipment = intent.getStringExtra("equipment").toString()
            var description = intent.getStringExtra("description").toString()
            var name = intent.getStringExtra("name").toString()
            var isWorkout = intent.getStringExtra("isWorkout").toString()

            reps = intent.getStringExtra("reps").toString().toInt()
            sets =  intent.getStringExtra("sets").toString().toInt()


            binding.name.text = name
            binding.equipment.text = equipment.replace("_", " ")
            binding.difficulty.text = diff
            binding.description.text = description
            binding.lblReps.text = reps.toString()
            binding.lblSets.text = sets.toString()

            exercise = Exercise(name,type,muscle,equipment,diff,description)

            if (isWorkout != "na"){
                binding.btnRepsDown.isVisible = false
                binding.btnRepsDown.isClickable = false

                binding.btnRepsUp.isVisible = false
                binding.btnRepsUp.isClickable = false

                binding.btnSetsUp.isVisible = false
                binding.btnSetsUp.isClickable = false

                binding.btnSetsDown.isVisible = false
                binding.btnSetsDown.isClickable = false

                binding.btnRemove.isVisible = true
                binding.btnRemove.isClickable = true
            }

            //adds excercise to users workout group, based off button click
            binding.w1.setOnClickListener {
//                workoutOne.add(exercise.name
                workoutGroup = "workOutOne"
                database(exercise)
                Toast.makeText(baseContext, "Successfully added to Workout 1",
                    Toast.LENGTH_SHORT).show()
            }

            binding.w2.setOnClickListener {
                workoutGroup = "workOutTwo"
                database(exercise)
                Toast.makeText(baseContext, "Successfully added to Workout 2",
                    Toast.LENGTH_SHORT).show()
            }

            binding.w3.setOnClickListener {
                workoutGroup = "workOutThree"
                database(exercise)
                Toast.makeText(baseContext, "Successfully added to Workout 3",
                    Toast.LENGTH_SHORT).show()
            }

            binding.w4.setOnClickListener {
                workoutGroup = "workOutFour"
                database(exercise)
                Toast.makeText(baseContext, "Successfully added to Workout 4",
                    Toast.LENGTH_SHORT).show()


            }

            binding.btnGoogle.setOnClickListener{

                val uri: Uri =
                    Uri.parse("https://www.google.com/search?tbm=isch&q="+name +" exercise gif") // missing 'http://' will cause crashed

                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            binding.btnSetsUp.setOnClickListener{
                sets +=1
                binding.lblSets.text = sets.toString()
            }
            binding.btnSetsDown.setOnClickListener{
                sets -=1
                binding.lblSets.text = sets.toString()
            }
            binding.btnRepsUp.setOnClickListener{
                reps +=1
                binding.lblReps.text = reps.toString()
            }
            binding.btnRepsDown.setOnClickListener{
                reps -=1
                binding.lblReps.text = reps.toString()
            }

            binding.btnRemove.setOnClickListener{
                val userId = Firebase.auth.currentUser?.uid
                val db = FirebaseFirestore.getInstance().collection("workout")
                db.document(name+ isWorkout + userId)
                    .delete()
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "DB_DELETE COMPLETE")

                    }
                    .addOnFailureListener { e ->
                        Log.w(
                            ContentValues.TAG,
                            "Error deleting document",
                                e
                            )


                        }

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

//        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//            menuInflater.inflate(R.menu.main_menu, menu)
//            return true
//        }
//        override fun onOptionsItemSelected(item: MenuItem): Boolean {
//            //different menu options
//            return when (item.itemId) {
//                R.id.anatomy -> {
//                    startActivity(Intent(this,BrowsingActivity::class.java))
//                    return true
//                }
//                R.id.workout -> {
//                    startActivity(Intent(this,WorkoutActivity::class.java))
//                    return true
//                }
//
//                else -> super.onOptionsItemSelected(item)
//            }
//
//            return super.onOptionsItemSelected(item)
//        }

        fun database(excerciseForWorkout: Exercise){
            val db = FirebaseFirestore.getInstance().collection("workout")

            val id = exercise.name+workoutGroup+Firebase.auth.currentUser?.uid


            //debugging not needed
//        Log.i("DB_Mason: ","About to upload to DB")
//        if (excerciseForWorkout != null) {
//            Log.i("DB_Mason: ",excerciseForWorkout.getName())
//        }
            Log.w("DB_Mason", auth.currentUser!!.uid)
            if (excerciseForWorkout != null) { //Gets the users ID
                excerciseForWorkout.setuid(auth.currentUser!!.uid)
                excerciseForWorkout.setWorkOutGroup(workoutGroup) //You can use this for putting it into a workout group
            }
            //Writes to the database
            if (excerciseForWorkout != null) {
                exercise.setReps(reps.toString())
                exercise.setSets(sets.toString())
                db.document(id).set(excerciseForWorkout)
                    .addOnSuccessListener {  Log.w("DB_Mason", "ADDED") }
                    .addOnFailureListener{ Log.w("DB_Fail", it.localizedMessage)}
            }


        }



    }



