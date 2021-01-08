package com.example.todoapp.fragments.TodoListFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.todoapp.R
import com.example.todoapp.data.viewmodel.TodoViewModel
import com.example.todoapp.databinding.FragmentDeleteAllBottomSheetDialogBinding
import com.example.todoapp.databinding.FragmentDeleteBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DeleteAllBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding : FragmentDeleteAllBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private val todoViewModel : TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDeleteAllBottomSheetDialogBinding.inflate(inflater, container, false)

        binding.deleteAllDataBottomSheetConfirmBtn.setOnClickListener {
            todoViewModel.deleteAllData()
            Toast.makeText(context, "All Todo Delete" , Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return binding.root
    }


}