package com.example.myapplication.models

import androidx.room.*

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String
) {
    constructor(title: String, description: String) : this(0, title, description)
}
