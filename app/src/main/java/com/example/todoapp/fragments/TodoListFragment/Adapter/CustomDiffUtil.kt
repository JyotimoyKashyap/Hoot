package com.example.todoapp.fragments.TodoListFragment.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.data.models.TodoData

class CustomDiffUtil : DiffUtil.ItemCallback<TodoData>() {
    override fun areItemsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
        return oldItem.title == newItem.title && oldItem.desc == newItem.desc && oldItem.priority == newItem.priority
    }
}