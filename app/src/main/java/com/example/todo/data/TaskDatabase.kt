package com.example.todo.data

import android.content.Context
import androidx.compose.runtime.CompositionContext
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao


    companion object{
        @Volatile
        private var INSTANCE:TaskDatabase?= null

        fun getdabaseinstance(context: Context):TaskDatabase{
             var instance  = INSTANCE


            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,TaskDatabase::class.java,"TaskDb")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }



        }

    }



}