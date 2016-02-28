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
class SimpleDialogFragment : SafetyDialogFragment() {

    companion object Builder {
        internal const val POSITIVE_BUTTON_TITLE = "SimpleDialogFragment.POSITIVE_BUTTON_TITLE"
        internal const val NEGATIVE_BUTTON_TITLE = "SimpleDialogFragment.NEGATIVE_BUTTON_TITLE"
        internal const val TITLE = "SimpleDialogFragment.TITLE"
        internal const val MESSAGE = "SimpleDialogFragment.MESSAGE"
        internal const val CANCELABLE = "SimpleDialogFragment.CANCELABLE"

        @JvmOverloads
        fun build(message: CharSequence,
                  cancelable: Boolean = false,
                  positiveButtonTitle: CharSequence? = null,
                  negativeButtonTitle: CharSequence? = null,
                  title: CharSequence? = null): SimpleDialogFragment {
            return SimpleDialogFragment().apply {
                arguments = (arguments ?: Bundle()).apply {
                    listOf(TITLE, POSITIVE_BUTTON_TITLE, NEGATIVE_BUTTON_TITLE)
                            .zip(listOf(title, positiveButtonTitle, negativeButtonTitle))
                            .asSequence()
                            .filter { it.second != null }
                            .forEach { putCharSequence(it.first, it.second) }
                    putCharSequence(MESSAGE, message)
                    putBoolean(CANCELABLE, cancelable)
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = with(AlertDialog.Builder(context)) {
        val args = arguments
                ?: throw IllegalArgumentException("arguments is null. Did you create instance from Builder#build()?.")
        args.getCharSequence(POSITIVE_BUTTON_TITLE)?.run { setPositiveButton(this, adapter) }
        args.getCharSequence(NEGATIVE_BUTTON_TITLE)?.run { setNegativeButton(this, adapter) }
        args.getCharSequence(TITLE)?.run { setTitle(this) }
        setMessage(args.getCharSequence(MESSAGE)
                ?: throw IllegalArgumentException("message is null. Did you create instance from Builder#build()?."))
        this@SimpleDialogFragment.isCancelable = args.getBoolean(CANCELABLE, true)
        create()
    }

}