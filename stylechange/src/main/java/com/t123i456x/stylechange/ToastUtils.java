package com.t123i456x.stylechange;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast = null;

    public static void showToast(Context mContext,String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

    public static void showCustomImgToast(Context mContext,String text) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.toast_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.toast_image);
        imageView.setBackgroundResource(android.R.drawable.star_big_on);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);
        Toast toast = null;
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(mContext);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }

    public static void showCustomToastCenter(Context mContext,String text, int imgResId) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.toast_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.toast_image);
        imageView.setBackgroundResource(imgResId);
        TextView t = (TextView) view.findViewById(R.id.toast_text);
        t.setText(text);
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(mContext);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}

