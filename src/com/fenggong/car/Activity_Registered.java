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
	private Button buyreg;// ��ע��
	@ViewInject(R.id.acregistered_sellingcarsreg)
	private Button sellingreg;// ����ע��
	@ViewInject(R.id.acregistered_phone)
	private EditText phonereg;// �ֻ���
	@ViewInject(R.id.acregistered_verification)
	private Button verification;// ��֤
	@ViewInject(R.id.acregistered_Codes)
	private EditText Codes;// ��֤��
	@ViewInject(R.id.acregistered_next)
	private Button next;// ��һ��
	private Analyzing_Network anet;// �ж���������
	private int i;// ���緵����
	/**
	 * 1��ʾ��ע�� ��2��ʾ����ע��
	 */
	private static int Variety = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registered);

		x.view().inject(this);// ע��view���¼�

		CLPApplication.getInstance().addActivity(this);// ���Activity��������

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
				Variety = 1;// ��ע��
				buyreg.setBackgroundColor(getResources().getColor(
						R.drawable.blue));
				sellingreg.setBackgroundColor(getResources().getColor(
						R.drawable.hui));
				Toast.makeText(getApplicationContext(), Variety + "", 0).show();
				break;
			case R.id.acregistered_sellingcarsreg:
				Variety = 2;// ��ע��
				buyreg.setBackgroundColor(getResources().getColor(
						R.drawable.hui));
				sellingreg.setBackgroundColor(getResources().getColor(
						R.drawable.blue));
				Toast.makeText(getApplicationContext(), Variety + "", 0).show();
				break;

			case R.id.acregistered_verification:// �����֤
				if (phonereg.getText().toString().trim().equals("")) {
					Toast.makeText(getApplicationContext(), "����д�ֻ���", 0).show();
				} else {
					anet = new Analyzing_Network();
					i = anet.GetNetype(getApplicationContext());
					if (i == -1) {// -1��û������ 1��WIFI����2��wap����3��net����
						Toast.makeText(getApplicationContext(), "û�����������������",
								0).show();
					} else {

						Toast.makeText(getApplicationContext(), "�Ѿ�������֤��Ϣ�����",
								0).show();
					}
				}
				break;

			case R.id.acregistered_next:// �����һ��
				if (phonereg.getText().toString().trim().equals("")) {
					Toast.makeText(getApplicationContext(), "����д�ֻ���", 0).show();
				} else if (Codes.getText().toString().trim().equals("")) {
					Toast.makeText(getApplicationContext(), "����д��֤��", 0).show();
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
