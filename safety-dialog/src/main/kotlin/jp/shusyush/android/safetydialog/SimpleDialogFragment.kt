package jp.shusyush.android.safetydialog

import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

/**
 * Abstract DialogFragment.
 *
 * Created by shu-syu-sh.
 */
abstract class SimpleDialogFragment : DialogFragment(), Safety {

    interface OnClickListener {
        fun onClick(f: DialogFragment, requestCode: Int, which: Int)
    }

    open val positiveButtonTitle: CharSequence? = null

    open val negativeButtonTitle: CharSequence? = null

    open val title: CharSequence? = null

    abstract val message: CharSequence

    open val cancelable = false

    internal val adapter = DialogInterface.OnClickListener { dialogInterface, which ->
        val targetFragment = targetFragment as? OnClickListener
        if (null == targetFragment?.onClick(this, targetRequestCode, which)) {
            val requestCode = arguments?.getInt(REQUEST_CODE, 0) ?: 0
            val l = activity as? OnClickListener
            l?.onClick(this, requestCode, which)
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = with(AlertDialog.Builder(context)) {
        positiveButtonTitle?.run { setPositiveButton(this, adapter) }
        negativeButtonTitle?.run { setNegativeButton(this, adapter) }
        title?.run { setTitle(this) }
        setMessage(message)
        this@SimpleDialogFragment.setCancelable(cancelable)
        create()
    }

}