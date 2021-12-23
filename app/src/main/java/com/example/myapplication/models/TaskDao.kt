package com.example.myapplication.models

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>
    @Query("SELECT * FROM task WHERE id = :id")
    fun getById(id: Int): Task
    @Insert
    fun save(task: Task)
    @Update
    fun update(task: Task)
    @Query("DELETE FROM task")
    fun deleteAll()
    @Delete
    fun delete(task: Task)
}