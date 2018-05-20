package com.vanitygoods.tudu.ui.main.view

import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.vanitygoods.tudu.R
import com.vanitygoods.tudu.data.TodoTask
import com.vanitygoods.tudu.ui.main.ItemWrapper
import com.vanitygoods.tudu.ui.main.viewmodel.OnDoneTaskListener
import com.vanitygoods.tudu.util.DateTimeFormats
import kotlinx.android.synthetic.main.recycler_view_date.view.*
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import org.threeten.bp.LocalDate


sealed class TodoViewHolder(itemView: View,
                            protected val onDoneTaskListener: OnDoneTaskListener? = null) : RecyclerView.ViewHolder(itemView) {
    open fun bind(itemWrapper: ItemWrapper) {
        //no-op
    }
}

class TodoDateViewHolder(itemView: View) : TodoViewHolder(itemView) {
    override fun bind(itemWrapper: ItemWrapper) {
        val date = itemWrapper.item as? LocalDate ?: return
        itemView.date_view.text = date.format(DateTimeFormats.longDateFormat)
    }
}

class TodoTaskViewHolder(itemView: View, onDoneTaskListener: OnDoneTaskListener) : TodoViewHolder(itemView, onDoneTaskListener) {

    private val doneColor = ContextCompat.getColor(itemView.context, R.color.grey)
    private val undoneColor = ContextCompat.getColor(itemView.context, R.color.black)

    override fun bind(itemWrapper: ItemWrapper) {
        val task = itemWrapper.item as? TodoTask ?: return
        task.id ?: return

        with(itemView) {
            textView1.text = task.title
            textView2.text = task.summary
            time.text = task.dueDate.toLocalTime().format(DateTimeFormats.timeFormat)
            priority.visibility = if (task.isImportant) View.VISIBLE else View.GONE

            with(task.isDone) {
                check_box.isChecked = this
                textView1.setTextStyle(this)
                textView2.setTextStyle(this)
            }

            check_box.setOnClickListener {
                onDoneTaskListener?.onDoneStatusChanged(check_box.isChecked, task.id)
            }
        }
    }

    private fun TextView.setTextStyle(isDone: Boolean) {
        val color = if (isDone) doneColor else undoneColor
        val flags = if (isDone) Paint.STRIKE_THRU_TEXT_FLAG else 0
        this.setTextColor(color)
        this.paintFlags = flags
    }
}