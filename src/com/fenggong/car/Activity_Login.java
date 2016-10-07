package com.fenggong.car;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.fenggogn.car.R;
import com.fenggong.system.CLPApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Login extends Activity {
	@ViewInject(R.id.aclogin_registered)
	private Button mlogin_registered;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		x.view().inject(this);// 注入view和事件

		CLPApplication.getInstance().addActivity(this);// 添加Activity到容器中

		mlogin_registered.setOnClickListener(new ClickListener());

	}

	private class ClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.aclogin_registered:
				// 打开注册
				startActivity(new Intent(Activity_Login.this,
						Activity_Registered.class));

				break;

			default:
				break;
			}
		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		CLPApplication.getInstance().removeActivity(this);
	}

}
