package com.example.myapplication.adapter_holder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemTaskBinding
import com.example.myapplication.models.Task

class TaskHolder(
    private val binding: ItemTaskBinding,
    private val edit: (Int) -> Unit,
    private val delete: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var task: Task

    init {
        itemView.setOnClickListener {
            task.id.also(edit)
        }
        itemView.findViewById<ImageView>(R.id.btn_delete).setOnClickListener {
            task.id.also(delete)
        }
    }

    fun bind(item: Task) {
        this.task = item
        with(binding) {
            tvTitle.text = item.title
            tvDescription.text = item.description
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            edit: (Int) -> Unit,
            delete: (Int) -> Unit,
        ) = TaskHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), edit, delete
        )
    }
}