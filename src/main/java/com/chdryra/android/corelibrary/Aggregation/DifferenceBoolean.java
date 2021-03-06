/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Aggregation;

/**
 * Created by: Rizwan Choudrey
 * On: 03/07/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class DifferenceBoolean implements DifferenceLevel<DifferenceBoolean> {
    private final boolean mValue;

    public DifferenceBoolean(boolean value) {
        mValue = value;
    }

    @Override
    public boolean lessThanOrEqualTo(DifferenceBoolean differenceThreshold) {
        return mValue == differenceThreshold.mValue;
    }
}
