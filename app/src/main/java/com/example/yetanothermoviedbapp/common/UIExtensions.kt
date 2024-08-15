package com.example.yetanothermoviedbapp.common

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}


fun <T> List<T?>?.toNotNull() = this?.filterNotNull() ?: listOf()