package com.example.todoapp.fragments.UpdateTodoFragment

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
import com.example.todoapp.data.viewmodel.TodoViewModel
import com.example.todoapp.databinding.FragmentDeleteBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class DeleteBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDeleteBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private val todoViewModel : TodoViewModel by viewModels()
    private val args by navArgs<DeleteBottomSheetDialogFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDeleteBottomSheetDialogBinding.inflate(inflater, container, false)

        binding.deleteBottomSheetConfirmBtn.setOnClickListener{
            todoViewModel.deleteSingleData(args.currentDeleteItem)
            findNavController().popBackStack(R.id.updateTodoFragment, true)
            Toast.makeText(context, "Todo Deleted" ,Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return binding.root
    }


}