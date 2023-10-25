package com.hfad.stopwatch.chapter_14_Tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.stopwatch.R


/*адаптер без diffUtill и binding*/
class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {
    var data = listOf<Tasks>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TaskItemViewHolder.inflaterFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount() = data.size

    class TaskItemViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {
        val taskName = rootView.findViewById<TextView>(R.id.task_name)
        val taskDone = rootView.findViewById<CheckBox>(R.id.task_done)

        companion object {
            fun inflaterFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.task_item, parent, false) as CardView
                return TaskItemViewHolder(view)
            }
        }

        fun bind(item: Tasks) {
            taskName.text = item.tasksName
            taskDone.isChecked = item.tasksDone
        }
    }

}

/*
class TaskItemViewHolder(val rootView: TextView) : RecyclerView.ViewHolder(rootView) {
    companion object {
        fun inflaterFrom(parent: ViewGroup): TaskItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.task_item, parent, false) as TextView
            return TaskItemViewHolder(view)
        }
    }

    fun bind(item: Tasks) {
        rootView.text = item.tasksName
    }
}*/
