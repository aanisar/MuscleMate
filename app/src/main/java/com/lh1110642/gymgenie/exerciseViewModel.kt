package com.lh1110642.gymgenie


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class exerciseViewModel() : ViewModel() {


    private val exercises = MutableLiveData<List<Exercise>>()


    init {
        val userId = Firebase.auth.currentUser?.uid

        val db = FirebaseFirestore.getInstance().collection("workout")
            .whereEqualTo("uid", userId)


            .addSnapshotListener{documents, exception ->
                if (exception != null) {
                    Log.w("DB_Response", "Listen Failed ${exception.code}")
                    return@addSnapshotListener
                }

                documents?.let {
                    val exerciseList = ArrayList<Exercise>()

                    for(document in documents){
                        Log.i("DB_Mason", "${document.data}")
                        val exercise = document.toObject(Exercise::class.java)
                        exerciseList.add(exercise)
                    }
                    exercises.value = exerciseList


                }


            }
    }
    fun getExercises() : LiveData<List<Exercise>> {
        return exercises
    }


}




