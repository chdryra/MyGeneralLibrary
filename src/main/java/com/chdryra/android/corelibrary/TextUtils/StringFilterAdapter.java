/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.TextUtils;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

/**
 * An adapter that can be bound to a widget that requires filtering such as an
 * AutoCompleteTextView. Implements a filter to
 * find suggestions using the provided {@link StringFilterAdapter.StringFilter}. Allows default
 * suggestions to be included via the constructor for when the constraint is null.
 */
public class StringFilterAdapter extends ArrayAdapter<String> implements Filterable {
    private static final int TEXT_VIEW_RESOURCE = android.R.layout.simple_list_item_1;
    private final StringFilter mStringFilter;
    private ArrayList<String> mFiltered = new ArrayList<>();
    private ArrayList<String> mDefaultSuggestions = new ArrayList<String>();

    public interface StringFilter {
        ArrayList<String> filter(String query);
    }

    public StringFilterAdapter(Context context, ArrayList<String> defaultSuggestions,
                               StringFilter stringFilter) {
        super(context, TEXT_VIEW_RESOURCE);
        if (defaultSuggestions != null) {
            mDefaultSuggestions = defaultSuggestions;
            mFiltered = defaultSuggestions;
        }
        mStringFilter = stringFilter;
    }

    public void filter(CharSequence query) {
        getFilter().filter(query);
    }

    //Overridden
    @Override
    public int getCount() {
        return mFiltered != null ? mFiltered.size() : 0;
    }

    @Override
    public String getItem(int index) {
        return mFiltered != null && mFiltered.size() > 0 ? mFiltered.get(index) : null;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();

                mFiltered = mDefaultSuggestions;
                if (constraint != null && constraint.length() > 0) {
                    mFiltered = mStringFilter.filter(constraint.toString());
                }

                filterResults.values = mFiltered;
                filterResults.count = getCount();

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }
}