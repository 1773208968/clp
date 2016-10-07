package com.fenggong.fragment;

import java.util.ArrayList;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.fenggogn.car.R;
import com.fenggong.car.Buy_car_details;
import com.fenggong.car.Buycar_city;
import com.fenggong.view.ExpandTabView;
import com.fenggong.view.ViewLeft;
import com.fenggong.view.ViewMiddle;
import com.fenggong.view.ViewRight;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Buy_carFragment extends Fragment {
	private Context mContext;

	@ViewInject(R.id.buycar_city)
	private TextView buycar_city;
	@ViewInject(R.id.expandtab_view)
	private ExpandTabView expandTabView;
	@ViewInject(R.id.buy_car_layoutcar)
	private View buy_car_layoutcar;//临时 跳转 车详细页面

	private ArrayList<View> mViewArray = new ArrayList<View>();
	private ViewLeft viewLeft;
	private ViewMiddle viewMiddle;
	private ViewRight viewRight;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.buy_car, container, false);
		x.view().inject(this, view);
		mContext = this.getActivity();

		buycar_city.setOnClickListener(new ClickListener());
		buy_car_layoutcar.setOnClickListener(new ClickListener());

		initView();
		initVaule();
		initListener();
		return view;
	}

	private void initView() {

		viewLeft = new ViewLeft(mContext);
		viewMiddle = new ViewMiddle(mContext);
		viewRight = new ViewRight(mContext);

	}

	private void initVaule() {

		mViewArray.add(viewLeft);
		mViewArray.add(viewMiddle);
		mViewArray.add(viewRight);
		ArrayList<String> mTextArray = new ArrayList<String>();
		mTextArray.add("距离");
		mTextArray.add("区域");
		mTextArray.add("距离");
		expandTabView.setValue(mTextArray, mViewArray);
		expandTabView.setTitle("排序", 0);
		expandTabView.setTitle("品牌", 1);
		expandTabView.setTitle("价格", 2);
		// 初始标题
		// expandTabView.setTitle(viewLeft.getShowText(), 0);
		// expandTabView.setTitle(viewMiddle.getShowText(), 1);
		// expandTabView.setTitle(viewRight.getShowText(), 2);

	}

	private void initListener() {

		viewLeft.setOnSelectListener(new ViewLeft.OnSelectListener() {

			@Override
			public void getValue(String distance, String showText) {
				onRefresh(viewLeft, showText);
			}
		});

		viewMiddle.setOnSelectListener(new ViewMiddle.OnSelectListener() {

			@Override
			public void getValue(String showText) {

				onRefresh(viewMiddle, showText);

			}
		});

		viewRight.setOnSelectListener(new ViewRight.OnSelectListener() {

			@Override
			public void getValue(String distance, String showText) {
				onRefresh(viewRight, showText);
			}
		});

	}

	private void onRefresh(View view, String showText) {

		expandTabView.onPressBack();
		int position = getPositon(view);
		if (position >= 0 && !expandTabView.getTitle(position).equals(showText)) {
			// expandTabView.setTitle(showText, position); 选择改变 标题
		}
		Toast.makeText(mContext.getApplicationContext(), showText,
				Toast.LENGTH_SHORT).show();

	}

	private int getPositon(View tView) {
		for (int i = 0; i < mViewArray.size(); i++) {
			if (mViewArray.get(i) == tView) {
				return i;
			}
		}
		return -1;
	}

	public void onBackPressed() {

		if (!expandTabView.onPressBack()) {
			// finish();
			expandTabView.onPressBack();
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
			case R.id.buycar_city:
				Intent intent = new Intent(getActivity(), Buycar_city.class);
				startActivityForResult(intent, 1);
				break;
			case R.id.buy_car_layoutcar:
				Intent intent1 = new Intent(getActivity(), Buy_car_details.class);
				startActivity(intent1);
				break;

			default:
				break;
			}
		}
	}

	/**
	 * 城市返回数据
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case 1:
			if (resultCode == getActivity().RESULT_OK) {
				String returnedData = data.getStringExtra("data_return");
				buycar_city.setText(returnedData);
			}
			break;

		default:
			break;
		}

	}
}
