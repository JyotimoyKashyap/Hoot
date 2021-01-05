package com.example.todoapp.fragments.TodoListFragment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.data.models.Priority
import com.example.todoapp.data.models.TodoData
import com.example.todoapp.databinding.RowLayoutBinding
import java.util.zip.Inflater

class TodoListAdapter(private val listener : TodoAdapterListener) : RecyclerView.Adapter<TodoListAdapter.MyViewHolder>() {

    var dataList = emptyList<TodoData>()

    class MyViewHolder(val viewBinding: RowLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewBinding.run {
            titleTextView.text = dataList[position].title
            descTextView.text = dataList[position].desc

            val priority = dataList[position].priority
            when (priority) {
                Priority.HIGH -> priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.viewBinding.root.context,
                        R.color.red
                    )
                )
                Priority.MEDIUM -> priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.viewBinding.root.context,
                        R.color.yellow
                    )
                )
                Priority.LOW -> priorityIndicator.setCardBackgroundColor(
                    ContextCompat.getColor(
                        holder.viewBinding.root.context,
                        R.color.green
                    )
                )
            }
            //extras for the material container transform
            val extras = FragmentNavigatorExtras(todoCardItem to "container_transform_for_row_item")

            //on item clicked
            todoCardItem.setOnClickListener{
                listener.onTodoClicked(todoCardItem, dataList[position])
            }


        }


    }

    fun setData(todoData: List<TodoData>){
        this.dataList = todoData
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    interface TodoAdapterListener{
        fun onTodoClicked(cardView: View, todoData: TodoData)
    }
}