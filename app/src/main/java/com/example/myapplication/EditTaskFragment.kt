package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentEditTaskBinding
import com.example.myapplication.models.Task

class EditTaskFragment : Fragment(R.layout.fragment_edit_task) {
    private lateinit var db: AppDatabase
    private lateinit var binding: FragmentEditTaskBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEditTaskBinding.bind(view)
        db = AppDatabase(requireContext())

        with(binding) {
            val id = arguments?.getInt("id")
            if (id != null) {
                if (id == -1) {
                    btnSave.setOnClickListener {
                        save()
                    }
                } else {
                    val task = db.taskDao().getById(id)
                    etTitle.setText(task.title)
                    etDescription.setText(task.description)
                    btnSave.setOnClickListener {
                        update(id)
                    }
                }
            }
        }
    }

    private fun save() {
        with(binding) {
            val title = etTitle.text.toString()
            val description = etDescription.text.toString()
            db.taskDao().save(
                Task(
                    title,
                    description
                )
            )
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, TaskListFragment())
                ?.commit()
        }
    }

    private fun update(id: Int) {
        with(binding) {
            val title = etTitle.text.toString()
            val desc = etDescription.text.toString()
            db.taskDao().update(
                Task(id, title, desc)
            )
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, TaskListFragment())
                ?.commit()
        }
    }
}