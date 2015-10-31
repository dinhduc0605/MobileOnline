package com.project.mobileonline.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by nguyendinhduc on 10/28/15.
 */
public class LoadingDialog {
    ProgressDialog dialog;

    public LoadingDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading....");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    public void show() {
        dialog.show();
    }

    public void cancel() {
        dialog.cancel();
    }
}
