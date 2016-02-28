package jp.shusyush.android.safetydialog

import android.content.DialogInterface
import android.support.v4.app.DialogFragment

open class SafetyDialogFragment : DialogFragment(), Safety {

    protected val adapter = DialogInterface.OnClickListener { dialogInterface, which ->
        val targetFragment = targetFragment as? OnClickListener
        if (null == targetFragment?.onClick(this, targetRequestCode, which)) {
            val requestCode = arguments?.getInt(REQUEST_CODE, 0) ?: 0
            val l = activity as? OnClickListener
            l?.onClick(this, requestCode, which)
        }
    }
}