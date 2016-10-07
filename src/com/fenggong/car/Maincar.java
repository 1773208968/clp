package com.fenggong.car;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.fenggogn.car.R;
import com.fenggong.fragment.Buy_carFragment;
import com.fenggong.fragment.HomepageFragment;
import com.fenggong.fragment.MineFragment;
import com.fenggong.fragment.Selling_carsFragment;
import com.fenggong.system.CLPApplication;

import android.R.integer;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

/**
 * 菜单栏
 * 
 * @author Maincar
 * 
 */
public class Maincar extends Activity {
	// 使用xutlis 绑定控件
	@ViewInject(R.id.radiogroup)
	private RadioGroup rg; // RadioGroup
	@ViewInject(R.id.homepage)
	private RadioButton homepage; // 首页
	@ViewInject(R.id.buycar)
	private RadioButton buycra; // 买车
	@ViewInject(R.id.sellingcars)
	private RadioButton sellingcars;// 买车
	@ViewInject(R.id.mine)
	private RadioButton mine; // 我的

	/**
	 * 主页的Fragment
	 */
	private HomepageFragment homepageFragment;

	/**
	 * 买车的Fragment
	 */
	private Buy_carFragment buy_carFragment;
	/**
	 * 卖车的Fragment
	 */
	private Selling_carsFragment selling_carsFragment;
	/**
	 * 我的的Fragment
	 */
	private MineFragment mineFragment;

	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;

	// 定义一个变量，来标识是否退出
	private static boolean isExit = false;
	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			isExit = false;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_main);
		x.view().inject(this); // 注入view和事件

		CLPApplication.getInstance().addActivity(this);// 添加Activity到容器中
		rg.setOnCheckedChangeListener(new SelectPage());// RadioGroup单击事件
		fragmentManager = getFragmentManager();// 获取fragmentManager

		Toast.makeText(getApplicationContext(), "进入了onCreate", 0).show();
		setTabSelection(0);// 默认选择第一个页面

	}

	/**
	 * onNewIntent 获取 fragment 传入的值
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);//获取最新的intent
		String selling = intent.getStringExtra("jumpfragment");
		if (selling == null) {
			//setTabSelection(0);// 默认选择第一个页面
			Toast.makeText(getApplicationContext(), "fragment传值错误", -1).show();
		} else {
			int i = Integer.parseInt(selling);
			if(i==2){
				rg.check(R.id.sellingcars);//选中 RadioButton 卖车
			}else if(i==1){
				rg.check(R.id.buycar);//选中 RadioButton 买车
			}else{
				return;
			}
		}
	}

	/**
	 * 选择每个 tab页面的按钮 SelectPage
	 * 
	 */
	private class SelectPage implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			switch (arg1) {
			case R.id.homepage:// 主页
				setTabSelection(0);// 传入的参数来设置选中的tab页
				break;
			case R.id.buycar:// 买车
				setTabSelection(1);// 传入的参数来设置选中的tab页
				break;
			case R.id.sellingcars:// 卖车
				setTabSelection(2);// 传入的参数来设置选中的tab页
				break;
			case R.id.mine:// 我的
				setTabSelection(3);// 传入的参数来设置选中的tab页
				break;

			default:
				break;
			}
		}

	}

	/**
	 * 根据传入的index参数来设置选中的tab页。 每个tab页对应的下标。 0表示首页 1表示买车 2表示卖车 3表示我的。
	 */

	public void setTabSelection(int i) {
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();

		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);

		switch (i) {
		case 0:
			// 当点击了首页tab时，改变控件的图片
			Drawable home = getResources().getDrawable(R.drawable.fenlei2);
			home.setBounds(0, 0, home.getMinimumWidth(),
					home.getMinimumHeight()); // 设置边界
			homepage.setCompoundDrawables(null, home, null, null);
			if (homepageFragment == null) {
				// 如果homepageFragment为空，则创建一个并添加到界面上
				homepageFragment = new HomepageFragment();
				transaction.add(R.id.car_main_framelayout, homepageFragment);
			} else {
				// 如果homepageFragment不为空，则直接将它显示出来
				transaction.show(homepageFragment);

			}
			break;
		case 1:
			// 当点击了买车tab时，改变控件的图片和文字颜色
			Drawable buy = getResources().getDrawable(R.drawable.fenlei2);
			buy.setBounds(0, 0, buy.getMinimumWidth(), buy.getMinimumHeight()); // 设置边界
			buycra.setCompoundDrawables(null, buy, null, null);
			if (buy_carFragment == null) {
				// 如果homepageFragment为空，则创建一个并添加到界面上
				buy_carFragment = new Buy_carFragment();
				transaction.add(R.id.car_main_framelayout, buy_carFragment);
			} else {
				// 如果homepageFragment不为空，则直接将它显示出来
				transaction.show(buy_carFragment);

			}
			break;
		case 2:
			// 当点击了卖车tab时，改变控件的图片和文字颜色
			Drawable selling = getResources().getDrawable(R.drawable.fenlei2);
			selling.setBounds(0, 0, selling.getMinimumWidth(),
					selling.getMinimumHeight()); // 设置边界
			sellingcars.setCompoundDrawables(null, selling, null, null);
			if (selling_carsFragment == null) {
				// 如果homepageFragment为空，则创建一个并添加到界面上
				selling_carsFragment = new Selling_carsFragment();
				transaction
						.add(R.id.car_main_framelayout, selling_carsFragment);
			} else {
				// 如果homepageFragment不为空，则直接将它显示出来
				transaction.show(selling_carsFragment);
			}
			break;
		case 3:
			// 当点击了我的tab时，改变控件的图片和文字颜色
			Drawable mine = getResources().getDrawable(R.drawable.fenlei2);
			mine.setBounds(0, 0, mine.getMinimumWidth(),
					mine.getMinimumHeight()); // 设置边界
			this.mine.setCompoundDrawables(null, mine, null, null);

			if (mineFragment == null) {
				// 如果homepageFragment为空，则创建一个并添加到界面上
				mineFragment = new MineFragment();
				transaction.add(R.id.car_main_framelayout, mineFragment);
			} else {
				// 如果homepageFragment不为空，则直接将它显示出来
				transaction.show(mineFragment);
			}
			break;

		default:
			break;
		}
		transaction.commit();

	}

	/**
	 * 清除掉所有的选中状态。
	 */

	public void clearSelection() {
		// TODO Auto-generated method stub

		Drawable home = getResources().getDrawable(R.drawable.fenlei);
		home.setBounds(0, 0, home.getMinimumWidth(), home.getMinimumHeight()); // 设置边界
		homepage.setCompoundDrawables(null, home, null, null);

		Drawable buy = getResources().getDrawable(R.drawable.fenlei);
		buy.setBounds(0, 0, buy.getMinimumWidth(), buy.getMinimumHeight()); // 设置边界
		buycra.setCompoundDrawables(null, buy, null, null);

		Drawable selling = getResources().getDrawable(R.drawable.fenlei);
		selling.setBounds(0, 0, selling.getMinimumWidth(),
				selling.getMinimumHeight()); // 设置边界
		sellingcars.setCompoundDrawables(null, selling, null, null);

		Drawable mine = getResources().getDrawable(R.drawable.fenlei);
		mine.setBounds(0, 0, mine.getMinimumWidth(), mine.getMinimumHeight()); // 设置边界
		this.mine.setCompoundDrawables(null, mine, null, null);
	}

	/**
	 * 将所有的Fragment都置为隐藏状态。 用于对Fragment执行操作的事务
	 */

	public void hideFragments(FragmentTransaction transaction) {
		// TODO Auto-generated method stub
		if (homepageFragment != null) {
			transaction.hide(homepageFragment);
		}
		if (buy_carFragment != null) {
			transaction.hide(buy_carFragment);
		}
		if (selling_carsFragment != null) {
			transaction.hide(selling_carsFragment);
		}
		if (mineFragment != null) {
			transaction.hide(mineFragment);
		}

	}

	// 点击两次退出
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}

		// 拦截MENU按钮点击事件，让他无任何操作
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "再按一次退出程序",
					Toast.LENGTH_SHORT).show();
			// 利用handler延迟发送更改状态信息
			mHandler.sendEmptyMessageDelayed(0, 3000);
		} else {
			CLPApplication.exitApplication();
		}
	}

}
