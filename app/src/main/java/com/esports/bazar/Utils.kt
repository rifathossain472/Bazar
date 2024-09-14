package com.esports.bazar

import android.widget.EditText

fun EditText.isEmpty(): Boolean {
    return if (this.text.toString().isEmpty()) {
        this.error = "This field must be filled"
        true
    } else {
        false
    }
}