package com.example.todoapp.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.TodoDatabase
import com.example.todoapp.data.models.TodoData
import com.example.todoapp.data.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {

    private val todoDao = TodoDatabase.getDatabase(application).todoDao()
    private val repository: TodoRepository


    val getAllData : LiveData<List<TodoData>>

    init {
        repository = TodoRepository(todoDao)
        getAllData = repository.getAllData
    }

    fun insertData(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(todoData)
        }
    }

    fun updateData(todoData: TodoData){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(todoData)
        }
    }

    fun deleteSingleData(todoData: TodoData){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteSingleData(todoData)
        }
    }
}