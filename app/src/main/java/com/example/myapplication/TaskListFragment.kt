package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter_holder.TaskAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TaskListFragment : Fragment(R.layout.fragment_list_task) {
    private var taskAdapter: TaskAdapter? = null
    private lateinit var db: AppDatabase
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase(requireContext())

        scope.launch {
            val taskList = db.taskDao().getAll()
            taskAdapter = TaskAdapter(taskList, {
                goToEditFragment(it)
            }, {
                deleteTask(it)
                taskAdapter?.notifyItemRemoved(it)
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, TaskListFragment())
                    ?.commit()
            })
            if (taskList.isEmpty()) {
                view.findViewById<TextView>(R.id.tv_please_add_task).visibility = view.visibility
            }
        }

        view.findViewById<ImageButton>(R.id.btn_add).setOnClickListener {
            goToEditFragment(-1)
        }
        view.findViewById<RecyclerView>(R.id.tasks).run {
            adapter = taskAdapter
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
        scope.launch {
            val task = db.taskDao().getById(id)
            db.taskDao().delete(task)
        }
    }
}