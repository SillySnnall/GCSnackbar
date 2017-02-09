package com.stringbox.gcsnackbar;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 内容：GC
 * 作者：StringBOX
 * 时间：2017/2/8 10:04
 */

public class GCSnackbar {

    /**
     * 显示，不消失
     */
    public static final int LENGTH_INDEFINITE = -2;
    /**
     * 短时间显示，消失
     */
    public static final int LENGTH_SHORT = -1;

    /**
     * 长时间显示，消失
     */
    public static final int LENGTH_LONG = 0;

    private final SparseArray<View> mViews;
    private Snackbar.SnackbarLayout mSnackbarLayout;
    private Activity mActivity;
    private Snackbar mSnackbar;

    public GCSnackbar() {
        mViews = new SparseArray<>();
    }

    /**
     * 自定义Snackbar
     *
     * @param activity
     * @param layoutResID
     */
    public GCSnackbar SnackbarDIY(Activity activity, int layoutResID, int duration) {
        mActivity = activity;
        mSnackbar = newSnackbar(activity, duration);
        View snackbarview = mSnackbar.getView();
        mSnackbarLayout = (Snackbar.SnackbarLayout) snackbarview;
        View addView = LayoutInflater.from(snackbarview.getContext()).inflate(layoutResID, null);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.gravity = Gravity.CENTER_VERTICAL;
        mSnackbarLayout.addView(addView, 0, p);
        // 获取传入布局文件的根布局颜色，设置为Snackbar的背景
        View inflate = activity.getLayoutInflater().inflate(layoutResID, null);
        Drawable background = inflate.getBackground();
        snackbarview.setBackgroundDrawable(background);
        return this;
    }

    /**
     * 显示Snackbar
     */
    public void show() {
        mSnackbar.show();
    }

    public <TView extends View> TView getView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = mSnackbarLayout.findViewById(id);
            mViews.put(id, view);
        }
        return (TView) view;
    }

    public GCSnackbar setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    public GCSnackbar setImageURI(int viewId, Uri uri) {
        ImageView view = getView(viewId);
        view.setImageURI(uri);
        return this;
    }

    public GCSnackbar setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public GCSnackbar setTextColorRes(int viewId, int textColorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mActivity.getResources().getColor(textColorRes));
        return this;
    }

    public GCSnackbar setImageResource(int viewId, int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public GCSnackbar setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public GCSnackbar setBackgroundResource(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public GCSnackbar setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public GCSnackbar setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public GCSnackbar setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    public GCSnackbar setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * 创建Snackbar对象
     *
     * @param activity
     * @param duration 显示时间 <br/>
     *                 LENGTH_INDEFINITE 显示，不消失<br/>
     *                 LENGTH_SHORT 短时间显示，消失<br/>
     *                 LENGTH_LONG 长时间显示，消失
     * @return
     */
    private static Snackbar newSnackbar(Activity activity, int duration) {
        return Snackbar.make(activity.findViewById(android.R.id.content), "", duration);
    }

    public static Snackbar infoSnackbar(Activity activity, CharSequence messageText, int duration) {
        Snackbar snackbar = newSnackbar(activity, duration);
        setSnackbarText(snackbar, messageText);
        setIcon(activity, snackbar, R.drawable.snackbar_info);
        setSnackbarColor(snackbar, Color.parseColor("#ffffff"), Color.parseColor("#99cc00"));
        return snackbar;
    }

    public static Snackbar warnSnackbar(Activity activity, CharSequence messageText, int duration) {
        Snackbar snackbar = newSnackbar(activity, duration);
        setSnackbarText(snackbar, messageText);
        setIcon(activity, snackbar, R.drawable.snackbar_warn);
        setSnackbarColor(snackbar, Color.parseColor("#000000"), Color.parseColor("#ffbb33"));
        return snackbar;
    }

    public static Snackbar errorSnackbar(Activity activity, CharSequence messageText, int duration) {
        Snackbar snackbar = newSnackbar(activity, duration);
        setSnackbarText(snackbar, messageText);
        setIcon(activity, snackbar, R.drawable.snackbar_error);
        setSnackbarColor(snackbar, Color.parseColor("#ffff00"), Color.parseColor("#ff4444"));
        return snackbar;
    }

    /**
     * 设置Snackbar文字和背景颜色
     *
     * @param snackbar
     * @param messageColor
     * @param backgroundColor
     */
    private static void setSnackbarColor(Snackbar snackbar, int messageColor, int backgroundColor) {
        View view = snackbar.getView();
        if (view != null) {
            view.setBackgroundColor(backgroundColor);
            ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(messageColor);
        }
    }

    /**
     * 设置Snackbar文字内容
     *
     * @param snackbar
     * @param messageText
     */
    private static void setSnackbarText(Snackbar snackbar, CharSequence messageText) {
        View view = snackbar.getView();
        if (view != null) {
            ((TextView) view.findViewById(R.id.snackbar_text)).setText(messageText);
        }
    }

    /**
     * 设置Snackbar图标
     * @param activity
     * @param snackbar
     * @param resId
     */
    private static void setIcon(Activity activity, Snackbar snackbar, int resId){
        View snackbarview = snackbar.getView();
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbarview;
        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(resId);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.gravity = Gravity.CENTER_VERTICAL;
        snackbarLayout.addView(imageView, 0, p);
    }
}
