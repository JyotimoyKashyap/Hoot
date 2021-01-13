package com.example.todoapp

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.todoapp.fragments.TodoListFragment.TodoListFragmentDirections
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BindingAdapters {
    companion object{

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view : FloatingActionButton, navigate : Boolean){
            view.setOnClickListener {
                if(navigate){
                    val directions = TodoListFragmentDirections.actionTodoListFragmentToAddTodoFragment()
                    val extras = FragmentNavigatorExtras(view to view.context.getString(R.string.container_transform_name))
                    view.findNavController().navigate(directions, extras)
                }
            }
        }


        @BindingAdapter("android:emptyDatabase")
        @JvmStatic
        fun emptyDatabase(view : View, emptyDatabase : MutableLiveData<Boolean>){
            when(emptyDatabase.value){
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }
    }
}