package com.example.todoapp.fragments.UpdateTodoFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentDeleteBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DeleteBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDeleteBottomSheetDialogBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDeleteBottomSheetDialogBinding.inflate(inflater, container, false)

        return binding.root
    }


}