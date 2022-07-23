package com.example.todo.data

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TaskRepository(application: Application) {

    private var taskDao:TaskDao

    init {
        val database  = TaskDatabase.getdabaseinstance(application)
        taskDao = database.taskDao()
    }

    val fetchall :LiveData<List<TaskEntity>> = taskDao.getalltasks()

    suspend fun inserttask(taskEntity: TaskEntity){
        taskDao.addtask(taskEntity)
    }

    suspend fun delete(id: Int){
        taskDao.delete(id)
    }

    init {
        val database  = TaskDatabase.getdabaseinstance(application)
        taskDao = database.taskDao()
    }

}