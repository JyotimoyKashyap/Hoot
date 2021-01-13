package com.example.todoapp.fragments.TodoListFragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.data.models.TodoData
import com.example.todoapp.data.viewmodel.TodoViewModel
import com.example.todoapp.databinding.FragmentTodoListBinding
import com.example.todoapp.fragments.SharedViewModel
import com.example.todoapp.fragments.TodoListFragment.Adapter.TodoListAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialSharedAxis
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator


class TodoListFragment : Fragment() , TodoListAdapter.TodoAdapterListener{

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!
    private val todoListAdapter : TodoListAdapter by lazy { TodoListAdapter(this) }
    private val todoViewModel : TodoViewModel by viewModels()
    private val sharedViewModel : SharedViewModel by viewModels()


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
        binding.lifecycleOwner = this
        binding.mSharedViewModel = sharedViewModel

        //postpone enter transition
        postponeEnterTransition()
        binding.root.doOnPreDraw { startPostponedEnterTransition() }

        //for the options menu on top of action bar or toolbar
        setHasOptionsMenu(true)
        binding.listBottomappbar.performShow()


        binding.run {
            //this is for the recycler view setting
            recyclerView.adapter = todoListAdapter
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            //swipe to delete callback
            swipeToDelete(recyclerView)


            //setting up ViewModel for the data retrieval
            todoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
                sharedViewModel.checkIfDatabaseEmpty(it)
                todoListAdapter.setData(it)
            })

            //adding animation to recycler view
            recyclerView.itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
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

    fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = todoListAdapter.dataList[viewHolder.adapterPosition]
                todoViewModel.deleteSingleData(itemToDelete)
                todoListAdapter.notifyItemRemoved(viewHolder.adapterPosition)

                //restore the deleted data
                restoreDeletedData(viewHolder.itemView, itemToDelete)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun restoreDeletedData(view : View, deletedItem: TodoData){
        val snackbar = Snackbar.make(
            view,
            "Deleted",
            Snackbar.LENGTH_LONG
        )
        snackbar.setAction("Undo"){
            todoViewModel.insertData(deletedItem)
        }

        snackbar.show()
    }
}