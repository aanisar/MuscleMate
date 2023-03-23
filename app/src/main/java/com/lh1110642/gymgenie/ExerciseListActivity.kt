package com.lh1110642.gymgenie

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.lh1110642.gymgenie.databinding.ActivityExerciseListBinding
import okhttp3.OkHttpClient
import okhttp3.Request



class ExerciseListActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var adapterExercise: Adapter
    //init variable section
    var exerciseList: ArrayList<Exercise> = ArrayList()
    var filteredExerciseList: ArrayList<Exercise> = ArrayList()
    var muscle = ""
    var diff = ""
    var type = ""
    var equipment =""
    var name =""
    var auth = Firebase.auth
    var listExercise = arrayOfNulls<Exercise>(10)
    var ThreadKiller = false

    //var MyRecyclerViewAdapter adapter
    private lateinit var binding: ActivityExerciseListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        muscle = intent.getStringExtra("muscle").toString()
        diff = intent.getStringExtra("difficulty").toString()
        type = intent.getStringExtra("type").toString()
        equipment = intent.getStringExtra("equipment").toString()
        apiCall()


//
//        val animalNames: ArrayList<String> = ArrayList()
//        animalNames.add("Horse")
//        animalNames.add("Cow")
//        animalNames.add("Camel")
//        animalNames.add("Sheep")
//        animalNames.add("Goat")
//
        val recyclerView: RecyclerView = findViewById(com.lh1110642.gymgenie.R.id.recycler)
//        recyclerView.setLayoutManager(LinearLayoutManager(this))
//       var adapter = MyRecyclerViewAdapter(this, listExercise.toMutableList())
//        adapter.setClickListener(this)
//        recyclerView.setAdapter(adapter)
//        for (for each exercise in listExercise){
//            if exercise.equipment != equipment
//                listExercise[i].remove
//            i++
//        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listExercise.toMutableList()?.let { CustomAdapter(it) }
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        val equipmentSet = mutableSetOf<String>()
        equipmentSet.add("All")
        for (exercise in listExercise) {
            exercise?.let {
                val equipmentStrings = it.equipment.capitalize().replace("_"," ").split(",")
                equipmentSet.addAll(equipmentStrings.map { it.trim() })
            }
        }
        val equipmentList = equipmentSet.toList()

        var adapter = ArrayAdapter(this,R.layout.simple_spinner_item, equipmentList)

        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.equipmentSpinner.adapter = adapter
        binding.equipmentSpinner.onItemSelectedListener = this


    }

    fun taskSelected(task: Exercise) {
        var intent = Intent(this, ViewExerciseActivity::class.java)
        startActivity(intent)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.lh1110642.gymgenie.R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //different menu options
        return when (item.itemId) {
            com.lh1110642.gymgenie.R.id.anatomy -> {
                startActivity(Intent(this,BrowsingActivity::class.java))
                return true
            }
            com.lh1110642.gymgenie.R.id.workout -> {
                startActivity(Intent(this,WorkoutActivity::class.java))
                return true
            }
            com.lh1110642.gymgenie.R.id.signOut -> {
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        startActivity(Intent(this,LoginActivity::class.java))
                    }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }

    fun apiCall(){

        var  APIMod = ""
        //  var muscle = ""
//        var diff = ""
//        var type = ""

        //Filters
        if (muscle != "")
        {
            if (APIMod == "")
                APIMod = "?muscle="+muscle
            else{
                APIMod += "&muscle="+muscle
            }
        }
        if (diff != "")
        {
            if (APIMod == "")
                APIMod = "?difficulty="+diff
            else{
                APIMod += "&difficulty="+diff
            }
        }
        if (type != "")
        {
            if (APIMod == "")
                APIMod = "?type="+type
            else{
                APIMod += "&type="+type
            }
        }
        if (equipment != "")
        {
            if (APIMod == "")
                APIMod = "?type="+equipment
            else{
                APIMod += "&type="+equipment
            }
        }
        if (name != "")
        {
            name.replace(" ","%20")
            if (APIMod == "")
                APIMod = "?name="+name
            else{
                APIMod += "&name="+name
            }
        }

        var url = "https://exercises-by-api-ninjas.p.rapidapi.com/v1/exercises" + APIMod;
        //"https://exercises-by-api-ninjas.p.rapidapi.com/v1/exercises?muscle=biceps
        Thread { //Thread that runs networking in aysnc

            //Code that calls the api, with URI and link
            val client = OkHttpClient()
            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "088a03c490mshbabdd39f3567b25p1b021ajsnd500a6f71c98")
                .addHeader("X-RapidAPI-Host", "exercises-by-api-ninjas.p.rapidapi.com")
                .build()

            try {

                //Calls API, then converts into a Gson() into a string
                val response = client.newCall(request).execute()
                val result: String = Gson().toJson(response.body!!.string())

                val x = result // set x to the result string, for substringing

                //split string delimited by bracket
                val stringarray =
                    x.split("},").toMutableList()


                for (i in stringarray.indices) { //Corrects each Exercise by adding bracket
                    //prints the tokens
                    stringarray[i] += "}"
                }

                //Corrects the leading and trailing char's in the first and last member of the array
                stringarray[0] = stringarray[0].substring(1, stringarray[0].length)
                stringarray[stringarray.size - 1] = stringarray[stringarray.size - 1].substring(0, stringarray[stringarray.size - 1].length - 2)


                // val listExercise = arrayOfNulls<Exercise>(10)
                for (i in stringarray.indices) {
                    Log.i("DB_Mason:", i.toString())
                    //Replaces the def of each exercise category, with a deliminator
                    var str = stringarray[i]
                    str = str.replace("\\\"name\\\":", "@!@")
                    str = str.replace("\\\"type\\\":", "@!@")
                    str = str.replace("\\\"muscle\\\":", "@!@")
                    str = str.replace("\\\"equipment\\\":", "@!@")
                    str = str.replace("\\\"difficulty\\\":", "@!@")
                    str = str.replace("\\\"instructions\\\":", "@!@")
                    str.replace("\"", "")
                    val strArr = str.split("@!@") //split each category into 1 list

                    //Creates a exercise, then inserts it into a list
                    val exercise = Exercise(strArr[1].substring(3, strArr[1].length - 4), strArr[2].substring(3, strArr[2].length - 4), strArr[3].substring(3, strArr[3].length - 4), strArr[4].substring(3, strArr[4].length - 4), strArr[5].substring(3, strArr[5].length - 4), strArr[6].substring(3, strArr[6].length - 4))
                    listExercise[i] = exercise
                }

                //Just displays the list for viewing purpose, NOT NEEDED
                println(listExercise[3]!!.excerciseToString())
                for (i in stringarray.indices) {
                    //prints the tokens
                    println(listExercise[i]!!.getName())
                    Log.i("Name: ", listExercise[i]!!.getName())
                    println("_________________________")
                }
                Log.i("Name: ", listExercise[3]!!.excerciseToString())

            }catch (err:Error) {//if call fails
                Log.i("error","ERRRRRRRRRRRR")
            }
            ThreadKiller = true;
        }.start()

        while (ThreadKiller == false){

        }

    }
    fun database(excerciseForWorkout: Exercise){
        val db = FirebaseFirestore.getInstance().collection("workout")

        val id = db.document().getId()


        //debugging not needed
//        Log.i("DB_Mason: ","About to upload to DB")
//        if (excerciseForWorkout != null) {
//            Log.i("DB_Mason: ",excerciseForWorkout.getName())
//        }
        Log.w("DB_Mason", auth.currentUser!!.uid)
        if (excerciseForWorkout != null) { //Gets the users ID
            excerciseForWorkout.setuid(auth.currentUser!!.uid)
            excerciseForWorkout.setWorkOutGroup("") //You can use this for putting it into a workout group
        }
        //Writes to the database
        if (excerciseForWorkout != null) {
            db.document(id).set(excerciseForWorkout)
                .addOnSuccessListener {  Log.w("DB_Mason", "ADDED") }
                .addOnFailureListener{ Log.w("DB_Fail", it.localizedMessage)}
        }


    }

    fun recyclerClicked(view: View) {}
    private fun filterExercisesByEquipment(equipmentName: String) {
        val recyclerView: RecyclerView = findViewById(com.lh1110642.gymgenie.R.id.recycler)
        val filteredList = exerciseList.filter { exercise ->
            exercise.equipment.split(",").map { it.trim().lowercase() }.contains(equipmentName)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        //recyclerView.adapter = filteredList.toMutableList()?.let { CustomAdapter(it) }
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }
    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        equipment = parent.getItemAtPosition(position).toString().lowercase().replace(" ","_")
        Toast.makeText(parent.context, equipment, Toast.LENGTH_SHORT).show()
        filterExercisesByEquipment(equipment)

}
    override fun onNothingSelected(parent: AdapterView<*>?) {}

//    private fun initRecyclerView(){
//
//        exerciseList = ArrayList()
//
//        val rv = binding.exerciseListrv
//        rv.layoutManager = LinearLayoutManager(this)
//        adapterExercise = Adapter(exerciseList)
//
//    }
}