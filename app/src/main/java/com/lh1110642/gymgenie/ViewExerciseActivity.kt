package com.lh1110642.gymgenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lh1110642.gymgenie.databinding.ActivityViewExerciseBinding

class ViewExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewExerciseBinding
    private lateinit var exercise: Exercise

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        exercise = intent.getSerializableExtra(KEY_EXERCISE_NAME) as Exercise

        binding.name.text = exercise.name
        binding.equipment.text = exercise.equipment
        binding.difficulty.text = exercise.difficulty
        binding.description.text = exercise.instructions
    }
}

const val KEY_EXERCISE_NAME = "key_exercise_name"