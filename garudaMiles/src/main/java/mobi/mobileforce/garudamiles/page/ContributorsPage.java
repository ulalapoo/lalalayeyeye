package mobi.mobileforce.garudamiles.page;

import java.util.ArrayList;

import com.capricorn.ArcMenu;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.ContributorsAdapters;
import mobi.mobileforce.garudamiles.model.ContributorsModel;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ContributorsPage extends Fragment {

	View view;
	ListView listContributors;
	ContributorsAdapters adapter;
	ArrayList<ContributorsModel> arrayData = new ArrayList<ContributorsModel>();
	ArcMenu arcMenu;
	MyOnClickContributorsPage listener;
	private TextView textTile;

	public interface MyOnClickContributorsPage {
		public void onItemContributorsPage(int id);
	}

	public void registerForListener(MyOnClickContributorsPage listener) {
		this.listener = listener;
	}

	public ContributorsPage(ArcMenu arcMenu) {
		// TODO Auto-generated constructor stub
		this.arcMenu = arcMenu;
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
		view = inflater.inflate(R.layout.contributors_page, null);
		arcMenu.setVisibility(View.GONE);
		listContributors = (ListView) view.findViewById(R.id.listContributors);
		adapter = new ContributorsAdapters(getActivity(),
				R.layout.item_contributor);
		listContributors.setAdapter(adapter);
		getData();
		return view;
	}

	public void getData() {
		for (int i = 0; i < 10; i++) {
			ContributorsModel model = new ContributorsModel();
			arrayData.add(model);
		}
		adapter.addAll(arrayData);
		adapter.notifyDataSetChanged();
	}
}
