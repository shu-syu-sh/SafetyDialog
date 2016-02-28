@file:JvmName("SafetyDialog")

package jp.shusyush.android.safetydialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

const val TAG = "SafetyDialog.TAG"
const val REQUEST_CODE = "REQUEST_CODE"


interface Safety

interface OnClickListener {
    fun onClick(f: DialogFragment, requestCode: Int, which: Int)
}

fun <CallbackActivity, SafetyDialogFragment> show(activity: CallbackActivity,
                                                  requestCode: Int,
                                                  f: SafetyDialogFragment)
        where
        CallbackActivity : FragmentActivity,
        CallbackActivity : OnClickListener,
        SafetyDialogFragment : Safety,
        SafetyDialogFragment : DialogFragment {
    val fragmentManager = activity.supportFragmentManager
    if (null != fragmentManager.findFragmentByTag(TAG)) {
        return
    }

    f.arguments = (f.arguments ?: Bundle()).apply { putInt(REQUEST_CODE, requestCode) }
    f.show(fragmentManager, TAG)
}

fun <SafetyDialogFragment, CallbackFragment> show(activity: FragmentActivity,
                                                  requestCode: Int,
                                                  f: SafetyDialogFragment,
                                                  callBackFragment: CallbackFragment)
        where
        CallbackFragment : Fragment,
        CallbackFragment : OnClickListener,
        SafetyDialogFragment : Safety,
        SafetyDialogFragment : DialogFragment {
    val fragmentManager = activity.supportFragmentManager
    if (null != fragmentManager.findFragmentByTag(TAG)) {
        return
    }

    fragmentManager
            .beginTransaction()
            .add(callBackFragment, callBackFragment.javaClass.simpleName)
            .commit()

    f.setTargetFragment(callBackFragment, requestCode)
    f.show(fragmentManager, TAG)
}
