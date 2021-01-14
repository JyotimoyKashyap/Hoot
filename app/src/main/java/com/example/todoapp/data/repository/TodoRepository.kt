package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.TodoDao
import com.example.todoapp.data.models.TodoData

class TodoRepository(private val todoDao: TodoDao) {

    val getAllData: LiveData<List<TodoData>> = todoDao.getAllData()
    val sortByHighPriority : LiveData<List<TodoData>> = todoDao.sortByHighPriority()
    val sortByLowPriority : LiveData<List<TodoData>> = todoDao.sortByLowPriority()

    suspend fun insertData(todoData: TodoData){
        todoDao.insertData(todoData)
    }

    suspend fun updateData(todoData: TodoData){
        todoDao.updateData(todoData)
    }

    suspend fun deleteSingleData(todoData: TodoData){
        todoDao.deleteSingleData(todoData)
    }

    suspend fun deleteAllData(){
        todoDao.deleteAllDAta()
    }

    fun searchDatabase(searchQuery: String) : LiveData<List<TodoData>>{
        return todoDao.searchDatabase(searchQuery)
    }
}