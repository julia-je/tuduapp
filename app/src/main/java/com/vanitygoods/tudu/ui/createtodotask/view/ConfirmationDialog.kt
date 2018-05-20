package com.vanitygoods.tudu.ui.createtodotask.view

import android.app.AlertDialog
import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.vanitygoods.tudu.R
import com.vanitygoods.tudu.ui.createtodotask.viewmodel.CreateTaskViewModel
import com.vanitygoods.tudu.ui.createtodotask.viewmodel.CreateTaskViewModelContract


class ConfirmationDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val viewModel: CreateTaskViewModelContract? = activity?.let { ViewModelProviders.of(it).get(CreateTaskViewModel::class.java) }
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.dialog_discard_changes)
            .setPositiveButton(R.string.dialog_ok, { _, _ ->
                viewModel?.hideConfirmationDialog()
                viewModel?.finishActivity()
            })
            .setNegativeButton(R.string.dialog_cancel, { _, _ ->
                viewModel?.hideConfirmationDialog()
            })
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }
}