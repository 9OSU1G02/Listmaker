package com.GOSU1902.listmaker.ui.main.ui.listdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.GOSU1902.listmaker.TaskList
import com.GOSU1902.listmaker.databinding.ListItemViewHolderBinding

class ListItemsRecyclerViewAdapter(var list: TaskList) : RecyclerView.Adapter<ListItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType:
        Int
    ): ListItemViewHolder {
        val binding =
            ListItemViewHolderBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        return ListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.binding.textViewTask.text = list.tasks[position]
    }
}