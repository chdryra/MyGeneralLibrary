/*
 * Copyright (c) Rizwan Choudrey 2017 - All Rights Reserved
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential
 * rizwan.choudrey@gmail.com
 *
 */

package com.chdryra.android.corelibrary.LocationServices;

import java.util.ArrayList;

/**
 * Created by: Rizwan Choudrey
 * On: 11/01/2016
 * Email: rizwan.choudrey@gmail.com
 */
public interface NearestPlacesSuggester {
    interface NearestPlacesListener {
        void onNearestPlacesFound(ArrayList<LocatedPlace> suggestions);

        void onNotPermissioned();
    }

    void fetchSuggestions(LocatedPlace place, NearestPlacesListener listener);
}
