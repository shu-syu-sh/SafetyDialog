package jp.shusyush.android.safetydialog.samples;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import jp.shusyush.android.safetydialog.OnClickListener;
import jp.shusyush.android.safetydialog.SafetyDialog;
import jp.shusyush.android.safetydialog.SimpleDialogFragment;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SafetyDialog.show(this, 1, SimpleDialogFragment.Builder.build("Sample Message.",
                false, "OK"));
    }

    @Override
    public void onClick(@NotNull DialogFragment f, int requestCode, int which) {
        Log.d(getClass().getSimpleName(), "onClick - requestCode = " + requestCode + ", which = "
                + which);
    }
}
