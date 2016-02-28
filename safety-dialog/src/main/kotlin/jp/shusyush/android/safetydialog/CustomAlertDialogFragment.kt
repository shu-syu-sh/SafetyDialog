package jp.shusyush.android.safetydialog

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View

/**
 * Abstract DialogFragment.
 *
 * Created by shu-syu-sh.
 */
abstract class CustomAlertDialogFragment : SafetyDialogFragment() {

    abstract val contentView: View

    open val positiveButtonTitle: CharSequence? = null

    open val negativeButtonTitle: CharSequence? = null

    open val cancelable = false

    override fun onCreateDialog(savedInstanceState: Bundle?) = with(AlertDialog.Builder(context)) {
        setView(contentView)
        positiveButtonTitle?.run { setPositiveButton(this, adapter) }
        negativeButtonTitle?.run { setNegativeButton(this, adapter) }
        this@CustomAlertDialogFragment.setCancelable(cancelable)
        create()
    }

}
