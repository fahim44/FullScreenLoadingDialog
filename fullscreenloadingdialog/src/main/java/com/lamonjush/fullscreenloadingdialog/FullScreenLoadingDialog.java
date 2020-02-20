package com.lamonjush.fullscreenloadingdialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;

import java.util.Objects;

@UiThread
public class FullScreenLoadingDialog {
    private static FullScreenLoadingDialog instance;

    private AlertDialog dialog;

    private FullScreenLoadingDialog() {
    }

    public static FullScreenLoadingDialog getInstance() {
        if (instance == null) {
            synchronized (FullScreenLoadingDialog.class) {
                if (instance == null) {
                    instance = new FullScreenLoadingDialog();
                }
            }
        }
        return instance;
    }

    @SuppressLint("InflateParams")
    public void show(Context context) {
        if (context != null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_loading_view, null, false);
            AlertDialog.Builder builder = new AlertDialog.Builder(context, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
            builder.setView(view).setCancelable(false);
            dialog = builder.create();
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(
                    new ColorDrawable(Color.parseColor("#88222222")));
            Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.CENTER);
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null) {
            new Handler().postDelayed(() -> {
                try {
                    dialog.dismiss();
                    dialog = null;
                } catch (Exception ignored) {
                }
            }, 50);
        }
    }
}
