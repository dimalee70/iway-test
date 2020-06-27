package kz.v.ui_components.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import kz.v.ui_components.R

object DialogFactory {
    fun getProgressDialog(context: Context): Dialog =
        AlertDialog.Builder(context).setCancelable(false).setView(R.layout.dialog_progress).create()

    fun getErrorDialog(context: Context): Dialog =
        AlertDialog.Builder(context).setCancelable(true).setView(R.layout.dialog_error).create()

    fun getSuccessDialog(context: Context): Dialog =
        AlertDialog.Builder(context).setCancelable(true).setView(R.layout.dialog_success).create()
}