package com.learnwright.kotlindi.common.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.learnwright.kotlindi.R

class ServerErrorDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle(R.string.server_error_dialog_title)
        alertDialogBuilder.setMessage(R.string.server_error_dialog_message)
        alertDialogBuilder.setPositiveButton(
            R.string.server_action_ok,
            DialogInterface.OnClickListener { _, _ -> dismiss() }
        )
        return alertDialogBuilder.create()
    }

    companion object {
        fun newInstance(): ServerErrorDialogFragment {
            return ServerErrorDialogFragment()
        }
    }
}