package com.fenggong.fragment;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.fenggogn.car.R;
import com.fenggong.car.Maincar;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class HomepageFragment extends Fragment {
	private Context mContext;
	@ViewInject(R.id.home_buycar)
	private View home_buycar;// 买车私家车源
	@ViewInject(R.id.home_sellingcars)
	private View home_sellingcars;// 卖车全国竞拍

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.homepage, container, false);
		x.view().inject(this, view);
		mContext=getActivity();
		home_buycar.setOnClickListener(new ClickListener());
		home_sellingcars.setOnClickListener(new ClickListener());
		return view;
	}

	private class ClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.home_buycar:
				Toast.makeText(mContext.getApplicationContext(), "点击了买车", -1).show();
				Intent home_buycarin = new Intent(getActivity(), Maincar.class);
				home_buycarin.putExtra("jumpfragment", "1");
				startActivity(home_buycarin);
				break;
			case R.id.home_sellingcars:
				Toast.makeText(mContext.getApplicationContext(), "点击了卖车", -1).show();
				Intent home_sellingcarsin=new Intent(getActivity(),Maincar.class);
				home_sellingcarsin.putExtra("jumpfragment", "2");	
				startActivity(home_sellingcarsin);
				break;

			default:
				break;
			}
		}

	}

}
