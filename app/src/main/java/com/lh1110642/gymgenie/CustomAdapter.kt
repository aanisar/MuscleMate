package com.lh1110642.gymgenie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//the basis of this adapter was found https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
class CustomAdapter(private val data: MutableList<Exercise>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //connects the row item to the adapter
        val rowItem =
            LayoutInflater.from(parent.context).inflate(R.layout.row_exercise, parent, false)
        return ViewHolder(rowItem)
    }

    //sets the vales of all row items, from the data list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = data[position]?.name
        holder.difficulty.text = data[position]?.difficulty
        holder.equipment.text = data[position]?.equipment?.replace("_"," ")
        holder.type = data[position]?.type.toString()
        holder.description = data[position]?.instructions.toString()
        holder.muscel = data[position]?.muscle.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        //init variable dec
        val name: TextView
        val difficulty: TextView
        val equipment: TextView
        var type = ""
        var description = ""
        var muscel = ""

        init {
            view.setOnClickListener(this)
            //finds the labels in row
            name = view.findViewById(R.id.exercise_name)
            difficulty = view.findViewById(R.id.difficulty_level)
            equipment = view.findViewById(R.id.equipment_name)


        }

        override fun onClick(view: View) {
//            Toast.makeText(
//                view.context,
//                "position : " + layoutPosition + " text : " + name.text,
//                Toast.LENGTH_SHORT
//            ).show()
            //opens up new scene
            val myIntent: Intent = Intent(view.context,ViewExerciseActivity::class.java).putExtra("muscle", muscel).putExtra("equipment",equipment.text).putExtra("difficulty",difficulty.text).putExtra("type", type).putExtra("description", description).putExtra("name", name.text).putExtra("sets", "0").putExtra("reps", "0").putExtra("isWorkout", "na")
            view.context.startActivity(myIntent)

        }
    }
}