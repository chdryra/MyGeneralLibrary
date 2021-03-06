/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Aggregation;

import android.support.annotation.NonNull;

/**
 * Created by: Rizwan Choudrey
 * On: 05/01/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface ComparatorString extends DifferenceComparator<String, DifferencePercentage> {
    @Override
    DifferencePercentage compare(@NonNull String lhs, @NonNull String rhs);
}
