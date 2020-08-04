package com.renanparis.chuckjokes.ui.dialog

import android.app.AlertDialog
import android.content.Context
import com.renanparis.chuckjokes.R.string.*

class RemoveFavoriteJokeDialog(private val context: Context,
                               var onItemClickListener: () -> Unit = {}) {

    fun show() {
        AlertDialog.Builder(context)
                .setMessage(context.getString(message_confirm_remove_favorite_joke))
                .setNegativeButton(context.getString(name_negative_button_remove_favorite_joke_dialog)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(context.getString(name_positive_button_remove_favorite_joke_dialog)) { dialog, _ ->
                    dialog.dismiss()
                    onItemClickListener()
                }
                .show()
    }
}