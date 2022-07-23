package com.example.todo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var  id : Int = 0,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name ="task")
    var  task: String
) {

}