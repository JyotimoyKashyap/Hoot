package com.example.todoapp.fragments.TodoListFragment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.models.TodoData
import com.example.todoapp.databinding.RowLayoutBinding

class TodoListAdapter(private val listener : TodoAdapterListener) : RecyclerView.Adapter<TodoListAdapter.MyViewHolder>() {

    var dataList = emptyList<TodoData>()

    class MyViewHolder(val viewBinding: RowLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
            fun bind(todoData: TodoData){
                viewBinding.todoData = todoData
                viewBinding.executePendingBindings()
            }
        companion object{
            fun from(parent: ViewGroup) : MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)

            //extras for the material container transform
            val extras = FragmentNavigatorExtras(holder.viewBinding.todoCardItem to "container_transform_for_row_item")
            holder.viewBinding.todoCardItem.transitionName = (R.string.todo_item_card + dataList[position].id).toString()

            //on item clicked
            holder.viewBinding.todoCardItem.setOnClickListener{
                listener.onTodoClicked(holder.viewBinding.todoCardItem, dataList[position])
            }
    }

    fun setData(todoData: List<TodoData>){
        val todoDiffUtil = TodoDiffUtil(dataList, todoData)
        val todoDiffResult = DiffUtil.calculateDiff(todoDiffUtil)
        this.dataList = todoData
        todoDiffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface TodoAdapterListener{
        fun onTodoClicked(cardView: View, todoData: TodoData)
    }
}