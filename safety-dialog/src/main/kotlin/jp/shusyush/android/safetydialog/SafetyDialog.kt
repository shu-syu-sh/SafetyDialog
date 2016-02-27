@file:JvmName("SafetyDialog")

package jp.shusyush.android.safetydialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

const val TAG = "SafetyDialog.TAG"
const val REQUEST_CODE = "REQUEST_CODE"


interface Safety

interface DialogFragmentProvider<T> where T : Safety, T : DialogFragment {
    fun provide(): T
}

fun <CallbackActivity, SafetyDialogFragment> show(activity: CallbackActivity,
                                                  requestCode: Int,
                                                  provider: DialogFragmentProvider<SafetyDialogFragment>)
        where
        CallbackActivity : FragmentActivity,
        CallbackActivity : SimpleDialogFragment.OnClickListener,
        SafetyDialogFragment : Safety,
        SafetyDialogFragment : DialogFragment {
    val fragmentManager = activity.supportFragmentManager
    if (null != fragmentManager.findFragmentByTag(TAG)) {
        return
    }

    val f = provider.provide()
    f.arguments = Bundle().apply { putInt(REQUEST_CODE, requestCode) }
    f.show(fragmentManager, TAG)
}

fun <SafetyDialogFragment, CallbackFragment> show(activity: FragmentActivity,
                                                  requestCode: Int,
                                                  provider: DialogFragmentProvider<SafetyDialogFragment>,
                                                  callBackFragment: CallbackFragment)
        where
        CallbackFragment : Fragment,
        CallbackFragment : SimpleDialogFragment.OnClickListener,
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

    val f = provider.provide()
    f.setTargetFragment(callBackFragment, requestCode)
    f.show(fragmentManager, TAG)
}
