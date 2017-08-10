/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.mygenerallibrary.Permissions;

import java.util.List;

/**
 * Created by: Rizwan Choudrey
 * On: 03/01/2017
 * Email: rizwan.choudrey@gmail.com
 */

public interface PermissionsManager {
    enum Permission {LOCATION, CAMERA}

    boolean hasPermissions(Permission... permission);

    void requestPermissions(int requestCode, PermissionsCallback callback, Permission... permissions);

    interface PermissionsCallback {
        void onPermissionsResult(int requestCode, List<PermissionResult> results);
    }
}
