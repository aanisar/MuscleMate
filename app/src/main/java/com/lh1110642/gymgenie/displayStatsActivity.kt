package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1110642.gymgenie.databinding.ActivityDisplayStatsBinding
import com.lh1110642.gymgenie.databinding.ActivityProfileRealBinding


private val authDb = FirebaseAuth.getInstance()
class displayStatsActivity : AppCompatActivity() {
    var auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityDisplayStatsBinding
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayStatsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //database call
        val userId = Firebase.auth.currentUser?.uid
        val db = FirebaseFirestore.getInstance().collection("bmi").whereEqualTo("uid", userId)
        db.get()
            .addOnSuccessListener { documents ->

                for (document in documents) {
                    var valueHolder = document.toObject(BodyStats::class.java)
                    Log.w("DB_Mason", valueHolder.getStat())
                    binding.lblBMI.text = valueHolder.getStat().take(5)

                }
            }






                binding.btnBMI.setOnClickListener {
                    startActivity(Intent(this, BodyCalculationActivity::class.java))
                }

                binding.btn1RM.setOnClickListener {
                    startActivity(Intent(this, BodyCalculationActivity::class.java))
                }

                binding.btnBMR.setOnClickListener {
                    startActivity(Intent(this, BodyCalculationActivity::class.java))
                }

    }
}