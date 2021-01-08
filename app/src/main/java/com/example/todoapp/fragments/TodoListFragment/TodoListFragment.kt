package com.example.todoapp.fragments.TodoListFragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.models.TodoData
import com.example.todoapp.data.viewmodel.TodoViewModel
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis


class TodoListFragment : Fragment() , TodoListAdapter.TodoAdapterListener{

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!
    private val todoListAdapter : TodoListAdapter by lazy { TodoListAdapter(this) }
    private val todoViewModel : TodoViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.show()
        exitTransition = MaterialElevationScale(false).apply {
            duration = 300.toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = 300.toLong()
        }
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)

        //postpone enter transition
        postponeEnterTransition()
        binding.root.doOnPreDraw { startPostponedEnterTransition() }

        //for the options menu on top of action bar or toolbar
        setHasOptionsMenu(true)
        binding.listBottomappbar.performShow()



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
                listBottomappbar.performHide()
            }

            //handling click events in bottom App bar
            listBottomappbar.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_delete_all ->{
                        confirmDeleteAll()
                        true
                    }
                    R.id.menu_sort_by ->{
                        true
                    }else -> false
                }
            }
        }



        //returning view root for layout inflation
        return binding.root
    }

    private fun confirmDeleteAll() {
        val deleteAllBottomSheetDialogFragment = DeleteAllBottomSheetDialogFragment()
        deleteAllBottomSheetDialogFragment.show(parentFragmentManager, "Delete")
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_fragment_menu_items, menu)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTodoClicked(cardView: View, todoData: TodoData) {
        exitTransition = MaterialElevationScale(false).apply {
            duration = 300.toLong()
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = 300.toLong()
        }
        val todoDetailTransitionName = getString(R.string.todo_detail_transform)
        val extras = FragmentNavigatorExtras(cardView to todoDetailTransitionName)
        val directions = TodoListFragmentDirections.actionTodoListFragmentToUpdateTodoFragment(todoData, todoData.id)
        findNavController().navigate(directions, extras)
    }
}