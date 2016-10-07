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
 * �˵���
 * 
 * @author Maincar
 * 
 */
public class Maincar extends Activity {
	// ʹ��xutlis �󶨿ؼ�
	@ViewInject(R.id.radiogroup)
	private RadioGroup rg; // RadioGroup
	@ViewInject(R.id.homepage)
	private RadioButton homepage; // ��ҳ
	@ViewInject(R.id.buycar)
	private RadioButton buycra; // ��
	@ViewInject(R.id.sellingcars)
	private RadioButton sellingcars;// ��
	@ViewInject(R.id.mine)
	private RadioButton mine; // �ҵ�

	/**
	 * ��ҳ��Fragment
	 */
	private HomepageFragment homepageFragment;

	/**
	 * �򳵵�Fragment
	 */
	private Buy_carFragment buy_carFragment;
	/**
	 * ������Fragment
	 */
	private Selling_carsFragment selling_carsFragment;
	/**
	 * �ҵĵ�Fragment
	 */
	private MineFragment mineFragment;

	/**
	 * ���ڶ�Fragment���й���
	 */
	private FragmentManager fragmentManager;

	// ����һ������������ʶ�Ƿ��˳�
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
		x.view().inject(this); // ע��view���¼�

		CLPApplication.getInstance().addActivity(this);// ���Activity��������
		rg.setOnCheckedChangeListener(new SelectPage());// RadioGroup�����¼�
		fragmentManager = getFragmentManager();// ��ȡfragmentManager

		Toast.makeText(getApplicationContext(), "������onCreate", 0).show();
		setTabSelection(0);// Ĭ��ѡ���һ��ҳ��

	}

	/**
	 * onNewIntent ��ȡ fragment �����ֵ
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);//��ȡ���µ�intent
		String selling = intent.getStringExtra("jumpfragment");
		if (selling == null) {
			//setTabSelection(0);// Ĭ��ѡ���һ��ҳ��
			Toast.makeText(getApplicationContext(), "fragment��ֵ����", -1).show();
		} else {
			int i = Integer.parseInt(selling);
			if(i==2){
				rg.check(R.id.sellingcars);//ѡ�� RadioButton ����
			}else if(i==1){
				rg.check(R.id.buycar);//ѡ�� RadioButton ��
			}else{
				return;
			}
		}
	}

	/**
	 * ѡ��ÿ�� tabҳ��İ�ť SelectPage
	 * 
	 */
	private class SelectPage implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			switch (arg1) {
			case R.id.homepage:// ��ҳ
				setTabSelection(0);// ����Ĳ���������ѡ�е�tabҳ
				break;
			case R.id.buycar:// ��
				setTabSelection(1);// ����Ĳ���������ѡ�е�tabҳ
				break;
			case R.id.sellingcars:// ����
				setTabSelection(2);// ����Ĳ���������ѡ�е�tabҳ
				break;
			case R.id.mine:// �ҵ�
				setTabSelection(3);// ����Ĳ���������ѡ�е�tabҳ
				break;

			default:
				break;
			}
		}

	}

	/**
	 * ���ݴ����index����������ѡ�е�tabҳ�� ÿ��tabҳ��Ӧ���±ꡣ 0��ʾ��ҳ 1��ʾ�� 2��ʾ���� 3��ʾ�ҵġ�
	 */

	public void setTabSelection(int i) {
		// ÿ��ѡ��֮ǰ��������ϴε�ѡ��״̬
		clearSelection();

		// ����һ��Fragment����
		FragmentTransaction transaction = fragmentManager.beginTransaction();

		// �����ص����е�Fragment���Է�ֹ�ж��Fragment��ʾ�ڽ����ϵ����
		hideFragments(transaction);

		switch (i) {
		case 0:
			// ���������ҳtabʱ���ı�ؼ���ͼƬ
			Drawable home = getResources().getDrawable(R.drawable.fenlei2);
			home.setBounds(0, 0, home.getMinimumWidth(),
					home.getMinimumHeight()); // ���ñ߽�
			homepage.setCompoundDrawables(null, home, null, null);
			if (homepageFragment == null) {
				// ���homepageFragmentΪ�գ��򴴽�һ������ӵ�������
				homepageFragment = new HomepageFragment();
				transaction.add(R.id.car_main_framelayout, homepageFragment);
			} else {
				// ���homepageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(homepageFragment);

			}
			break;
		case 1:
			// ���������tabʱ���ı�ؼ���ͼƬ��������ɫ
			Drawable buy = getResources().getDrawable(R.drawable.fenlei2);
			buy.setBounds(0, 0, buy.getMinimumWidth(), buy.getMinimumHeight()); // ���ñ߽�
			buycra.setCompoundDrawables(null, buy, null, null);
			if (buy_carFragment == null) {
				// ���homepageFragmentΪ�գ��򴴽�һ������ӵ�������
				buy_carFragment = new Buy_carFragment();
				transaction.add(R.id.car_main_framelayout, buy_carFragment);
			} else {
				// ���homepageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(buy_carFragment);

			}
			break;
		case 2:
			// �����������tabʱ���ı�ؼ���ͼƬ��������ɫ
			Drawable selling = getResources().getDrawable(R.drawable.fenlei2);
			selling.setBounds(0, 0, selling.getMinimumWidth(),
					selling.getMinimumHeight()); // ���ñ߽�
			sellingcars.setCompoundDrawables(null, selling, null, null);
			if (selling_carsFragment == null) {
				// ���homepageFragmentΪ�գ��򴴽�һ������ӵ�������
				selling_carsFragment = new Selling_carsFragment();
				transaction
						.add(R.id.car_main_framelayout, selling_carsFragment);
			} else {
				// ���homepageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(selling_carsFragment);
			}
			break;
		case 3:
			// ��������ҵ�tabʱ���ı�ؼ���ͼƬ��������ɫ
			Drawable mine = getResources().getDrawable(R.drawable.fenlei2);
			mine.setBounds(0, 0, mine.getMinimumWidth(),
					mine.getMinimumHeight()); // ���ñ߽�
			this.mine.setCompoundDrawables(null, mine, null, null);

			if (mineFragment == null) {
				// ���homepageFragmentΪ�գ��򴴽�һ������ӵ�������
				mineFragment = new MineFragment();
				transaction.add(R.id.car_main_framelayout, mineFragment);
			} else {
				// ���homepageFragment��Ϊ�գ���ֱ�ӽ�����ʾ����
				transaction.show(mineFragment);
			}
			break;

		default:
			break;
		}
		transaction.commit();

	}

	/**
	 * ��������е�ѡ��״̬��
	 */

	public void clearSelection() {
		// TODO Auto-generated method stub

		Drawable home = getResources().getDrawable(R.drawable.fenlei);
		home.setBounds(0, 0, home.getMinimumWidth(), home.getMinimumHeight()); // ���ñ߽�
		homepage.setCompoundDrawables(null, home, null, null);

		Drawable buy = getResources().getDrawable(R.drawable.fenlei);
		buy.setBounds(0, 0, buy.getMinimumWidth(), buy.getMinimumHeight()); // ���ñ߽�
		buycra.setCompoundDrawables(null, buy, null, null);

		Drawable selling = getResources().getDrawable(R.drawable.fenlei);
		selling.setBounds(0, 0, selling.getMinimumWidth(),
				selling.getMinimumHeight()); // ���ñ߽�
		sellingcars.setCompoundDrawables(null, selling, null, null);

		Drawable mine = getResources().getDrawable(R.drawable.fenlei);
		mine.setBounds(0, 0, mine.getMinimumWidth(), mine.getMinimumHeight()); // ���ñ߽�
		this.mine.setCompoundDrawables(null, mine, null, null);
	}

	/**
	 * �����е�Fragment����Ϊ����״̬�� ���ڶ�Fragmentִ�в���������
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

	// ��������˳�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}

		// ����MENU��ť����¼����������κβ���
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if (!isExit) {
			isExit = true;
			Toast.makeText(getApplicationContext(), "�ٰ�һ���˳�����",
					Toast.LENGTH_SHORT).show();
			// ����handler�ӳٷ��͸���״̬��Ϣ
			mHandler.sendEmptyMessageDelayed(0, 3000);
		} else {
			CLPApplication.exitApplication();
		}
	}

}
