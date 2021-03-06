/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.Aggregation;


import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by: Rizwan Choudrey
 * On: 08/07/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class ItemCounter<T, D> {
    private final ItemGetter<T, D> mGetter;
    private final Map<D, Integer> mCountMap;
    private D mModeItem;

    public ItemCounter(ItemGetter<T, D> getter) {
        mCountMap = new LinkedHashMap<>();
        mGetter = getter;
    }

    public D getModeItem() {
        return mModeItem;
    }

    public int getNonModeCount() {
        return Math.max(0, getCountOfItemTypes() - 1);
    }

    public int getCountOfItemTypes() {
        return mCountMap.size();
    }

    public void performCount(Iterable<? extends T> data) {
        int maxCountSoFar = initialiseCounting(data);
        for (T datum : data) {
            D item = mGetter.getItem(datum);
            if (item == null) continue;
            Integer numberOfItems = incrementCount(item);
            maxCountSoFar = setNewMaxIfNeccessary(maxCountSoFar, numberOfItems, item);
        }
    }

    private int initialiseCounting(Iterable<? extends T> data) {
        mCountMap.clear();
        Iterator<? extends T> iterator = data.iterator();
        if (iterator.hasNext()) {
            mModeItem = mGetter.getItem(iterator.next());
            return 1;
        }
        return 0;
    }

    private int setNewMaxIfNeccessary(int maxCount, Integer numberOfItems, D item) {
        if (numberOfItems > maxCount) {
            maxCount = numberOfItems;
            mModeItem = item;
        }
        return maxCount;
    }

    @NonNull
    private Integer incrementCount(D item) {
        Integer num = mCountMap.get(item);
        Integer numberOfItems = num == null ? 1 : num + 1;
        mCountMap.put(item, numberOfItems);
        return numberOfItems;
    }
}
