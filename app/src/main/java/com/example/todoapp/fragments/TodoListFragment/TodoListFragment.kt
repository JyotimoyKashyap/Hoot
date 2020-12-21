package com.example.todoapp.fragments.TodoListFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_todo_list.view.*


class TodoListFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        val view = inflater.inflate(R.layout.fragment_todo_list, container, false)

        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment)
        }
        return view
    }


}