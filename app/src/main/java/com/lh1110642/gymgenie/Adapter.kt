package com.lh1110642.gymgenie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter(private val exerciseList: ArrayList<Exercise>?,private val onViewExerciseClickListener: OnViewExerciseClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_exercise, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return exerciseList!!.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val exercisez = exerciseList!![position]

        val name: String? = exercisez.name
        val type: String? = exercisez.type
        val muscle: String? = exercisez.muscle
        val equipment: String? = exercisez.equipment
        val difficulty: String? = exercisez.difficulty
        val instructions: String? = exercisez.instructions

        viewHolder.itemView.setOnClickListener {
            onViewExerciseClickListener.onViewExerciseItemClicked(exercisez)
        }

        /// for row
        viewHolder.exerciseName.text = name
        viewHolder.equipmentName.text = equipment
        viewHolder.difficultyLevel.text = difficulty


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        // for row
        val exerciseName: TextView = itemView.findViewById(R.id.exercise_name)
        val equipmentName: TextView = itemView.findViewById(R.id.equipment_name)
        val difficultyLevel: TextView = itemView.findViewById(R.id.difficulty_level)
    }
}
