/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Dialogs;

import android.os.Bundle;

/**
 * Created by: Rizwan Choudrey
 * On: 03/02/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface AlertListener {
    void onAlertNegative(int requestCode, Bundle args);

    void onAlertPositive(int requestCode, Bundle args);
}
