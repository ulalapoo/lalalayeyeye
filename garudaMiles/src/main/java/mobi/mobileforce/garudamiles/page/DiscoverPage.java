package mobi.mobileforce.garudamiles.page;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.activity.DetailActivity;
import mobi.mobileforce.garudamiles.activity.DetailActivity2;
import mobi.mobileforce.garudamiles.adapter.DiscoverAdapter;
import mobi.mobileforce.garudamiles.adapter.TravelpediaAdapter;
import mobi.mobileforce.garudamiles.model.DiscoverModel;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DiscoverPage extends Fragment implements OnItemClickListener {

	View view;

	int[] drawable_asset = new int[] { R.drawable.discover_img_3,
			R.drawable.discover_img_2, R.drawable.discover_img_1,
			R.drawable.discover_img_3, R.drawable.discover_img_2,
			R.drawable.discover_img_1 };
	String[] typePage = new String[] { "culinary", "heritage", "sport",
			"culinary", "heritage", "sport" };
	ArrayList<DiscoverModel> dataArray = new ArrayList<DiscoverModel>();
	DiscoverAdapter adapter;
	ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.dicoverpage, null);
		listView = (ListView) view.findViewById(R.id.listDiscover);
		adapter = new DiscoverAdapter(getActivity(), R.layout.item_discover);
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
		Intent i = new Intent(getActivity(), DetailActivity2.class);
		i.putExtra("type", typePage[position]);
		startActivity(i);
		
	}
}
