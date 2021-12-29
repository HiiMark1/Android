package com.example.myapplication.models

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    suspend fun getAll(): List<Task>
    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getById(id: Int): Task
    @Insert
    suspend fun save(task: Task)
    @Update
    suspend fun update(task: Task)
    @Query("DELETE FROM task")
    suspend fun deleteAll()
    @Delete
    suspend fun delete(task: Task)
}