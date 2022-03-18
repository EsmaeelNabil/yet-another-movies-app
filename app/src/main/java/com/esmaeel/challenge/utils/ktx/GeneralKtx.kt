package com.esmaeel.challenge.utils.ktx

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun Fragment.showToast(message: String?) = message?.let {
    makeToast(requireContext(), message)
}

fun makeToast(context: Context, message: String?) =
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

fun Activity.showToast(message: String?) = message?.let {
    makeToast(this, message)
}

fun Date?.getFormattedApiDate(datePattern: String = "yyyy"): String {
    this ?: return ""
    return SimpleDateFormat(datePattern, Locale.ROOT).format(this)
}


