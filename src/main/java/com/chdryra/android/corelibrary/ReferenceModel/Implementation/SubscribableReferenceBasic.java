/*
 * Copyright (c) Rizwan Choudrey 2018 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.ReferenceModel.Implementation;

import android.support.annotation.Nullable;

import java.util.Collection;

/**
 * Created by: Rizwan Choudrey
 * On: 01/08/2016
 * Email: rizwan.choudrey@gmail.com
 */
public abstract class SubscribableReferenceBasic<T> extends InvalidatableReferenceBasic<T> {

    protected abstract void removeSubscriber(ValueSubscriber<T> subscriber);

    protected abstract void doDereferencing(DereferenceCallback<T> callback);

    protected abstract void bind(ValueSubscriber<T> subscriber);

    public void notifySubscribers() {
        if (getSubscribers().size() > 0) {
            dereference(new DereferenceCallback<T>() {
                @Override
                public void onDereferenced(DataValue<T> value) {
                    if (value.hasValue()) {
                        T data = value.getData();
                        for (ValueSubscriber<T> subscriber : getSubscribers()) {
                            subscriber.onReferenceValue(data);
                        }
                    }
                }
            });
        }
    }

    protected abstract Collection<ValueSubscriber<T>> getSubscribers();

    @Nullable
    protected T getNullValue() {
        return null;
    }

    protected boolean contains(ValueSubscriber<T> subscriber) {
        return getSubscribers().contains(subscriber);
    }

    protected void invalidReference(DereferenceCallback<T> callback) {
        callback.onDereferenced(new DataValue<>(getNullValue()));
    }

    @Override
    public void dereference(final DereferenceCallback<T> callback) {
        if (isValidReference()) {
            doDereferencing(callback);
        } else {
            invalidReference(callback);
        }
    }

    @Override
    public void subscribe(final ValueSubscriber<T> subscriber) {
        if (!isValidReference()) {
            subscriber.onInvalidated(this);
            return;
        }
        //Have to do this to force it to refire.
        if (contains(subscriber)) unsubscribe(subscriber);

        bind(subscriber);
    }

    @Override
    public void unsubscribe(ValueSubscriber<T> subscriber) {
        if (contains(subscriber)) removeSubscriber(subscriber);
    }

    @Override
    protected void onInvalidate() {
        super.onInvalidate();
        for (ValueSubscriber<T> binder : getSubscribers()) {
            binder.onInvalidated(this);
        }
    }

    void notifySubscriber(final ValueSubscriber<T> subscriber) {
        dereference(new DereferenceCallback<T>() {
            @Override
            public void onDereferenced(DataValue<T> value) {
                if (value.hasValue()) subscriber.onReferenceValue(value.getData());
            }
        });
    }
}
