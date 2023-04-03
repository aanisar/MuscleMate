package com.lh1110642.gymgenie

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1110642.gymgenie.databinding.ActivityBmrBinding
import com.lh1110642.gymgenie.databinding.ActivityOneRepMaxCalcBinding

class bmrActivity : AppCompatActivity() {
    var gender = "";
    var height = "";
    var weight = "";
    var active = "bmr"
    var bmr = 0.0f
    var age = 0
    var flag = 0
    private lateinit var binding: ActivityBmrBinding

    private lateinit var bodyStats: BodyStats
    var auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnCheckedChangeListener()
        databaseRead()

        binding.bottomNavigator.selectedItemId = R.id.profile
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

        binding.chxBxFemale.setOnClickListener { gender = "female"

            binding.chxBoxMale.isChecked = false

        }
        binding.chxBoxMale.setOnClickListener { gender = "male"

            binding.chxBxFemale.isChecked = false

        }
        binding.btnSearch2.setOnClickListener{

//
            val uri: Uri =
                Uri.parse("https://www.healthline.com/health/what-is-basal-metabolic-rate") // missing 'http://' will cause crashed

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.bmrCalc.setOnClickListener {

//            1740 * x = 2372 =1.36
//
//            1740 * 2549 =  1.46
//
//            1740 * 2696 = 1.54
            height = binding.htText.text.toString()
            weight = binding.wtText.text.toString()

            age = binding.ageText.text.toString().toInt()

            if(validateInput(height, weight)) {
                var activeMulti = 1.0
                when(active){
                    "bmr" -> activeMulti = 1.0
                    "light" ->activeMulti = 1.36
                    "moderate" -> activeMulti = 1.46
                    "active" -> activeMulti = 1.54
                }


               if (gender == "male"){
                bmr = (10*weight.toFloat()*0.453592 + 6.25*height.toFloat()).toFloat()-5*age +5
               }else if(gender == "female"){
                   bmr = (10*weight.toFloat()*0.453592 + 6.25*height.toFloat()).toFloat()-5*age -161
               }else{
                   bmr = (10*weight.toFloat()*0.453592 + 6.25*height.toFloat()).toFloat()-5*age - 78
               }
                bmr = (bmr * activeMulti).toFloat()

                bodyStats = BodyStats(height,weight,bmr.toString())
                bodyStats.setAge(age)


                binding.lblbmr.text = bmr.toString().take(4)
                binding.lblmild.text = (bmr*0.91).toString().take(4)
                binding.lblmod.text = (bmr*0.81).toString().take(4)
              //  database(bodyStats)
            flag = 1
            }
        }

        binding.bmrSave.setOnClickListener {
            if(flag == 1) {
                database(bodyStats)
                Toast.makeText(this,  "Successfully added to the database", Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this,  "Fields might be empty", Toast.LENGTH_LONG).show()
        }


    }
    private fun validateInput(reps:String?, weight: String?):Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,  "Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,  "Height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            age ==0 -> {
                Toast.makeText(this,  "Age is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
    private fun setOnCheckedChangeListener() {
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->

            if (R.id.rbtnA == checkedId)
                active = "bmr"
            if (R.id.rbtnB == checkedId)
                active = "light"
            if (R.id.rbtnC == checkedId)
                active = "moderate"
            if (R.id.rbtnD == checkedId)
                active = "active"
        }
    }
    fun database(stats: BodyStats){

        var userId = Firebase.auth.currentUser?.uid
        var db = FirebaseFirestore.getInstance().collection("BMR")
        if (userId != null) {
            db.document(userId)
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


        db = FirebaseFirestore.getInstance().collection("BMR")

        var id = Firebase.auth.currentUser?.uid

        Log.w("DB_Parth", auth.currentUser!!.uid)
        if (stats != null) { //Gets the users ID
            stats.setuid(auth.currentUser!!.uid)
        }
        //Writes to the database
        if (stats != null) {
            bodyStats.setstatOne(bodyStats.statOne)
            bodyStats.setstatTwo(bodyStats.statTwo)
            bodyStats.setStat(bodyStats.stat)
            if (id != null) {
                db.document(id).set(stats)
                    .addOnSuccessListener {  Log.w("DB_Parth", "ADDED") }
                    .addOnFailureListener{ Log.w("DB_Fail", it.localizedMessage)}
            }
        }


    }
    fun databaseRead(){
        //database call
        val userId = Firebase.auth.currentUser?.uid
        val db = FirebaseFirestore.getInstance().collection("BMR").whereEqualTo("uid", userId)
        db.get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    var valueHolder = document.toObject(BodyStats::class.java)
                    binding.wtText.setText( valueHolder.statTwo)
                    binding.htText.setText(valueHolder.statOne)
                    binding.ageText.setText(valueHolder.age.toString())


                }
            }



    }
}