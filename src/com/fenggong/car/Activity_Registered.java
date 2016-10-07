package com.fenggong.car;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.fenggogn.car.R;
import com.fenggong.service.Analyzing_Network;
import com.fenggong.system.CLPApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Registered extends Activity {

	@ViewInject(R.id.acregistered_buycarreg)
	private Button buyreg;// 买车注册
	@ViewInject(R.id.acregistered_sellingcarsreg)
	private Button sellingreg;// 卖车注册
	@ViewInject(R.id.acregistered_phone)
	private EditText phonereg;// 手机号
	@ViewInject(R.id.acregistered_verification)
	private Button verification;// 验证
	@ViewInject(R.id.acregistered_Codes)
	private EditText Codes;// 验证码
	@ViewInject(R.id.acregistered_next)
	private Button next;// 下一步
	private Analyzing_Network anet;// 判断网络连接
	private int i;// 网络返回码
	/**
	 * 1表示买车注册 ，2表示卖车注册
	 */
	private static int Variety = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registered);

		x.view().inject(this);// 注入view和事件

		CLPApplication.getInstance().addActivity(this);// 添加Activity到容器中

		buyreg.setOnClickListener(new ClickListener());
		sellingreg.setOnClickListener(new ClickListener());
		next.setOnClickListener(new ClickListener());
		verification.setOnClickListener(new ClickListener());

	}

	private class ClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.acregistered_buycarreg:
				Variety = 1;// 买车注册
				buyreg.setBackgroundColor(getResources().getColor(
						R.drawable.blue));
				sellingreg.setBackgroundColor(getResources().getColor(
						R.drawable.hui));
				Toast.makeText(getApplicationContext(), Variety + "", 0).show();
				break;
			case R.id.acregistered_sellingcarsreg:
				Variety = 2;// 买车注册
				buyreg.setBackgroundColor(getResources().getColor(
						R.drawable.hui));
				sellingreg.setBackgroundColor(getResources().getColor(
						R.drawable.blue));
				Toast.makeText(getApplicationContext(), Variety + "", 0).show();
				break;

			case R.id.acregistered_verification:// 点击验证
				if (phonereg.getText().toString().trim().equals("")) {
					Toast.makeText(getApplicationContext(), "请填写手机号", 0).show();
				} else {
					anet = new Analyzing_Network();
					i = anet.GetNetype(getApplicationContext());
					if (i == -1) {// -1：没有网络 1：WIFI网络2：wap网络3：net网络
						Toast.makeText(getApplicationContext(), "没有网络请打开网络连接",
								0).show();
					} else {

						Toast.makeText(getApplicationContext(), "已经发送验证消息请查收",
								0).show();
					}
				}
				break;

			case R.id.acregistered_next:// 点击下一步
				if (phonereg.getText().toString().trim().equals("")) {
					Toast.makeText(getApplicationContext(), "请填写手机号", 0).show();
				} else if (Codes.getText().toString().trim().equals("")) {
					Toast.makeText(getApplicationContext(), "请填写验证码", 0).show();
				} else {
					startActivity(new Intent(Activity_Registered.this,
							Activity_Registered_detailed.class));
				}
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
