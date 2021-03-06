/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.AsyncUtils;

/**
 * Created by: Rizwan Choudrey
 * On: 05/04/2016
 * Email: rizwan.choudrey@gmail.com
 */
public class WorkerToken {
    private String mItemId;
    private Object mWorker;

    public WorkerToken(String itemId, Object worker) {
        mItemId = itemId;
        mWorker = worker;
    }

    public String getItemId() {
        return mItemId;
    }
}
