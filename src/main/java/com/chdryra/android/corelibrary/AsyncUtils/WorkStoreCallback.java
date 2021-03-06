/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.AsyncUtils;

import android.support.annotation.Nullable;

/**
 * Created by: Rizwan Choudrey
 * On: 04/04/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface WorkStoreCallback<T> {
    void onAddedToStore(T item, String storeId, CallbackMessage result);

    void onRetrievedFromStore(T item, String requestedId, CallbackMessage result);

    void onRemovedFromStore(String itemId, CallbackMessage result);

    void onFailed(@Nullable T item, @Nullable String itemId, CallbackMessage result);
}
