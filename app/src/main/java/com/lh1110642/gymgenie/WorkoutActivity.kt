package com.lh1110642.gymgenie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1110642.gymgenie.databinding.ActivityWorkoutsBinding


var listworkOutOne: ArrayList<String> = ArrayList()
var listworkOutTwo: ArrayList<String> = ArrayList()
var listworkOutThree: ArrayList<String> = ArrayList()
var listworkOutFour: ArrayList<String> = ArrayList()

lateinit var workoutOne: ArrayList<String>
lateinit var workoutTwo: ArrayList<String>
lateinit var workoutThree: ArrayList<String>
lateinit var workoutFour: ArrayList<String>

var DBlistExercise = arrayOfNulls<Exercise>(1000)
class WorkoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkoutsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //list view setup
        workoutOne = ArrayList()
        workoutTwo = ArrayList()
        workoutThree = ArrayList()
        workoutFour = ArrayList()


        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@WorkoutActivity,
            android.R.layout.simple_list_item_1,
            workoutOne as List<String?>
        )
        val adapter2: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@WorkoutActivity,
            android.R.layout.simple_list_item_1,
            workoutTwo as List<String?>
        )
        val adapter3: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@WorkoutActivity,
            android.R.layout.simple_list_item_1,
            workoutThree as List<String?>
        )
        val adapter4: ArrayAdapter<String?> = ArrayAdapter<String?>(
            this@WorkoutActivity,
            android.R.layout.simple_list_item_1,
            workoutFour as List<String?>
        )
        binding.lvOne.adapter = adapter
        binding.lvTwo.adapter = adapter2
        binding.lvThree.adapter = adapter3
        binding.lvFour.adapter = adapter4





        val viewModel : exerciseViewModel by viewModels()
        viewModel.getExercises().observe(this) { exercises ->

            for (i in exercises.indices) {
//                //Populated DBListExercise
                   DBlistExercise[i] = exercises[i]

            }

            sortWorkouts()

            for (i in listworkOutOne.indices) {
                workoutOne.add(listworkOutOne[i])
                adapter.notifyDataSetChanged()
                //workoutTwo.add("Fries")
            }
            for (i in listworkOutTwo.indices) {

                workoutTwo.add(listworkOutTwo[i])
                adapter2.notifyDataSetChanged()
                //workoutTwo.add("Fries")

            }
            for (i in listworkOutThree.indices) {
                workoutThree.add(listworkOutThree[i])
                adapter3.notifyDataSetChanged()
            }
            for (i in listworkOutFour.indices) {
                workoutFour.add(listworkOutFour[i])
                adapter4.notifyDataSetChanged()
            }



        }


    }
    fun sortWorkouts() {
        var name = " "
        for (i in DBlistExercise.indices) {
            //prints the tokens
            name = DBlistExercise[i]?.getName().toString();
            var workOutGroup = DBlistExercise[i]?.getWorkOutGroup()

            if (workOutGroup == "workOutOne" && listworkOutOne.contains(name) == false){
                listworkOutOne.add(name)}
            else if (workOutGroup == "workOutTwo"  && listworkOutTwo.contains(name) == false){
                listworkOutTwo.add(name)}

            else if (workOutGroup == "workOutThree"  && listworkOutThree.contains(name) == false){
                listworkOutThree.add(name)}
            else if (workOutGroup == "workOutFour"  && listworkOutFour.contains(name) == false){
                listworkOutFour.add(name)}


        }



    }

}
