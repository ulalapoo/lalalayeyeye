package mobi.mobileforce.garudamiles.page;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.AttractionAdapter;
import mobi.mobileforce.garudamiles.model.ReviewModel;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.ListView;

public class DetailDiscover extends Fragment {

	View view;
	AttractionAdapter adapter;
	ArrayList<ReviewModel> dataArray = new ArrayList<ReviewModel>();
	int[] drawable_asset = new int[] { R.drawable.trip, R.drawable.trip2,
			R.drawable.trip, R.drawable.trip2, };
	String[] type = new String[] { "2", "2", "2", "2", "2", "2", };
	ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.detail_discover, null);
		adapter = new AttractionAdapter(getActivity(), R.layout.item_review);
		listView = (ListView) view.findViewById(R.id.listDiscover);
		ViewGroup header = (ViewGroup) inflater.inflate(
				R.layout.header_discover, listView, false);

		Bitmap icon = BitmapFactory.decodeResource(
				getActivity().getResources(), R.drawable.img_avatar);
		ImageView imageAvatar = (ImageView) header
				.findViewById(R.id.imageAvatar);
		imageAvatar.setImageBitmap(getCroppedBitmap(icon));

		ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.footer_layout,
				listView, false);
		listView.addHeaderView(header, null, false);
		listView.addFooterView(footer, null, false);
		listView.setAdapter(adapter);
		getdata();
		return view;
	}

	public void getdata() {
		for (int i = 0; i < drawable_asset.length; i++) {
			ReviewModel item = new ReviewModel();
			dataArray.add(item);
		}

		adapter.addAll(dataArray);
		adapter.notifyDataSetChanged();
	}

	public Bitmap getCroppedBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_4444);
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
