package com.lh1110642.gymgenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ViewExerciseActivity : AppCompatActivity() {

    private lateinit var name: Exercise

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_exercise)
    }
}