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
		x.Ext.init(this);// Xutils初始化

	}

	/**
	 * 单例模式中获取唯一的Application实例
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
	 * 添加Activity到容器中,从而实现整个系统的完全退出功能 退出是调用exit();
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	/**
	 * 移除容器中的Activity;
	 * 
	 * @param activity
	 */
	public  void removeActivity(Activity activity) {
		//Toast.makeText(getApplicationContext(), "移出了一个Activity", 0).show();
		activityList.remove(activity);
	}

	/**
	 * 遍历所有Activity并finish
	 */
	public static void finishAll() {
		for (Activity activity : activityList) {
			activity.finish();
		}
	}

	/**
	 * 退出程序
	 **/
	public static void exitApplication() {
		finishAll();
		System.exit(0);
	}
}
