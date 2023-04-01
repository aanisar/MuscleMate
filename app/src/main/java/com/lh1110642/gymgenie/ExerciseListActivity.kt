package com.lh1110642.gymgenie

import android.R
import android.annotation.SuppressLint
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
    var muscle = ""
    var diff = ""
    var type = ""
    var equipment =""
    var name =""
    var auth = Firebase.auth
    var listExercise = mutableListOf<Exercise>()
    var ThreadKiller = false
    var offset = 0;
    lateinit var recyclerView: RecyclerView
    //var MyRecyclerViewAdapter adapter
    private lateinit var binding: ActivityExerciseListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigator.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                com.lh1110642.gymgenie.R.id.anatomy -> {
                    offset += 10
                    apiCall()
                    filterExercisesByEquipment(equipment)
                  //  startActivity(Intent(this, BrowsingActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                com.lh1110642.gymgenie.R.id.workout -> {
                    startActivity(Intent(this, WorkoutActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                com.lh1110642.gymgenie.R.id.profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener super.onOptionsItemSelected(menuItem)
            }
        }

        muscle = intent.getStringExtra("muscle").toString()
        diff = intent.getStringExtra("difficulty").toString()
        type = intent.getStringExtra("type").toString()
        equipment = intent.getStringExtra("equipment").toString()
        name = intent.getStringExtra("name").toString()
        listExercise.clear()

            apiCall()

        exerciseList.addAll(listExercise)

//
        recyclerView = findViewById(com.lh1110642.gymgenie.R.id.recycler)




        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdapter(listExercise)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))


        val equipmentSet = mutableSetOf<String>()
        //all starts at position 0 in the spinner
        //adding a-z at position 7 of spinner
        equipmentSet.add("All (A to Z)")
        //adding z-a at position 8 of spinner
        equipmentSet.add("All (Z to A)")

        //exercises take up positions 1-6
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

        var url = " https://api.api-ninjas.com/v1/exercises" + APIMod+"&offset="+offset.toString()
        //"https://exercises-by-api-ninjas.p.rapidapi.com/v1/exercises?muscle=biceps
        Thread { //Thread that runs networking in aysnc
            while (listExercise.size < 30) {
                var offsetCounter = -1


                var url =
                    " https://api.api-ninjas.com/v1/exercises" + APIMod + "&offset=" + offset.toString()
                offsetCounter = listExercise.size
                //apiCall()


                //Code that calls the api, with URI and link
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url(url)
                    .get()
                    // .addHeader("X-RapidAPI-Key", "088a03c490mshbabdd39f3567b25p1b021ajsnd500a6f71c98")
                    .addHeader("X-Api-Key", "2J0K5sCKdQmqfU9GdHRtrPqGxMKde7OerW2roLJY")
                    //  .addHeader("X-RapidAPI-Host", "exercises-by-api-ninjas.p.rapidapi.com")

                    .build()


                try {

                    //Calls API, then converts into a Gson() into a string
                    val response = client.newCall(request).execute()
                    Log.i("DB_Mason:", response.toString())
                    val result: String = Gson().toJson(response.body!!.string())
                    Log.i("DB_Mason!!!!!!!!!!!:", listExercise.size.toString())
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
                    stringarray[stringarray.size - 1] = stringarray[stringarray.size - 1].substring(
                        0,
                        stringarray[stringarray.size - 1].length - 2
                    )

                    var counter = 0;
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

                        if (strArr.size == 7) {
                            //Creates a exercise, then inserts it into a list
                            val exercise = Exercise(
                                strArr[1].substring(3, strArr[1].length - 4),
                                strArr[2].substring(3, strArr[2].length - 4),
                                strArr[3].substring(3, strArr[3].length - 4),
                                strArr[4].substring(3, strArr[4].length - 4),
                                strArr[5].substring(3, strArr[5].length - 4),
                                strArr[6].substring(3, strArr[6].length - 4)
                            )
                            listExercise.add(exercise)

                        }
                    }
                    offset +=10

                    //Just displays the list for viewing purpose, NOT NEEDED
//                println(listExercise[3]!!.excerciseToString())
//                for (i in stringarray.indices) {
//                    //prints the tokens
//                    println(listExercise[i]!!.getName())
//                    Log.i("Name: ", listExercise[i]!!.getName())
//                    println("_________________________")
//                }
//                Log.i("Name: ", listExercise[3]!!.excerciseToString())

                } catch (err: Error) {//if call fails
                    Log.i("error", "ERRRRRRRRRRRR")
                }

            }
            ThreadKiller = true;
        }.start()

        while (ThreadKiller == false){

        }

    }

    fun recyclerClicked(view: View) {}
//    private fun filterExercisesByEquipment(equipmentName: String) {
//        when (equipmentName) {
//            "all__a_to_z_" -> {
//                listExercise.clear()
//                listExercise.addAll(exerciseList)
//                exerciseList.sortByDescending { it.name }
//            }
//            "all__z_to_a_" -> {
//                listExercise.clear()
//                listExercise.addAll(exerciseList)
//                exerciseList.sortBy { it.name }
//            }
//        }
//        var holder: ArrayList<Exercise> = ArrayList()
//        while (listExercise.size != 0){
//            holder.add(listExercise.first())
//            listExercise.removeFirst()
//            recyclerView.adapter?.notifyDataSetChanged()
//        }
//        if(equipmentName != "all__a_to_z_" && equipmentName != "all__z_to_a_")
//            holder = holder.filter { it.equipment == equipment } as ArrayList<Exercise>
//        while ( holder.size != 0){
//            listExercise.add(holder.first())
//            holder.removeFirst()
//            recyclerView.adapter?.notifyDataSetChanged()
//        }
//
//
//    }
private fun filterExercisesByEquipment(equipmentName: String) {
    val sortedExerciseList = when (equipmentName) {
        "all__a_to_z_" -> exerciseList.sortedBy { it.name }
        "all__z_to_a_" -> exerciseList.sortedByDescending { it.name }
        else -> exerciseList.filter { it.equipment == equipmentName }.sortedBy { it.name }
    }

    listExercise.clear()
    listExercise.addAll(sortedExerciseList)

    recyclerView.adapter?.notifyDataSetChanged()
}

    private fun filterExercisesByNameAZ() {
        listExercise.clear()
        listExercise.addAll(exerciseList)
        var holder: ArrayList<Exercise> = ArrayList()
        while (listExercise.size != 0){
            holder.add(listExercise.first())
            listExercise.removeFirst()
            recyclerView.adapter?.notifyDataSetChanged()
        }
        while ( holder.size != 0){
            holder = holder.sortedBy {it.name.first() } as ArrayList<Exercise>
            listExercise.add(holder.first())
            holder.removeFirst()
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun filterExercisesByNameZA() {
        listExercise.clear()
        listExercise.addAll(exerciseList)
        var holder: ArrayList<Exercise> = ArrayList()
        while (listExercise.size != 0){
            holder.add(listExercise.first())
            listExercise.removeFirst()
            recyclerView.adapter?.notifyDataSetChanged()
        }
        while ( holder.size != 0){
            holder = holder.sortedByDescending {it.name.first() } as ArrayList<Exercise>
            listExercise.add(holder.first())
            holder.removeFirst()
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        equipment = parent.getItemAtPosition(position).toString().lowercase().replace(" ","_").replace("(","_").replace(")","_")
            filterExercisesByEquipment(equipment)

//        var atoz = false
//        var ztoa = false
//
//        //run through all positions in the spinner
//        //may not need the for loop wrapper- but will keep the if statements inside
////        for(i in 1..position){
//
//            //if the value selected in the spinner says Name (A to Z) then we set the atoz flag true and the other one false
//            if(equipment == "Name (A to Z)"){
//                atoz = true
//                ztoa = false
//            }
//
//            //if the value selected in the spinner says Name (Z to A) then we set the ztoa flag true and the pther one false
//            if(equipment == "Name (Z to A)"){
//                atoz = false
//                ztoa = true
//            }
//
//            //if something else is clicked other than the 2 things maintain false flags
//            else{
//                atoz = false
//                ztoa = false
//            }
////        }
//
//        //if atoz is true then run the filter method
//        if(atoz)
//            filterExercisesByNameAZ()
//
//        //if the ztoa is true run the filter method
//        if(ztoa)
//            filterExercisesByNameZA()


        //listExercise = listExercise.filter { it.equipment == equipment } as MutableList<Exercise>
            //listExercise.removeLast()
       // var holder: Array<Exercise> = listExercise.toTypedArray()
//        listExercise.clear()
//        listExercise.addAll(holder)
//        recyclerView.adapter?.notifyDataSetChanged()

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