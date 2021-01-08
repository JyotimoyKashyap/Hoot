package com.example.todoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoapp.data.models.TodoData


@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData() : LiveData<List<TodoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(todoData: TodoData)

    @Update
    suspend fun updateData(todoData: TodoData)

    @Delete
    suspend fun deleteSingleData(todoData: TodoData)

}