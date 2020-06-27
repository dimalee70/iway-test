package kz.v.ui_components.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kz.v.ui_components.dialogs.DialogFactory

abstract class BaseFragment : Fragment() {
    private lateinit var progressDialog: Dialog
    private lateinit var errorDialog: Dialog
    private lateinit var successDialog: Dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
    }

    protected open fun setUI() {
        progressDialog = DialogFactory.getProgressDialog(requireContext())
        errorDialog = DialogFactory.getErrorDialog(requireContext())
        successDialog = DialogFactory.getSuccessDialog(requireContext())
    }

    protected open fun showProgress() {
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }
    }

    protected open fun showSuccess() {
        if (!successDialog.isShowing)
            successDialog.show()
    }

    protected fun closeSuccess() {
        if (successDialog.isShowing)
            successDialog.dismiss()
    }

    protected fun closeProgress() {
        if (progressDialog.isShowing)
            progressDialog.dismiss()
    }

    protected open fun showError() {
        if (!errorDialog.isShowing) {
            closeProgress()
            errorDialog.show()
        }
    }
}