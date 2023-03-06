package com.lh1110642.gymgenie

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
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




        //tracks and updates the listviews
        val viewModel : exerciseViewModel by viewModels()
        viewModel.getExercises().observe(this) { exercises ->

//            for (i in exercises.indices) {
////                //Populated DBListExercise
//                DBlistExercise[i] = exercises[i]
//
//
//            }

            var name = " "
            for (i in exercises.indices) {
                //prints the tokens
                name = exercises[i]?.getName().toString();
                var workOutGroup = exercises[i]?.getWorkOutGroup()

                if (workOutGroup == "workOutOne" && listworkOutOne.contains(name) == false){
                    listworkOutOne.add(name)}
                else if (workOutGroup == "workOutTwo"  && listworkOutTwo.contains(name) == false){
                    listworkOutTwo.add(name)}

                else if (workOutGroup == "workOutThree"  && listworkOutThree.contains(name) == false){
                    listworkOutThree.add(name)}
                else if (workOutGroup == "workOutFour"  && listworkOutFour.contains(name) == false){
                    listworkOutFour.add(name)}


            }

            //sorts and populates each listview
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


            //clears workout groups
        binding.btnClearOne.setOnClickListener{

            //db setup
            val userId = Firebase.auth.currentUser?.uid
            val db = FirebaseFirestore.getInstance().collection("workout")

            for (i in listworkOutOne.indices) {//for each object in the list

                db.document(listworkOutOne[i] + "workOutOne" + userId)//delete this document name
                    .delete()
                    .addOnSuccessListener { Log.d(ContentValues.TAG, "DB_DELETE COMPLETE") }
                    .addOnFailureListener { e ->
                        Log.w(
                            ContentValues.TAG,
                            "Error deleting document",
                            e
                        )


                    }

            }
            listworkOutOne.clear()
            workoutOne.clear()
            startActivity(Intent(this, WorkoutActivity::class.java))
        }
            binding.btnClearTwo.setOnClickListener{

                val userId = Firebase.auth.currentUser?.uid
                val db = FirebaseFirestore.getInstance().collection("workout")

                for (i in listworkOutTwo.indices) {

                    db.document(listworkOutTwo[i] + "workOutTwo" + userId)
                        .delete()
                        .addOnSuccessListener { Log.d(ContentValues.TAG, "DB_DELETE COMPLETE") }
                        .addOnFailureListener { e ->
                            Log.w(
                                ContentValues.TAG,
                                "Error deleting document",
                                e
                            )


                        }
                }
                listworkOutTwo.clear()
                workoutTwo.clear()
                startActivity(Intent(this, WorkoutActivity::class.java))
            }
            binding.btnClearThree.setOnClickListener{

                val userId = Firebase.auth.currentUser?.uid
                val db = FirebaseFirestore.getInstance().collection("workout")

                for (i in listworkOutThree.indices) {

                    db.document(listworkOutThree[i] + "workOutThree" + userId)
                        .delete()
                        .addOnSuccessListener { Log.d(ContentValues.TAG, "DB_DELETE COMPLETE") }
                        .addOnFailureListener { e ->
                            Log.w(
                                ContentValues.TAG,
                                "Error deleting document",
                                e
                            )


                        }
                }
                listworkOutThree.clear()
                workoutThree.clear()
                startActivity(Intent(this, WorkoutActivity::class.java))

            }
            binding.btnClearFour.setOnClickListener{

                val userId = Firebase.auth.currentUser?.uid
                val db = FirebaseFirestore.getInstance().collection("workout")

                for (i in listworkOutFour.indices) {

                    db.document(listworkOutFour[i] + "workOutFour" + userId)
                        .delete()
                        .addOnSuccessListener { Log.d(ContentValues.TAG, "DB_DELETE COMPLETE") }
                        .addOnFailureListener { e ->
                            Log.w(
                                ContentValues.TAG,
                                "Error deleting document",
                                e
                            )


                        }
                }
                listworkOutFour
                workoutFour.clear()
                startActivity(Intent(this, WorkoutActivity::class.java))

            }

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