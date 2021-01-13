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

    private var dataList = emptyList<TodoData>()

    class MyViewHolder(val viewBinding: RowLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
            fun bind(todoData: TodoData){
                viewBinding.todoData = todoData
                viewBinding.executePendingBindings()
            }
        companion object{
            fun from(parent: ViewGroup) : MyViewHolder{
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

//        holder.viewBinding.run {
//            titleTextView.text = dataList[position].title
//            descTextView.text = dataList[position].desc
//
//            val priority = dataList[position].priority
//            when (priority) {
//                Priority.HIGH -> {priorityIndicator.setCardBackgroundColor(
//                    ContextCompat.getColor(
//                        holder.viewBinding.root.context,
//                        R.color.red
//                    )
//                )
//                    }
//                Priority.MEDIUM -> {priorityIndicator.setCardBackgroundColor(
//                    ContextCompat.getColor(
//                        holder.viewBinding.root.context,
//                        R.color.yellow
//                    )
//                )
//                    }
//                Priority.LOW -> {priorityIndicator.setCardBackgroundColor(
//                    ContextCompat.getColor(
//                        holder.viewBinding.root.context,
//                        R.color.green
//                    )
//                )
//                    }
//            }
            //extras for the material container transform
            val extras = FragmentNavigatorExtras(holder.viewBinding.todoCardItem to "container_transform_for_row_item")
            holder.viewBinding.todoCardItem.transitionName = (R.string.todo_item_card + dataList[position].id).toString()

            //on item clicked
            holder.viewBinding.todoCardItem.setOnClickListener{
                listener.onTodoClicked(holder.viewBinding.todoCardItem, dataList[position])
            }
//
//
//        }


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