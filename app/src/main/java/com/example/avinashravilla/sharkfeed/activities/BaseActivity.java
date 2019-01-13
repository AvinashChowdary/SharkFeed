package com.example.avinashravilla.sharkfeed.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.avinashravilla.sharkfeed.R;

import butterknife.ButterKnife;

/**
 * Base for activities containing
 * abstract getting layout from extended class
 * butterknife initialization
 * progressbar show and dismiss
 * alert dialog with message and cancel button
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        //Binding Butter knife
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * for assigning layout before binding
     *
     * @return
     */
    @LayoutRes
    public abstract int getContentViewId();

    public void showProgress() {
        if(progress != null) {
            progress.show();
        } else {
            progress = new ProgressDialog(this);
            progress.setMessage(getResources().getString(R.string.loading_sharks));
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();
        }
    }

    /**
     * removing progress bar
     */
    public void dismissProgress() {
        if(progress != null) {
            progress.dismiss();
        }
    }

    /**
     *
     * @param message
     * displays message along with cancel button
     * @return
     */
    public Dialog showDialogWithMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
        return dialog;
    }
}
