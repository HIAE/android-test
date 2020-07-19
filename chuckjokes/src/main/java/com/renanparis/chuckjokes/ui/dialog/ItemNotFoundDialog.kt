package com.renanparis.chuckjokes.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.renanparis.chuckjokes.R

class ItemNotFoundDialog(private val context: Context,
                         var onItemClickListener: (dialog: DialogInterface) -> Unit = {}) {


    fun show() {
        AlertDialog.Builder(context)
                .setMessage(context.getString(R.string.message_warning_dialog))
                .setPositiveButton(context.getString(R.string.name_botton_warning_dialog)) { dialog, _ ->
                    onItemClickListener(dialog)
                }.show()

    }
}