package com.lamonjush.fullscreenloadingdialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.SpriteFactory;

import java.util.Objects;

@UiThread
public class FullScreenLoadingDialog {
    private static FullScreenLoadingDialog instance;

    private AlertDialog dialog;

    private int drawable = 0;
    private int spinKitColor = R.color.colorWhite;
    private int backgroundColor = R.color.dialogDefaultBackGround;
    private SpinKitStyle spinKitStyle = SpinKitStyle.CIRCLE;

    private FullScreenLoadingDialog() {
    }

    public FullScreenLoadingDialog setDrawable(@DrawableRes int drawable) {
        this.drawable = drawable;
        return this;
    }

    public FullScreenLoadingDialog setSpinKitColor(@ColorRes int spinKitColor) {
        this.spinKitColor = spinKitColor;
        return this;
    }

    public FullScreenLoadingDialog setBackgroundColor(@ColorRes int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public FullScreenLoadingDialog setSpinKitStyle(SpinKitStyle spinKitStyle) {
        this.spinKitStyle = spinKitStyle;
        return this;
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

            SpinKitView spinKitView = view.findViewById(R.id.spinKitView);

            //show drawable instead of SpinKitView
            if (drawable != 0) {
                ImageView imageView = view.findViewById(R.id.imageView);
                imageView.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(drawable)
                        .centerCrop()
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                        .into(imageView);

                spinKitView.setVisibility(View.GONE);
            } else {
                spinKitView.setColor(ContextCompat.getColor(context, spinKitColor));
                spinKitView.setIndeterminateDrawable(SpriteFactory.create(spinKitStyle.convertToLibStyle()));
            }

            if (dialog != null) {
                new Handler().postDelayed(() -> {
                    try {
                        dialog.dismiss();
                        showLoader(context, builder);
                    } catch (Exception ignored) {
                    }
                }, 50);
            } else {
                showLoader(context, builder);
            }

        }
    }

    private void showLoader(Context context, AlertDialog.Builder builder) {
        dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(
                new ColorDrawable(ContextCompat.getColor(context, backgroundColor)));
        Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.CENTER);
        dialog.show();
    }

    public boolean isShowing() {
        if (dialog != null)
            return dialog.isShowing();
        return false;
    }

    public void dismiss() {
        if (dialog != null) {
            new Handler().postDelayed(() -> {
                try {
                    if (dialog != null)
                        dialog.dismiss();
                    dialog = null;
                } catch (Exception ignored) {
                }
            }, 50);
        }
    }
}
