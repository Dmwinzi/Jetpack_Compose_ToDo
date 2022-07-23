package com.example.todo.data

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskviewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository : TaskRepository = TaskRepository(application)

     fun fetchalltasks() : LiveData<List<TaskEntity>>{
         return taskRepository.fetchall
     }

    fun  inserttask(taskEntity: TaskEntity){
        viewModelScope.launch {
            taskRepository.inserttask(taskEntity = taskEntity)
        }
    }

    fun deletetask(id: Int){
        viewModelScope.launch {
            taskRepository.delete(id)
        }
    }

}