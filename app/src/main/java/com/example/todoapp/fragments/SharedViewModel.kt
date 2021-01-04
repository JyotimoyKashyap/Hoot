package com.example.todoapp.fragments

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.data.models.Priority

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    fun verifyDataFromUser(title: String, description: String) : Boolean{
        return if(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
            false
        else !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priority: String): Priority {
        return when(priority){
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}
            else -> Priority.LOW
        }
    }
}