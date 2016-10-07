package com.fenggong.fragment;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.fenggogn.car.R;
import com.fenggong.car.AccountNumberSafety;
import com.fenggong.car.Activity_Login;
import com.fenggong.car.Activity_Registered;
import com.fenggong.car.Maincar;
import com.fenggong.car.Mine_forauction;
import com.fenggong.car.Mine_successful;
import com.fenggong.car.Mine_transaction_management;
import com.fenggong.car.Mine_xiezhufabu;
import com.fenggong.car.Mine_zijifabu;
import com.fenggong.car.Salesm_Anagement;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MineFragment extends Fragment {
	@ViewInject(R.id.miine_login)
	private TextView mlogin;// ��¼
	@ViewInject(R.id.miine_registered)
	private TextView mregistered;// ע��
	@ViewInject(R.id.mine_layoutsafety)
	private View viewSafety;// �ʺŰ�ȫ
	@ViewInject(R.id.mine_layoutSales_Management)
	private View viewsalsemanage;// ���۹���
	@ViewInject(R.id.mine_layoutSuccessful)
	private View layoutsuccessful;// �б�ĳ�
	@ViewInject(R.id.mine_layoutforauction)
	private View layoutforauction;// �����ĳ�
	@ViewInject(R.id.mine_layoutTransaction_Management)
	private View layoutTransaction_Management;// ���׹���
	@ViewInject(R.id.mine_layoutrelease_car)
	private View layoutrelease_car;// ������Դ
	@ViewInject(R.id.mine_fabucheyuan_tanchu)
	private View layoutfabucheyuan_tanchu;// ������Դ��������

	@ViewInject(R.id.layoutzijifabu)
	private View layoutzijifabu;// �Լ�����
	@ViewInject(R.id.mine_layoutxiezhufabu)
	private View layoutxiezhufabu;// Э������

	private Maincar maincar = (Maincar) getActivity();
	/**
	 * ������Դ�������� ״̬
	 */
	private boolean fabutanchu = false;

	// �򳵵�¼����
	@ViewInject(R.id.mine_buycarlogin1)
	private View layoutbuycarlogin1;
	@ViewInject(R.id.mine_buycarlogin2)
	private View layoutbuycarlogin2;

	// ������¼����
	@ViewInject(R.id.mine_sellingcarslogin)
	private View layoutsellingcarslogin;

	// δ��¼���򳵲��� ��ͬ
	@ViewInject(R.id.mine_Visitorslognin)
	private View layoutvisitorsogin;

	// δ��¼
	@ViewInject(R.id.mine_notlongin)
	private View layoutnotlogin;

	// �Ѿ���¼
	@ViewInject(R.id.mine_alreadylogin)
	private View layoutalreadylogin;
	/**
	 * -1��ʾ�ο� ��1��ʾ�򳵵�¼�� 2��ʾ������¼
	 */
	private static int Identity = 2;

	/**
	 * �����߳� �ı䲼�ֵ���ʾ�� Ӱ��
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case -1:// �����ο�
				layoutalreadylogin.setVisibility(View.GONE);// �Ѿ���¼
				layoutnotlogin.setVisibility(View.VISIBLE);// ��ʾδ��¼����
				layoutvisitorsogin.setVisibility(View.VISIBLE);// δ��¼���򳵲��� ��ͬ
				layoutbuycarlogin2.setVisibility(View.GONE);// �򳵲���1����
				layoutbuycarlogin1.setVisibility(View.GONE);// �򳵲���2����
				layoutsellingcarslogin.setVisibility(View.GONE);// ��������1����

				break;
			case 1:// �򳵵�¼
				layoutalreadylogin.setVisibility(View.VISIBLE);// �Ѿ���¼
				layoutnotlogin.setVisibility(View.GONE);// ��ʾδ��¼����
				layoutvisitorsogin.setVisibility(View.VISIBLE);// δ��¼���򳵲��� ��ͬ
				layoutbuycarlogin2.setVisibility(View.VISIBLE);// �򳵲���1����
				layoutbuycarlogin1.setVisibility(View.VISIBLE);// �򳵲���2����
				layoutsellingcarslogin.setVisibility(View.GONE);// ��������1����
				break;

			case 2:// ������¼
				layoutalreadylogin.setVisibility(View.VISIBLE);// �Ѿ���¼
				layoutnotlogin.setVisibility(View.GONE);// ��ʾδ��¼����
				layoutvisitorsogin.setVisibility(View.GONE);// δ��¼���򳵲��� ��ͬ
				layoutbuycarlogin2.setVisibility(View.GONE);// �򳵲���1����
				layoutbuycarlogin1.setVisibility(View.GONE);// �򳵲���2����
				layoutsellingcarslogin.setVisibility(View.VISIBLE);// ��������1����
				break;
			case 11:
				layoutfabucheyuan_tanchu.setVisibility(View.VISIBLE);
				break;
			case 12:
				layoutfabucheyuan_tanchu.setVisibility(View.GONE);
				break;
			default:
				break;
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.mine, container, false);
		x.view().inject(this, view); // ע��view���¼�

		inint();// ��ͬ�� ��� ���ز�ͬ�Ĳ���

		mlogin.setOnClickListener(new ClickListener());
		mregistered.setOnClickListener(new ClickListener());
		viewSafety.setOnClickListener(new ClickListener());
		viewsalsemanage.setOnClickListener(new ClickListener());
		layoutsuccessful.setOnClickListener(new ClickListener());
		layoutforauction.setOnClickListener(new ClickListener());
		layoutTransaction_Management.setOnClickListener(new ClickListener());
		layoutrelease_car.setOnClickListener(new ClickListener());
		layoutzijifabu.setOnClickListener(new ClickListener());
		layoutxiezhufabu.setOnClickListener(new ClickListener());
		return view;
	}

	/**
	 * ���� ��ͬ�� ��� ���ز�ͬ�Ĳ���
	 */
	private void inint() {
		// TODO Auto-generated method stub
		if (Identity == -1) {

			// TODO Auto-generated method stub
			Message message = new Message();
			message.what = -1;
			handler.sendMessage(message);
		} else if (Identity == 1) {
			Toast.makeText(getActivity(), Identity + "=��", 0).show();

			// TODO Auto-generated method stub
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);

		} else if (Identity == 2) {
			Toast.makeText(getActivity(), Identity + "=����", 0).show();

			// TODO Auto-generated method stub
			Message message = new Message();
			message.what = 2;
			handler.sendMessage(message);

		} else {
			Toast.makeText(getActivity(), Identity + "=����", 0).show();
		}
	}

	/**
	 * ���õ����¼�
	 * 
	 * @author ��
	 * 
	 */
	private class ClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.miine_login:
				// ��ת����¼ҳ��
				startActivity(new Intent(getActivity(), Activity_Login.class));
				break;
			case R.id.miine_registered:
				// ��ת��ע��ҳ��
				startActivity(new Intent(getActivity(),
						Activity_Registered.class));
				break;
			case R.id.mine_layoutsafety:// �ʺŰ�ȫ
				startActivity(new Intent(getActivity(),
						AccountNumberSafety.class));
				break;
			case R.id.mine_layoutSales_Management:// ���۹���
				startActivity(new Intent(getActivity(), Salesm_Anagement.class));
				break;
			case R.id.mine_layoutSuccessful:// �б�ĳ�
				startActivity(new Intent(getActivity(), Mine_successful.class));
				break;
			case R.id.mine_layoutforauction:// ���ĵĳ�
				startActivity(new Intent(getActivity(), Mine_forauction.class));
				break;
			case R.id.mine_layoutTransaction_Management:// ���׹���
				startActivity(new Intent(getActivity(),
						Mine_transaction_management.class));
				break;
			case R.id.mine_layoutrelease_car:// ������Դ
				if (!fabutanchu) {
					fabutanchu = true;

					Message message = new Message();
					message.what = 11;
					handler.sendMessage(message);
				} else {
					fabutanchu = false;
					Message message = new Message();
					message.what = 12;
					handler.sendMessage(message);

				}
				break;
			case R.id.layoutzijifabu:

				Intent in=new Intent(getActivity(),Maincar.class);
				in.putExtra("jumpfragment", "2");	
				startActivity(in);
				
				break;
			case R.id.mine_layoutxiezhufabu:
				startActivity(new Intent(getActivity(), Mine_xiezhufabu.class));
				break;
			default:
				break;
			}
		}

	}

}
