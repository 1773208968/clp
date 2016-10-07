package com.fenggong.car;

import org.xutils.x;

import com.fenggogn.car.R;
import com.fenggong.system.CLPApplication;

import android.app.Activity;
import android.os.Bundle;

public class AccountNumberSafety extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_number_safety);
		x.view().inject(this);
		CLPApplication.getInstance().addActivity(this);
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		CLPApplication.getInstance().removeActivity(this);
	}

}
