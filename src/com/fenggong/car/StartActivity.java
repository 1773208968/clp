package com.fenggong.car;

import com.fenggogn.car.R;
import com.fenggong.system.CLPApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * 启动页
 * 
 * @author 夏
 * 
 */
public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startactivity);
		// CLPApplication.getInstance().addActivity(this);//添加Activity到容器中
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				startActivity(new Intent().setClass(StartActivity.this,
						Maincar.class));
				finish();
			}
		}, 1000);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	//	CLPApplication.getInstance().removeActivity(this);
	}
}
