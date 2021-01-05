package com.example.todoapp.fragments.TodoListFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.viewmodel.TodoViewModel
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.google.android.material.transition.MaterialElevationScale


class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!
    private val todoListAdapter : TodoListAdapter by lazy { TodoListAdapter() }
    private val todoViewModel : TodoViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)

        //for the options menu on top of action bar or toolbar
        setHasOptionsMenu(true)



        //for passing the shared view for material container transform
        val extras = FragmentNavigatorExtras(binding.listFab to resources.getString(R.string.container_transform_name))

        binding.run {
            //this is for the recycler view setting
            recyclerView.adapter = todoListAdapter
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


            //setting up ViewModel for the data retrieval
            todoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
                todoListAdapter.setData(it)
            })



            // this is for the floating action button to add
            listFab.setOnClickListener {
                findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment, null, null, extras)
            }
        }



        //returning view root for layout inflation
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