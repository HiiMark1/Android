package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter_holder.TaskAdapter

class TaskListFragment : Fragment(R.layout.fragment_list_task) {
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var db: AppDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase(requireContext())

        val taskList = db.taskDao().getAll()
        taskAdapter = TaskAdapter(taskList, {
            goToEditFragment(it)
        }, {
            deleteTask(it)
            taskAdapter.notifyItemRemoved(it)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, TaskListFragment())
                ?.commit()
        })

        view.findViewById<ImageButton>(R.id.btn_add).setOnClickListener {
            goToEditFragment(-1)
        }
        view.findViewById<RecyclerView>(R.id.tasks).run {
            adapter = taskAdapter
        }

        if (taskList.isEmpty()) {
            view.findViewById<TextView>(R.id.tv_please_add_task).visibility = view.visibility
        }
    }

    private fun goToEditFragment(id: Int) {
        val bundle = Bundle()
        val editTaskFragment = EditTaskFragment()
        bundle.putInt("id", id)
        editTaskFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, editTaskFragment)
            ?.commit()
    }

    private fun deleteTask(id: Int) {
        val task = db.taskDao().getById(id)
        db.taskDao().delete(task)
    }
}