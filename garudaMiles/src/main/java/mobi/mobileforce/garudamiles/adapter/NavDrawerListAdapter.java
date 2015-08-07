package mobi.mobileforce.garudamiles.adapter;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.model.MenuModel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NavDrawerListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<MenuModel> navDrawerItems;

	public NavDrawerListAdapter(Context context,
			ArrayList<MenuModel> navDrawerItems) {
		this.context = context;
		this.navDrawerItems = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.item_side_menu, null);
		}

		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
		TextView txtTitle = (TextView) convertView
				.findViewById(R.id.textTravelopediaSide);
		LinearLayout titleLayout = (LinearLayout) convertView
				.findViewById(R.id.titleLayout);
		LinearLayout Layoutbtn = (LinearLayout) convertView
				.findViewById(R.id.Layoutbtn);

		// displaying count
		// check whether it set visible or not
		if (navDrawerItems.get(position).isTypeItem()) {
			titleLayout.setVisibility(View.VISIBLE);
			Layoutbtn.setVisibility(View.GONE);
			TextView txtUsername = (TextView) convertView
					.findViewById(R.id.textUsername);
			TextView textStatus = (TextView) convertView
					.findViewById(R.id.textStatus);
		} else {
			// hide the counter view
			titleLayout.setVisibility(View.GONE);
			Layoutbtn.setVisibility(View.VISIBLE);
			imgIcon.setImageResource(navDrawerItems.get(position).getRes());
			txtTitle.setText(navDrawerItems.get(position).getMenuTitle());
		}

		return convertView;
	}

}
