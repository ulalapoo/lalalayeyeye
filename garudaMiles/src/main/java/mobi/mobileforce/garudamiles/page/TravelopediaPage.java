package mobi.mobileforce.garudamiles.page;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.activity.DetailActivity;
import mobi.mobileforce.garudamiles.adapter.TravelpediaAdapter;
import mobi.mobileforce.garudamiles.model.TravelpediaModel;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class TravelopediaPage extends Fragment {
	View view;
	ListView listView;
	TravelpediaAdapter adapter;
	ArrayList<TravelpediaModel> dataArray = new ArrayList<TravelpediaModel>();

	int[] drawable_asset = new int[] { R.drawable.bali, R.drawable.trip,
			R.drawable.trip2, R.drawable.bali, R.drawable.trip,
			R.drawable.trip2, };

	String[] type = new String[] { "1", "2", "2", "1", "2", "2", };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.travelpediapage, null);
		listView = (ListView) view.findViewById(R.id.listTravelpedia);
		adapter = new TravelpediaAdapter(getActivity(),
				R.layout.item_list_travelpedia_type1);

		ViewGroup header = (ViewGroup) inflater.inflate(
				R.layout.header_travelpedia, listView, false);
		Button alldestinantion = (Button) header
				.findViewById(R.id.alldestinantion);
		alldestinantion.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), DetailActivity.class);
				i.putExtra("type", "alldestination");
				getActivity().startActivity(i);
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

	public Bitmap getCroppedBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.RGB_565);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
				bitmap.getWidth() / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
}
