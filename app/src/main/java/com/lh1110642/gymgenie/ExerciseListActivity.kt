package com.lh1110642.gymgenie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lh1110642.gymgenie.databinding.ActivityExerciseListBinding
import java.util.ArrayList

class ExerciseListActivity : AppCompatActivity() {
    private lateinit var adapterExercise: Adapter
    var exerciseList: ArrayList<Exercise> = ArrayList()
    private lateinit var binding: ActivityExerciseListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseListBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}