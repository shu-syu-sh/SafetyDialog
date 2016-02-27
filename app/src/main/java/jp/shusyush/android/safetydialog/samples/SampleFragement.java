package jp.shusyush.android.safetydialog.samples;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import jp.shusyush.android.safetydialog.SimpleDialogFragment;

/**
 * Created by r-kobayashi on 2016/02/27.
 */
public class SampleFragement extends SimpleDialogFragment {

    public SampleFragement() {
        super();
    }

    @Nullable
    @Override
    public CharSequence getPositiveButtonTitle() {
        return "OK";
    }

    @Nullable
    @Override
    public CharSequence getNegativeButtonTitle() {
        return null;
    }

    @Nullable
    @Override
    public CharSequence getTitle() {
        return null;
    }

    @Override
    public boolean getCancelable() {
        return false;
    }

    @NotNull
    @Override
    public CharSequence getMessage() {
        return "sample message.";
    }
}
