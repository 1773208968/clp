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
	private TextView mlogin;// 登录
	@ViewInject(R.id.miine_registered)
	private TextView mregistered;// 注册
	@ViewInject(R.id.mine_layoutsafety)
	private View viewSafety;// 帐号安全
	@ViewInject(R.id.mine_layoutSales_Management)
	private View viewsalsemanage;// 出售管理
	@ViewInject(R.id.mine_layoutSuccessful)
	private View layoutsuccessful;// 中标的车
	@ViewInject(R.id.mine_layoutforauction)
	private View layoutforauction;// 参卖的车
	@ViewInject(R.id.mine_layoutTransaction_Management)
	private View layoutTransaction_Management;// 交易管理
	@ViewInject(R.id.mine_layoutrelease_car)
	private View layoutrelease_car;// 发布车源
	@ViewInject(R.id.mine_fabucheyuan_tanchu)
	private View layoutfabucheyuan_tanchu;// 发布车源弹出布局

	@ViewInject(R.id.layoutzijifabu)
	private View layoutzijifabu;// 自己发布
	@ViewInject(R.id.mine_layoutxiezhufabu)
	private View layoutxiezhufabu;// 协助发布

	private Maincar maincar = (Maincar) getActivity();
	/**
	 * 发布车源弹出布局 状态
	 */
	private boolean fabutanchu = false;

	// 买车登录布局
	@ViewInject(R.id.mine_buycarlogin1)
	private View layoutbuycarlogin1;
	@ViewInject(R.id.mine_buycarlogin2)
	private View layoutbuycarlogin2;

	// 卖车登录布局
	@ViewInject(R.id.mine_sellingcarslogin)
	private View layoutsellingcarslogin;

	// 未登录和买车布局 共同
	@ViewInject(R.id.mine_Visitorslognin)
	private View layoutvisitorsogin;

	// 未登录
	@ViewInject(R.id.mine_notlongin)
	private View layoutnotlogin;

	// 已经登录
	@ViewInject(R.id.mine_alreadylogin)
	private View layoutalreadylogin;
	/**
	 * -1表示游客 ，1表示买车登录， 2表示卖车登录
	 */
	private static int Identity = 2;

	/**
	 * 开启线程 改变布局的显示和 影藏
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case -1:// 这是游客
				layoutalreadylogin.setVisibility(View.GONE);// 已经登录
				layoutnotlogin.setVisibility(View.VISIBLE);// 显示未登录布局
				layoutvisitorsogin.setVisibility(View.VISIBLE);// 未登录和买车布局 共同
				layoutbuycarlogin2.setVisibility(View.GONE);// 买车布局1隐藏
				layoutbuycarlogin1.setVisibility(View.GONE);// 买车布局2隐藏
				layoutsellingcarslogin.setVisibility(View.GONE);// 卖车布局1隐藏

				break;
			case 1:// 买车登录
				layoutalreadylogin.setVisibility(View.VISIBLE);// 已经登录
				layoutnotlogin.setVisibility(View.GONE);// 显示未登录布局
				layoutvisitorsogin.setVisibility(View.VISIBLE);// 未登录和买车布局 共同
				layoutbuycarlogin2.setVisibility(View.VISIBLE);// 买车布局1隐藏
				layoutbuycarlogin1.setVisibility(View.VISIBLE);// 买车布局2隐藏
				layoutsellingcarslogin.setVisibility(View.GONE);// 卖车布局1隐藏
				break;

			case 2:// 卖车登录
				layoutalreadylogin.setVisibility(View.VISIBLE);// 已经登录
				layoutnotlogin.setVisibility(View.GONE);// 显示未登录布局
				layoutvisitorsogin.setVisibility(View.GONE);// 未登录和买车布局 共同
				layoutbuycarlogin2.setVisibility(View.GONE);// 买车布局1隐藏
				layoutbuycarlogin1.setVisibility(View.GONE);// 买车布局2隐藏
				layoutsellingcarslogin.setVisibility(View.VISIBLE);// 卖车布局1隐藏
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
		x.view().inject(this, view); // 注入view和事件

		inint();// 不同的 身份 加载不同的布局

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
	 * 根据 不同的 身份 加载不同的布局
	 */
	private void inint() {
		// TODO Auto-generated method stub
		if (Identity == -1) {

			// TODO Auto-generated method stub
			Message message = new Message();
			message.what = -1;
			handler.sendMessage(message);
		} else if (Identity == 1) {
			Toast.makeText(getActivity(), Identity + "=买车", 0).show();

			// TODO Auto-generated method stub
			Message message = new Message();
			message.what = 1;
			handler.sendMessage(message);

		} else if (Identity == 2) {
			Toast.makeText(getActivity(), Identity + "=卖车", 0).show();

			// TODO Auto-generated method stub
			Message message = new Message();
			message.what = 2;
			handler.sendMessage(message);

		} else {
			Toast.makeText(getActivity(), Identity + "=错误！", 0).show();
		}
	}

	/**
	 * 设置单击事件
	 * 
	 * @author 夏
	 * 
	 */
	private class ClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.miine_login:
				// 跳转到登录页面
				startActivity(new Intent(getActivity(), Activity_Login.class));
				break;
			case R.id.miine_registered:
				// 跳转到注册页面
				startActivity(new Intent(getActivity(),
						Activity_Registered.class));
				break;
			case R.id.mine_layoutsafety:// 帐号安全
				startActivity(new Intent(getActivity(),
						AccountNumberSafety.class));
				break;
			case R.id.mine_layoutSales_Management:// 出售管理
				startActivity(new Intent(getActivity(), Salesm_Anagement.class));
				break;
			case R.id.mine_layoutSuccessful:// 中标的车
				startActivity(new Intent(getActivity(), Mine_successful.class));
				break;
			case R.id.mine_layoutforauction:// 参拍的车
				startActivity(new Intent(getActivity(), Mine_forauction.class));
				break;
			case R.id.mine_layoutTransaction_Management:// 交易管理
				startActivity(new Intent(getActivity(),
						Mine_transaction_management.class));
				break;
			case R.id.mine_layoutrelease_car:// 发布车源
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
