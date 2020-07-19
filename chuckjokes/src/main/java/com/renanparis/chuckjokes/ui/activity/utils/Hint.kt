package com.renanparis.chuckjokes.ui.activity.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showMessage(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}