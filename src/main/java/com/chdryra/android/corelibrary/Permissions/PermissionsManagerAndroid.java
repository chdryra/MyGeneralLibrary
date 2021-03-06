/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Permissions;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: Rizwan Choudrey
 * On: 03/01/2017
 * Email: rizwan.choudrey@gmail.com
 */

public class PermissionsManagerAndroid implements PermissionsManager, ActivityCompat
        .OnRequestPermissionsResultCallback {
    private static final String COARSE_LOCATION = android.Manifest.permission
            .ACCESS_COARSE_LOCATION;
    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String CAMERA = android.Manifest.permission.CAMERA;
    private static final String WRITE = android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static Map<PermissionsManager.Permission, String[]> sMap = new HashMap<>();

    static {
        sMap.put(Permission.LOCATION, new String[]{COARSE_LOCATION, FINE_LOCATION});
        sMap.put(Permission.CAMERA, new String[]{CAMERA, WRITE});
    }

    private final Context mContext;
    private Activity mActivity;
    private Map<Integer, PermissionRequest> mInProgress;

    public PermissionsManagerAndroid(Context context) {
        mContext = context;
        mInProgress = new HashMap<>();
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionRequest request = mInProgress.remove(requestCode);
        if (request == null) return;
        PermissionsManager.Permission[] requested = request.getRequested();
        ArrayList<PermissionResult> results = new ArrayList<>();
        for (Permission permission : requested) {
            boolean result = wasGranted(permission, permissions, grantResults);
            results.add(new PermissionResult(permission, result));
        }
        request.getCallback().onPermissionsResult(requestCode, results);
    }

    @Override
    public void requestPermissions(int requestCode, PermissionsCallback callback, Permission...
            permissions) {
        if (!hasPermissions(permissions)) {
            mInProgress.put(requestCode, new PermissionRequest(permissions, callback));
            ActivityCompat.requestPermissions(mActivity, toStringArray(permissions), requestCode);
        } else {
            ArrayList<PermissionResult> results = new ArrayList<>();
            for (Permission permission : permissions) {
                results.add(new PermissionResult(permission, true));
            }
            callback.onPermissionsResult(requestCode, results);
        }
    }

    @Override
    public boolean hasPermissions(Permission... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissions.length > 0) {
            for (String permission : toStringArray(permissions)) {
                if (ContextCompat.checkSelfPermission(mContext, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean wasGranted(Permission permission,
                               @NonNull String[] permissions,
                               @NonNull int[] grantResults) {
        List<String> returnedList = Arrays.asList(permissions);
        String[] passedList = sMap.get(permission);
        if (passedList == null) return false;

        boolean granted = true;
        for (String requested : passedList) {
            if (returnedList.contains(requested)) {
                granted = grantResults[returnedList.indexOf(requested)] == PackageManager
                        .PERMISSION_GRANTED;
            }
            if (!granted) break;
        }

        return granted;
    }

    @NonNull
    private String[] toStringArray(Permission[] permissions) {
        ArrayList<String> perms = new ArrayList<>();
        for (Permission permission : permissions) {
            perms.addAll(Arrays.asList(sMap.get(permission)));
        }
        return perms.toArray(new String[0]);
    }

    private class PermissionRequest {
        private final PermissionsCallback mCallback;
        private final PermissionsManager.Permission[] mRequested;

        private PermissionRequest(Permission[] requested, PermissionsCallback callback) {
            mCallback = callback;
            mRequested = requested;
        }

        public PermissionsCallback getCallback() {
            return mCallback;
        }

        public PermissionsManager.Permission[] getRequested() {
            return mRequested;
        }
    }
}
