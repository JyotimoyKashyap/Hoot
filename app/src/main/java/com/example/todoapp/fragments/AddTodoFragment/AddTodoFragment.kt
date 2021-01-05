package com.example.todoapp.fragments.AddTodoFragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.*
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority
import com.example.todoapp.data.models.TodoData
import com.example.todoapp.data.viewmodel.TodoViewModel
import com.example.todoapp.databinding.FragmentAddTodoBinding
import com.example.todoapp.fragments.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialSharedAxis


class AddTodoFragment : Fragment() {

    private var _binding: FragmentAddTodoBinding? = null
    private val binding get() = _binding!!
    private val mTodoViewModel : TodoViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddTodoBinding.inflate(inflater, container, false)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            setPathMotion(MaterialArcMotion())
            scrimColor = Color.TRANSPARENT
            containerColor = activity?.resources?.getColor(R.color.tranform_color)!!
            startContainerColor = activity?.resources?.getColor(R.color.tranform_color)!!
            endContainerColor = activity?.resources?.getColor(R.color.tranform_color)!!
        }




        binding.addBottomAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_save ->{
                    insertIntoDatabase()
                    true
                }
                else -> false
            }
        }

        return binding.root
    }

    private fun insertIntoDatabase() {
        binding.run {
            val title = addTodoTitleEditText.text.toString()
            val priority = prioritySpinnerAddTodo.selectedItem.toString()
            val description = addTodoDescEditText.text.toString()

            val validation = sharedViewModel.verifyDataFromUser(title, description)

            if(validation){
                //insert data into the database
                val newData = TodoData(
                    0,
                    title,
                    sharedViewModel.parsePriority(priority),
                    description
                )

                mTodoViewModel.insertData(newData)
                Toast.makeText(context, "Todo Added" , Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }else Toast.makeText(context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}