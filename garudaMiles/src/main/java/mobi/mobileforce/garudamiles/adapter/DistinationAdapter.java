package mobi.mobileforce.garudamiles.adapter;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.model.DiscoverModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class DistinationAdapter extends ArrayAdapter<DiscoverModel> {

	Context context;
	int viewResources;
	RecordHolder holder;

	public DistinationAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.viewResources = textViewResourceId;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		DiscoverModel model = getItem(position);

		if (row == null) {
			holder = new RecordHolder();
			row = LayoutInflater.from(context).inflate(viewResources, parent,
					false);
			holder.imageItem = (ImageView) row.findViewById(R.id.imageItem);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		holder.imageItem.setImageResource(model.getImg_default());

		return row;

	}

	static class RecordHolder {
		ImageView imageItem;

	}
}
