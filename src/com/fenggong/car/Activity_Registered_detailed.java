package com.fenggong.car;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.fenggogn.car.R;
import com.fenggong.system.CLPApplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Registered_detailed extends Activity {
	@ViewInject(R.id.acregdetailed_compreg)
	private Button compreg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registered_detailed);
		x.view().inject(this);// 注入view和事件
		CLPApplication.getInstance().addActivity(this);// 添加Activity到容器中

		compreg.setOnClickListener(new ClickListener());// 单击事件
	}

	private class ClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "aaa", 0).show();
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		CLPApplication.getInstance().removeActivity(this);
	}
}
