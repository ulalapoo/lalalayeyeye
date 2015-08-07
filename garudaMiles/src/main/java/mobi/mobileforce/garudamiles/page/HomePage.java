package mobi.mobileforce.garudamiles.page;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.TabsPagerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomePage extends Fragment implements OnClickListener {
	View view;
	ViewPager viewPager;
	TabsPagerAdapter mAdapter;
	TextView btnTravelPedia, btnDiscover, btnMe;
	View lineTravelpedia, lineDiscover, lineMe;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//TODO Auto-generated method stub

		//buat nampung custom adapter yg ada di home_page.xml
		view = inflater.inflate(R.layout.home_page, null);
		//deklarasi komponen
		btnTravelPedia = (TextView) view.findViewById(R.id.btnTravelPedia);
		btnDiscover = (TextView) view.findViewById(R.id.btnDiscover);
		btnMe = (TextView) view.findViewById(R.id.btnMe);
		btnTravelPedia.setOnClickListener(this);
		btnDiscover.setOnClickListener(this);
		btnMe.setOnClickListener(this);

		lineTravelpedia = (View) view.findViewById(R.id.lineTravelpedia);
		lineDiscover = (View) view.findViewById(R.id.lineDiscover);
		lineMe = (View) view.findViewById(R.id.lineMe);

		//pager yg ada di home_page.xml
		viewPager = (ViewPager) view.findViewById(R.id.pager);
		//adapter yg dibuat
		mAdapter = new TabsPagerAdapter(getFragmentManager());
		//untuk nanggepin perubahan
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				Log.e("position = ", "" + position);
				changeNavigationTab(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		viewPager.setAdapter(mAdapter);

		return view;
	}

	public void changeNavigationTab(int position) {
		//kondisi untuk menu ketika di swipe
		switch (position) {
		case 0:
			btnTravelPedia.setTextColor(0xFF0065b3);
			btnDiscover.setTextColor(0xFFaaaaaa);
			btnMe.setTextColor(0xFFaaaaaa);
			lineTravelpedia.setBackgroundColor(0xFF0065b3);
			lineDiscover.setBackgroundColor(0xFFaaaaaa);
			lineMe.setBackgroundColor(0xFFaaaaaa);
			break;
		case 1:
			btnTravelPedia.setTextColor(0xFFaaaaaa);
			btnDiscover.setTextColor(0xFF0065b3);
			btnMe.setTextColor(0xFFaaaaaa);
			lineTravelpedia.setBackgroundColor(0xFFaaaaaa);
			lineDiscover.setBackgroundColor(0xFF0065b3);
			lineMe.setBackgroundColor(0xFFaaaaaa);
			break;
		case 2:
			btnTravelPedia.setTextColor(0xFFaaaaaa);
			btnDiscover.setTextColor(0xFFaaaaaa);
			btnMe.setTextColor(0xFF0065b3);
			lineTravelpedia.setBackgroundColor(0xFFaaaaaa);
			lineDiscover.setBackgroundColor(0xFFaaaaaa);
			lineMe.setBackgroundColor(0xFF0065b3);
			break;

		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnTravelPedia:
			changeNavigationTab(0);
			viewPager.setCurrentItem(0);
			break;
		case R.id.btnDiscover:
			changeNavigationTab(1);
			viewPager.setCurrentItem(1);
			break;
		case R.id.btnMe:
			changeNavigationTab(2);
			viewPager.setCurrentItem(2);

			break;

		default:
			break;
		}
	}
}
