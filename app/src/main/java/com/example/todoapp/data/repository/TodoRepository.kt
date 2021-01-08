package com.example.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.TodoDao
import com.example.todoapp.data.models.TodoData

class TodoRepository(private val todoDao: TodoDao) {

    val getAllData: LiveData<List<TodoData>> = todoDao.getAllData()

    suspend fun insertData(todoData: TodoData){
        todoDao.insertData(todoData)
    }

    suspend fun updateData(todoData: TodoData){
        todoDao.updateData(todoData)
    }
}