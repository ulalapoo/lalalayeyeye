package mobi.mobileforce.garudamiles.page;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.DistinationAdapter;
import mobi.mobileforce.garudamiles.model.DiscoverModel;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CulinaryPage extends Fragment implements OnItemClickListener {
	View view;

	int[] drawable_asset = new int[] { R.drawable.image_culinary_1,
			R.drawable.image_culinary_2, R.drawable.image_culinary_3,
			R.drawable.image_culinary_4, };

	ArrayList<DiscoverModel> dataArray = new ArrayList<DiscoverModel>();
	DistinationAdapter adapter;
	ListView listView;

	MyOnClickCulinaryPage listener;

	public interface MyOnClickCulinaryPage {
		public void onItemCulinaryPage(int id);
	}

	public void registerForListener(MyOnClickCulinaryPage listener) {
		this.listener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.culinary_page, null);
		listView = (ListView) view.findViewById(R.id.listCulinary);
		adapter = new DistinationAdapter(getActivity(),
				R.layout.item_culinary_list);
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
		listener.onItemCulinaryPage(0);
	}
}
