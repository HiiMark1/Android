package com.example.myapplication.adapter_holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Task

class TaskAdapter(
    private val list: List<Task>,
    private val edit: (Int) -> Unit,
    private val delete: (Int) -> Unit,
) : RecyclerView.Adapter<TaskHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskHolder = TaskHolder.create(parent, edit, delete)

    override fun onBindViewHolder(holder: TaskHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}