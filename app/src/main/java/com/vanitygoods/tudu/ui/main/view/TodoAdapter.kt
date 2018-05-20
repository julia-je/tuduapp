package com.vanitygoods.tudu.ui.main.view

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vanitygoods.tudu.R
import com.vanitygoods.tudu.ui.main.ItemWrapper
import com.vanitygoods.tudu.ui.main.TASK_TYPE
import com.vanitygoods.tudu.ui.main.viewmodel.OnDoneTaskListener


class TodoAdapter(private val onDoneTaskListener: OnDoneTaskListener) : RecyclerView.Adapter<TodoViewHolder>() {

    var items: List<ItemWrapper> = emptyList()
        set(value) {
            field = value.map { it.copy() }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TASK_TYPE -> TodoTaskViewHolder(inflater.inflate(R.layout.recycler_view_item, parent, false), onDoneTaskListener)
            else      -> TodoDateViewHolder(inflater.inflate(R.layout.recycler_view_date, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].type

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateList(tasks: List<ItemWrapper>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                items[oldItemPosition].getItemId() == tasks[newItemPosition].getItemId()

            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = tasks.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                items[oldItemPosition] == tasks[newItemPosition]
        })
        items = tasks
        diff.dispatchUpdatesTo(this)
    }
}