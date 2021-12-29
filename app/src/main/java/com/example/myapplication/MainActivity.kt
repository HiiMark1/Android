package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private val taskListFragment = TaskListFragment()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = AppDatabase(this)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        with(binding) {
            btnDeleteAll.setOnClickListener {
                scope.launch {
                    db.taskDao().deleteAll()
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, taskListFragment)
                    .commit()
            }
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container, TaskListFragment())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}