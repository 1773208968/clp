package com.fenggong.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Analyzing_Network {

	/**
	 *     ����ֵ -1��û������  
	 *     1��WIFI����
	 *     2��wap����
	 *     3��net����  
	 * @param context
	 * @return
	 */
	public static int GetNetype(Context context) {
		int netType = -1;
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
				netType = 3;
			} else {
				netType = 2;
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = 1;
		}
		return netType;
	}

}
