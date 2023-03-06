package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
import com.lh1110642.gymgenie.databinding.ActivityMainBinding
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var APIMod = "?muscle=biceps  "
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


                val listExercise = arrayOfNulls<Exercise>(10)
                for (i in stringarray.indices) {

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
        }.start()
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