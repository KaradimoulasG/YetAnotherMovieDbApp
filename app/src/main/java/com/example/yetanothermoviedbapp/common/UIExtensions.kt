package com.example.yetanothermoviedbapp.common

import android.content.Context
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun <T> List<T?>?.toNotNull() = this?.filterNotNull() ?: listOf()

fun showDialog(context: Context, title: String, message: String, decline: String, accept: String) {
    MaterialAlertDialogBuilder(context)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(decline) { dialog, _ -> dialog.dismiss() }
        .setPositiveButton(accept) { dialog, _ -> dialog.dismiss() }
        .show()
}