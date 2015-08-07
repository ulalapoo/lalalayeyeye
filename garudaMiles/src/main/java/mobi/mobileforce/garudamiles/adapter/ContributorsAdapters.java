package mobi.mobileforce.garudamiles.adapter;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.CulinaryAdapter.RecordHolder;
import mobi.mobileforce.garudamiles.model.ContributorsModel;
import mobi.mobileforce.garudamiles.model.DiscoverModel;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ContributorsAdapters extends ArrayAdapter<ContributorsModel> {

	Context context;
	int viewResources;
	RecordHolder holder;

	public ContributorsAdapters(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.viewResources = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		ContributorsModel model = getItem(position);

		if (row == null) {
			holder = new RecordHolder();
			row = LayoutInflater.from(context).inflate(viewResources, parent,
					false);
			holder.imageItem = (ImageView) row.findViewById(R.id.imageItem);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.img_avatar);

		holder.imageItem.setImageBitmap(getCroppedBitmap(icon));

		return row;
	}

	static class RecordHolder {
		ImageView imageItem;

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
