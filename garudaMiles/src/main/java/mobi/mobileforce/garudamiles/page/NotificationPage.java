package mobi.mobileforce.garudamiles.page;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.NotificationAdapter;
import mobi.mobileforce.garudamiles.model.NotificationModel;
import mobi.mobileforce.garudamiles.page.DestinationPage.MyOnClickDestinationPage;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class NotificationPage extends Fragment {

	View view;
	ArrayList<NotificationModel> dataArray = new ArrayList<NotificationModel>();
	NotificationAdapter adapter;
	ListView listView;

	MyOnClickNotificationPage listener;

	public interface MyOnClickNotificationPage {
		public void onItemNotificationPage(int id);
	}

	public void registerForListener(MyOnClickNotificationPage listener) {
		this.listener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.notification_page, null);
		listView = (ListView) view.findViewById(R.id.listNotification);
		adapter = new NotificationAdapter(getActivity(),
				R.layout.item_notification);
		listView.setAdapter(adapter);
		getData();

		return view;

	}

	public void getData() {
		for (int i = 0; i < 10; i++) {
			NotificationModel model = new NotificationModel();
			dataArray.add(model);
		}

		adapter.addAll(dataArray);
		adapter.notifyDataSetChanged();
	}

}
