package com.example.todoapp.fragments.TodoListFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.google.android.material.transition.MaterialElevationScale


class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)

        binding.run {
            listFab.setOnClickListener {
                findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment)
            }
        }


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_fragment_menu_items, menu)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}