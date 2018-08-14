package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.Surface;

public class MyOrientationDetector extends OrientationEventListener {

    private static final String TAG = "MyOrientationDetector";

    private int lastOrientation = -1;

    private Activity mActivity;

    MyOrientationDetector(Activity activity) {
        super(activity);
        mActivity = activity;
    }

    void setLastOrientation(int rotation) {
        switch (rotation) {
            case Surface.ROTATION_90:
                lastOrientation = 270;
                break;
            case Surface.ROTATION_270:
                lastOrientation = 90;
                break;
            default:
                lastOrientation = -1;
        }
    }

    @Override
    public void onOrientationChanged(int orientation) {
        Log.d(TAG, "orientation:" + orientation);
        if (orientation > 45 && orientation < 135) {
            orientation = 90;
        } else if (orientation > 225 && orientation < 315) {
            orientation = 270;
        } else {
            orientation = -1;
        }
        if ((orientation == 90  && lastOrientation == 270) || (orientation == 270  && lastOrientation == 90)) {
            Log.i(TAG, "orientation:" + orientation + "lastOrientation:" + lastOrientation);
            Intent intent = mActivity.getIntent();
            mActivity.finish();
            mActivity.startActivity(intent);
            lastOrientation = orientation;
            Log.i(TAG, "SUCCESS");
        }
    }
}
