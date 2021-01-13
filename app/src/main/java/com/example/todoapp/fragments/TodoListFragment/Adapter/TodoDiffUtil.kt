package com.example.todoapp.fragments.TodoListFragment.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.todoapp.data.models.TodoData

class TodoDiffUtil(
    private val oldList : List<TodoData>,
    private val newList : List<TodoData>
    ) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].desc == newList[newItemPosition].desc
                && oldList[oldItemPosition].priority == newList[newItemPosition].priority
    }

}