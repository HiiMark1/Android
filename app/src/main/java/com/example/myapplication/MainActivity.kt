package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private val taskListFragment = TaskListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = AppDatabase(this)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        with(binding) {
            btnDeleteAll.setOnClickListener {
                db.taskDao().deleteAll()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, taskListFragment)
                    .commit()
            }
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container, TaskListFragment())
            .commit()
    }
}