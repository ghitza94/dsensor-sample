package com.example.hoan.dsensorsamples.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Created by Hoan on 3/3/2016.
 */
public class DeviceInfo {
    private DeviceInfo() {

    }

    public static DisplayMetrics getAppScreenInfo(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    @TargetApi(17)
    public static DisplayMetrics getAppActualScreenInfo(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRealMetrics(metrics);
        return metrics;
    }

    @TargetApi(16)
    public static Point getSmallestSizePixel(Context context) {
        Point outSmallestSize = new Point();
        Point outLargestSize = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).
                getDefaultDisplay().getCurrentSizeRange(outSmallestSize, outLargestSize);
        return outSmallestSize;
    }

    @TargetApi(16)
    public static Point getLargestSizePixel(Context context) {
        Point outSmallestSize = new Point();
        Point outLargestSize = new Point();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).
                getDefaultDisplay().getCurrentSizeRange(outSmallestSize, outLargestSize);
        return outLargestSize;
    }

    @TargetApi(13)
    public static Point getAvailableSizePixel(Context context) {
        Point point = getAvailableSizeDp(context);
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        point.x = dpToPixel(point.x, metrics.density);
        point.y = dpToPixel(point.y, metrics.density);
        return point;
    }

    @TargetApi(13)
    public static Point getAvailableSizeDp(Context context) {
        Configuration configuration = context.getResources().getConfiguration();

        return new Point(configuration.screenWidthDp, configuration.screenHeightDp);
    }

    public static int dpToPixel(int dp, float density) {
        return (int) Math.ceil(dp * density);
    }

    public static int getOrientation(Context context) {
        int orientation = context.getResources().getConfiguration().orientation;
        int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getRotation();
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            switch (rotation) {
                case Surface.ROTATION_0:

                case Surface.ROTATION_270:
                    return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

                case Surface.ROTATION_90:

                case Surface.ROTATION_180:
                    return ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
            }
        } else {
            switch (rotation) {
                case Surface.ROTATION_0:

                case Surface.ROTATION_90:
                    return ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;

                case Surface.ROTATION_270:

                case Surface.ROTATION_180:
                    return ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
            }
        }
        return orientation;
    }

}
