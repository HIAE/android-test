package com.renanparis.chuckjokes.ui.activity.extensions

import android.app.Activity
import android.widget.Toast
import com.renanparis.chuckjokes.R
import com.renanparis.chuckjokes.utils.Status


private fun Activity.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.validateRemovedFavorite(status: Status) {
    if (status == Status.SUCCESS) {
        showMessage(getString(R.string.message_remove_favorite))
    } else if (status == Status.ERROR) {
        showMessage(getString(R.string.message_remove_favorite_error))
    }
}

fun Activity.validateSavedFavorite(status: Status) {
    if (status == Status.SUCCESS) {
        showMessage(getString(R.string.message_add_favorite))
    } else if (status == Status.ERROR) {
        showMessage(getString(R.string.message_add_favorite_error))
    }
}