package mobi.mobileforce.garudamiles.page;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.DiscoverAdapter;
import mobi.mobileforce.garudamiles.adapter.DistinationAdapter;
import mobi.mobileforce.garudamiles.adapter.TravelpediaAdapter;
import mobi.mobileforce.garudamiles.model.DiscoverModel;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DestinationPage extends Fragment implements OnItemClickListener {

	View view;

	int[] drawable_asset = new int[] { R.drawable.bali, R.drawable.trip,
			R.drawable.trip2, R.drawable.bali, R.drawable.trip,
			R.drawable.trip2, };

	ArrayList<DiscoverModel> dataArray = new ArrayList<DiscoverModel>();
	DistinationAdapter adapter;
	ListView listView;
	MyOnClickDestinationPage listener;
	private TextView textTile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActivity().getActionBar();
		LayoutInflater mInflater = LayoutInflater.from(getActivity());
		View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
		textTile = (TextView) mCustomView.findViewById(R.id.textTitle);
		textTile.setText("All Destination");
	}

	public interface MyOnClickDestinationPage {
		public void onItemDestinationPage(int id);
	}

	public void registerForListener(MyOnClickDestinationPage listener) {
		this.listener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.destination_page, null);
		listView = (ListView) view.findViewById(R.id.listDestination);
		adapter = new DistinationAdapter(getActivity(),
				R.layout.item_distination);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		getData();
		return view;
	}

	public void getData() {
		for (int i = 0; i < drawable_asset.length; i++) {
			DiscoverModel modelitem = new DiscoverModel();
			modelitem.setImg_default(drawable_asset[i]);
			dataArray.add(modelitem);
		}

		adapter.addAll(dataArray);
		adapter.notifyDataSetChanged();

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.e("position = ", "" + position);
		listener.onItemDestinationPage(0);
	}

}
