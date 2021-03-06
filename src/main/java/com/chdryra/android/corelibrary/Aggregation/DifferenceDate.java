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
public class DifferenceDate implements DifferenceLevel<DifferenceDate> {
    private final DateBucket mDateLevel;

    public enum DateBucket {DAY, MONTH, YEAR, MORE_THAN_YEAR}

    public DifferenceDate(DateBucket dateLevel) {
        mDateLevel = dateLevel;
    }

    @Override
    public boolean lessThanOrEqualTo(DifferenceDate differenceThreshold) {
        return mDateLevel.ordinal() <= differenceThreshold.mDateLevel.ordinal();
    }
}
