package com.example.todoapp.fragments.UpdateTodoFragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority
import com.example.todoapp.data.models.TodoData
import com.example.todoapp.data.viewmodel.TodoViewModel
import com.example.todoapp.databinding.FragmentUpdateTodoBinding
import com.example.todoapp.fragments.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform


class UpdateTodoFragment : Fragment() {

    private var _binding : FragmentUpdateTodoBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateTodoFragmentArgs>()
    private val sharedViewModel : SharedViewModel by viewModels()
    private val todoViewModel : TodoViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateTodoBinding.inflate(inflater, container, false)
        //configuring material container transform for this
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            setPathMotion(MaterialArcMotion())
            scrimColor = Color.TRANSPARENT
            drawingViewId = R.id.navhostfragment
            containerColor = activity?.resources?.getColor(R.color.tranform_color)!!
            startContainerColor = activity?.resources?.getColor(R.color.tranform_color)!!
            endContainerColor = activity?.resources?.getColor(R.color.tranform_color)!!
        }



        binding.run {
            updateTodoEditText.setText(args.currentItem.title)
            updateTodoDescEditText.setText(args.currentItem.desc)
            prioritySpinnerUpdateTodo.setSelection(sharedViewModel.parsePriorityToInt(args.currentItem.priority))

            //bottom app bar item selector
            updateBottomAppBar.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_update ->{
                        updateTodo()
                        true
                    }
                    R.id.menu_delete_single ->{
                        confirmDeleteTodo()
                        true
                    }
                    else -> false
                }
            }
        }

        return binding.root
    }

    private fun confirmDeleteTodo() {
        binding.run {
            val title = updateTodoEditText.text.toString()
            val desc = updateTodoDescEditText.text.toString()
            val getPriority = prioritySpinnerUpdateTodo.selectedItem.toString()

            //will be deleted tododata
            val deleteItem = TodoData(
                args.currentItem.id,
                title,
                sharedViewModel.parsePriority(getPriority),
                desc
            )


            val directions = UpdateTodoFragmentDirections.actionUpdateTodoFragmentToDeleteBottomSheetDialogFragment(deleteItem)
            findNavController().navigate(directions)
        }

    }


    private fun updateTodo() {
        binding.run {
            val title = updateTodoEditText.text.toString()
            val desc = updateTodoDescEditText.text.toString()
            val getPriority = prioritySpinnerUpdateTodo.selectedItem.toString()

            val validation = sharedViewModel.verifyDataFromUser(title, desc)

            if(validation){
                //update current item
                val updateItem = TodoData(
                    args.currentItem.id,
                    title,
                    sharedViewModel.parsePriority(getPriority),
                    desc
                )

                todoViewModel.updateData(updateItem)
                Toast.makeText(context,"Todo Updated", Toast.LENGTH_SHORT).show()

                //now navigate back to the previous fragment
                findNavController().popBackStack()
            }else{
                Toast.makeText(context, "Please fill in all the details" , Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}