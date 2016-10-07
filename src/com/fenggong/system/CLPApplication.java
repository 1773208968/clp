package com.fenggong.system;

import java.util.LinkedList;
import java.util.List;

import org.xutils.x;
import android.app.Activity;
import android.app.Application;
import android.widget.Toast;

public class CLPApplication extends Application {

	private static CLPApplication mCLPApplication;
	private static List<Activity> activityList = new LinkedList<Activity>();

	@Override
	public void onCreate() {
		super.onCreate();
		x.Ext.init(this);// Xutils��ʼ��

	}

	/**
	 * ����ģʽ�л�ȡΨһ��Applicationʵ��
	 * 
	 * @return
	 */
	public static CLPApplication getInstance() {
		if (null == mCLPApplication) {
			mCLPApplication = new CLPApplication();
		}
		return mCLPApplication;
	}

	/**
	 * ���Activity��������,�Ӷ�ʵ������ϵͳ����ȫ�˳����� �˳��ǵ���exit();
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * �Ƴ������е�Activity;
	 * 
	 * @param activity
	 */
	public  void removeActivity(Activity activity) {
		//Toast.makeText(getApplicationContext(), "�Ƴ���һ��Activity", 0).show();
		activityList.remove(activity);
	}

	/**
	 * ��������Activity��finish
	 */
	public static void finishAll() {
		for (Activity activity : activityList) {
			activity.finish();
		}
	}

	/**
	 * �˳�����
	 **/
	public static void exitApplication() {
		finishAll();
		System.exit(0);
	}
}
