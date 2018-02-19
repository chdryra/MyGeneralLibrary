/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Implementation;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 01/08/2016
 * Email: rizwan.choudrey@gmail.com
 */
public abstract class DereferencableBasic<T> extends SubscribableReferenceBasic<T> {
    private final Collection<ValueSubscriber<T>> mValueBinders;

    public DereferencableBasic() {
        super();
        mValueBinders = new ArrayList<>();
    }

    @Override
    protected void removeSubscriber(ValueSubscriber<T> subscriber) {
        mValueBinders.remove(subscriber);
    }

    @Override
    protected void bind(ValueSubscriber<T> subscriber) {
        mValueBinders.add(subscriber);
        fireForBinder(subscriber);
    }

    @Override
    protected Collection<ValueSubscriber<T>> getSubscribers() {
        return mValueBinders;
    }

    @Override
    protected void onInvalidate() {
        super.onInvalidate();
        mValueBinders.clear();
    }

    @Override
    protected boolean contains(ValueSubscriber<T> subscriber) {
        return mValueBinders.contains(subscriber);
    }

    protected void fireForBinder(final ValueSubscriber<T> binder) {
        dereference(new DereferenceCallback<T>() {
            @Override
            public void onDereferenced(DataValue<T> value) {
                if (value.hasValue()) binder.onReferenceValue(value.getData());
            }
        });
    }
}
