package mobi.mobileforce.garudamiles.adapter;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.model.TravelpediaModel;
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

public class DestinationDetailAdapter extends ArrayAdapter<TravelpediaModel> {

	int resourcesID;
	Context context;
	RecordHolder holder = null;

	public DestinationDetailAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
		this.context = context;
		resourcesID = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;

		TravelpediaModel model = getItem(position);
		if (row == null) {
			holder = new RecordHolder();
			row = LayoutInflater.from(context).inflate(resourcesID, parent,
					false);
			holder.imageAvatar = (ImageView) row.findViewById(R.id.circleBg);

			Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
					R.drawable.img_avatar);
			holder.imageAvatar.setImageBitmap(getCroppedBitmap(icon));

			holder.imageViewItem = (ImageView) row.findViewById(R.id.imageItem);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		if (model.getType().equalsIgnoreCase("2")) {

		}
		holder.imageViewItem.setImageResource(model.getDefault_drawable());

		return row;
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

	static class RecordHolder {
		ImageView imageViewItem;
		ImageView imageAvatar;

	}
}
