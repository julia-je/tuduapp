package com.vanitygoods.tudu.ui.createtodotask.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import com.vanitygoods.tudu.ui.createtodotask.viewmodel.CreateTaskViewModel
import com.vanitygoods.tudu.ui.createtodotask.viewmodel.CreateTaskViewModelContract
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    private lateinit var viewModel: CreateTaskViewModelContract

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        //DatePickerDialog is based on java.util.Calendar, where month count starts from 0
        viewModel.onDateSet(year, month + 1, dayOfMonth)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {
            viewModel = ViewModelProviders.of(it).get(CreateTaskViewModel::class.java)
            val date = viewModel.date.value ?: LocalDate.now(ZoneId.systemDefault())
            return DatePickerDialog(it, this, date.year, date.monthValue - 1, date.dayOfMonth)
        }
        return super.onCreateDialog(savedInstanceState)
    }
}