package com.example.gadsleaderboard.utils

import android.content.Context
import com.example.gadsleaderboard.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CommonDialog(val context: Context) {
    fun showConfirmation(message: String, confirmed: () -> Unit) {
        MaterialAlertDialogBuilder(context)
            .setMessage(message)
            .setNegativeButton("No") { _, _ -> }
            .setPositiveButton("Yes") { _, _ -> confirmed() }
            .show()
    }

    fun showSuccess() {
        MaterialAlertDialogBuilder(context)
            .setView(R.layout.dialog_success)
            .show()
    }

    fun showError() {
        MaterialAlertDialogBuilder(context)
            .setView(R.layout.dialog_error)
            .show()
    }
}