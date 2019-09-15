package com.dev.android.mywoman.utils

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.dev.android.mywoman.R

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
fun Context.showLoadingDialog() : Dialog {
    val view = LayoutInflater.from(this).inflate(R.layout.custom_loading_layout, null)
    val alertDialog = AlertDialog.Builder(this).setView(view)
    val dialog = alertDialog.create()
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.setCanceledOnTouchOutside(false)
    dialog.show()
    return dialog
}