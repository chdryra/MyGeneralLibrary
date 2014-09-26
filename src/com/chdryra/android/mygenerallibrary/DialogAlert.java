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
	private ActionType mActionRight = ActionType.YES;
	private ActionType mActionLeft = ActionType.NO;
	
	protected abstract String getAlertString();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRightButtonAction(mActionRight);
		setLeftButtonAction(mActionLeft);
		setDismissDialogOnRightClick(true);
		setDismissDialogOnLeftClick(true);		
		setDialogTitle(getAlertString());
		showKeyboardOnLaunch(false);
	}
	
	@Override
	protected View createDialogUI(ViewGroup parent) {
		return null;
	}
	
	protected void setRightAction(ActionType action) {
		mActionRight = action;
	}
	
	protected void setLeftAction(ActionType action) {
		mActionLeft = action;
	}
}
