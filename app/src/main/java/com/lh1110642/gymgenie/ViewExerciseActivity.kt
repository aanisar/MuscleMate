package com.lh1110642.gymgenie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1110642.gymgenie.databinding.ActivityViewExerciseBinding

    class ViewExerciseActivity : AppCompatActivity() {

        private lateinit var binding: ActivityViewExerciseBinding
        private lateinit var exercise: Exercise
        var auth = Firebase.auth
        var workoutGroup = ""

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityViewExerciseBinding.inflate(layoutInflater)
            setContentView(binding.root)

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

            binding.name.text = name
            binding.equipment.text = equipment.replace("_", " ")
            binding.difficulty.text = diff
            binding.description.text = description

            exercise = Exercise(name,type,muscle,equipment,diff,description)


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
                db.document(id).set(excerciseForWorkout)
                    .addOnSuccessListener {  Log.w("DB_Mason", "ADDED") }
                    .addOnFailureListener{ Log.w("DB_Fail", it.localizedMessage)}
            }


        }



    }

const val KEY_EXERCISE_NAME = "key_exercise_name"

