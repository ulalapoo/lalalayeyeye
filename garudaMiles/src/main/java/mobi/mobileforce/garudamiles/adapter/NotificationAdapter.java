package mobi.mobileforce.garudamiles.adapter;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.model.DiscoverModel;
import mobi.mobileforce.garudamiles.model.NotificationModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationAdapter extends ArrayAdapter<NotificationModel> {

	Context context;
	int viewResources;
	RecordHolder holder;

	public NotificationAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.viewResources = textViewResourceId;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		NotificationModel model = getItem(position);

		if (row == null) {
			holder = new RecordHolder();
			row = LayoutInflater.from(context).inflate(viewResources, parent,
					false);
			holder.imageItem = (ImageView) row.findViewById(R.id.imageItem);
			holder.textMessage = (TextView) row.findViewById(R.id.textMessage);

			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		return row;

	}

	static class RecordHolder {
		ImageView imageItem;
		TextView textMessage;

	}
}
