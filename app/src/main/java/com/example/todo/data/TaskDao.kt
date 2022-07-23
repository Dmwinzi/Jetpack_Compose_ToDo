package com.example.todo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addtask(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun getalltasks():LiveData<List<TaskEntity>>

    @Query("DELETE FROM tasks where id =:id")
    suspend fun delete(id: Int)



}