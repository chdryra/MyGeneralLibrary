/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.TagsModel.Implementation;

import com.chdryra.android.corelibrary.TagsModel.Interfaces.ItemTag;
import com.chdryra.android.corelibrary.TagsModel.Interfaces.ItemTagCollection;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by: Rizwan Choudrey
 * On: 10/11/2015
 * Email: rizwan.choudrey@gmail.com
 */
public class ItemTagList extends AbstractCollection<ItemTag>
        implements ItemTagCollection {
    private final ArrayList<ItemTag> mTags;

    public ItemTagList() {
        mTags = new ArrayList<>();
    }

    ItemTagList(ItemTagList tags) {
        mTags = new ArrayList<>(tags.mTags);
    }

    public ItemTag getItemTag(String tag) {
        ItemTag rTag = null;
        for (ItemTag itemTag : mTags) {
            if (itemTag.isTag(tag)) {
                rTag = itemTag;
                break;
            }
        }

        return rTag;
    }

    public void remove(ItemTag tag) {
        mTags.remove(tag);
    }

    @Override
    public int size() {
        return mTags.size();
    }

    @Override
    public ItemTag getItemTag(int position) {
        return mTags.get(position);
    }

    @Override
    public ArrayList<String> toStringArray() {
        ArrayList<String> tagArray = new ArrayList<>();
        for (ItemTag tag : mTags) {
            tagArray.add(tag.getTag());
        }
        return tagArray;
    }

    @Override
    public boolean add(ItemTag tag) {
        if (!mTags.contains(tag)) {
            mTags.add(tag);
            return true;
        }

        return false;
    }

    @Override
    public Iterator<ItemTag> iterator() {
        return new ReviewTagIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemTagList)) return false;

        ItemTagList that = (ItemTagList) o;

        return mTags.equals(that.mTags);

    }

    @Override
    public int hashCode() {
        return mTags.hashCode();
    }

    private class ReviewTagIterator implements Iterator<ItemTag> {
        int position = 0;
        boolean nextCalled = false;

        @Override
        public boolean hasNext() {
            return position < size() && getItemTag(position) != null;
        }

        @Override
        public ItemTag next() {
            if (hasNext()) {
                nextCalled = true;
                return getItemTag(position++);
            } else {
                throw new NoSuchElementException("No more elements left");
            }
        }

        @Override
        public void remove() {
            if (!nextCalled) {
                throw new IllegalStateException("Have to call next() before remove()");
            } else if (position > 0) {
                ItemTagList.this.remove(getItemTag(--position));
                nextCalled = false;
            }
        }
    }
}
