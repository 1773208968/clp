package com.fenggong.car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.fenggogn.car.R;
import com.fenggong.baseadapter.SortAdapter;
import com.fenggong.service.CharacterParser;
import com.fenggong.service.CitySortModel;
import com.fenggong.service.PinyinComparator;
import com.fenggong.service.SideBar;
import com.fenggong.system.CLPApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Buycar_city extends Activity {

	@ViewInject(R.id.buycar_city_country_lvcountry)
	private ListView sortListView;
	@ViewInject(R.id.buycar_city_sidrbar)
	private SideBar sideBar;
	@ViewInject(R.id.buycar_city_dialog)
	private TextView dialog;

	private SortAdapter adapter;
	private CharacterParser characterParser;
	private List<CitySortModel> SourceDateList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buycar_city);

		x.view().inject(this);
		CLPApplication.getInstance().addActivity(this);
		initViews();
	}

	private void initViews() {
		// TODO Auto-generated method stub
		characterParser = CharacterParser.getInstance();
		sideBar.setTextView(dialog);
		sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				int position = adapter.getPositionForSection(s.charAt(0));
				if (position != -1) {
					sortListView.setSelection(position + 1);
				}

			}
		});

		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// Toast.makeText(getApplication(),((CitySortModel)
				// adapter.getItem(position - 1)).getName(),
				// Toast.LENGTH_SHORT).show();
				
//				Toast.makeText(getApplication(),
//						((CitySortModel) adapter.getItem(position)).getName(),
//						Toast.LENGTH_SHORT).show();
				String name=(String) ((CitySortModel) adapter.getItem(position)).getName();
				Intent intent = new Intent();
				intent.putExtra("data_return", name.toString());
				setResult(RESULT_OK, intent);
				finish();
			}

		});

		/**
		 * 后台获取城市数据 然后 传入SourceDateList中 现在是array资源获取的
		 */
		SourceDateList = filledData(getResources().getStringArray(
				R.array.provinces));// 这是array资源
		Collections.sort(SourceDateList, new PinyinComparator());
		adapter = new SortAdapter(this, SourceDateList);
		// sortListView.addHeaderView(initHeadView());
		sortListView.setAdapter(adapter);
	}

	/**
	 * 自动定位
	 * 
	 * @return
	 */
	private View initHeadView() {
		// TODO Auto-generated method stub
		View headView = getLayoutInflater().inflate(R.layout.item_select_city,
				null);
		TextView tv_catagory = (TextView) headView
				.findViewById(R.id.tv_catagory);
		TextView tv_city_name = (TextView) headView
				.findViewById(R.id.tv_city_name);
		tv_catagory.setText("自动定位@");
		tv_city_name.setText("北京@");
		tv_city_name.setCompoundDrawablesWithIntrinsicBounds(getResources()
				.getDrawable(R.drawable.ic_city_location), null, null, null);
		tv_city_name.setCompoundDrawablePadding(24);
		return headView;
	}

	private List<CitySortModel> filledData(String[] date) {
		List<CitySortModel> mSortList = new ArrayList<CitySortModel>();
		ArrayList<String> indexString = new ArrayList<String>();

		for (int i = 0; i < date.length; i++) {
			CitySortModel sortModel = new CitySortModel();
			sortModel.setName(date[i]);
			String pinyin = characterParser.getSelling(date[i]);
			String sortString = pinyin.substring(0, 1).toUpperCase();
			if (sortString.matches("[A-Z]")) {

				// 对重庆多音字做特殊处理
				if (pinyin.startsWith("zhongqing")) {
					sortString = "C";
					sortModel.setSortLetters("C");
				} else {
					sortModel.setSortLetters(sortString.toUpperCase());
				}

				if (!indexString.contains(sortString)) {
					indexString.add(sortString);
				}
			}

			mSortList.add(sortModel);
		}
		Collections.sort(indexString);
		sideBar.setIndexText(indexString);
		return mSortList;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		CLPApplication.getInstance().removeActivity(this);
	}
}
