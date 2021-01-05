package com.example.todoapp.fragments.UpdateTodoFragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority
import com.example.todoapp.databinding.FragmentUpdateTodoBinding
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform


class UpdateTodoFragment : Fragment() {

    private var _binding : FragmentUpdateTodoBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<UpdateTodoFragmentArgs>()



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
            prioritySpinnerUpdateTodo.setSelection(parsePriority(args.currentItem.priority))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parsePriority(priority: Priority) : Int{
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }
    }
}