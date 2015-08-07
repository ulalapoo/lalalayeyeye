package mobi.mobileforce.garudamiles.page;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.DestinationDetailAdapter;
import mobi.mobileforce.garudamiles.model.TravelpediaModel;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class DestinationDetailPage extends Fragment {
	View view;
	DestinationDetailAdapter adapter;
	ArrayList<TravelpediaModel> dataArray = new ArrayList<TravelpediaModel>();
	int[] drawable_asset = new int[] { R.drawable.trip, R.drawable.trip2,
			R.drawable.trip, R.drawable.trip2, };
	String[] type = new String[] { "2", "2", "2", "2", "2", "2", };
	private ListView listView;
	private TextView textTile;

	MyOnClickDestinationDetailPage listener;

	public interface MyOnClickDestinationDetailPage {
		public void onItemDestinationDetailPage(int id, boolean header_bukan);
	}

	public void registerForListener(MyOnClickDestinationDetailPage listener) {
		this.listener = listener;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActivity().getActionBar();
		LayoutInflater mInflater = LayoutInflater.from(getActivity());
		View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
		textTile = (TextView) mCustomView.findViewById(R.id.textTitle);
		textTile.setText("Bali");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.destination_detail_page, null);
		adapter = new DestinationDetailAdapter(getActivity(),
				R.layout.item_list_travelpedia_type2);
		listView = (ListView) view.findViewById(R.id.listDestination);
		ViewGroup header = (ViewGroup) inflater.inflate(
				R.layout.header_travelpedia_detail, listView, false);
		LinearLayout btnAttraction = (LinearLayout) header
				.findViewById(R.id.btnAttraction);
		LinearLayout btnContributors = (LinearLayout) header
				.findViewById(R.id.btnContributors);
		LinearLayout btnPhotos = (LinearLayout) header
				.findViewById(R.id.btnPhotos);

		btnAttraction.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listener.onItemDestinationDetailPage(R.id.btnAttraction, true);
			}
		});

		btnContributors.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listener.onItemDestinationDetailPage(R.id.btnContributors, true);
			}
		});

		btnPhotos.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				listener.onItemDestinationDetailPage(R.id.btnPhotos, true);
			}
		});

		listView.addHeaderView(header, null, false);
		listView.setAdapter(adapter);
		getdata();
		return view;
	}

	public void getdata() {
		for (int i = 0; i < drawable_asset.length; i++) {
			TravelpediaModel item = new TravelpediaModel();
			item.setDefault_drawable(drawable_asset[i]);
			item.setType(type[i]);
			dataArray.add(item);
		}

		adapter.addAll(dataArray);
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		textTile.setText("Bali");
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		super.onHiddenChanged(hidden);
		Log.e("bool all ", "" + hidden);
		if (!hidden) {
			textTile.setText("Balii");
		}
	}

}
