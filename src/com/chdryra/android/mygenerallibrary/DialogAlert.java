/*
 * Copyright (c) 2014, Rizwan Choudrey - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Author: Rizwan Choudrey
 * Date: 23 September, 2014
 */

package com.chdryra.android.mygenerallibrary;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public abstract class DialogAlert extends DialogTwoButtonFragment {
	private final ActionType mActionRight = ActionType.YES;
	private final ActionType mActionLeft = ActionType.NO;
	
	protected abstract String getAlertString();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRightButtonAction(mActionRight);
		setLeftButtonAction(mActionLeft);
        setDialogTitle(getAlertString());
        dismissDialogOnRightClick();
		dismissDialogOnLeftClick();
		hideKeyboardOnLaunch();
	}
	
	@Override
	protected View createDialogUI(ViewGroup parent) {
		return null;
	}
}
