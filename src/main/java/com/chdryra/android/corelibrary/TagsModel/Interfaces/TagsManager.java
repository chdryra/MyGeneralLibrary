/*
 * Copyright (c) Rizwan Choudrey 2016 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.TagsModel.Interfaces;

//import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

/**
 * The singleton that manages the tagging and untagging of Reviews.
 */
public interface TagsManager {
    void tagItem(String id, String tag);

    boolean tagsItem(String id, String tag);

    void tagItem(String id, ArrayList<String> tags);

    void clearTags(String id);

    ItemTagCollection getTags(String id);
}
