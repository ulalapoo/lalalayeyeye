package mobi.mobileforce.garudamiles.adapter;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.model.AttractionModel;
import mobi.mobileforce.garudamiles.model.ReviewModel;
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
import android.widget.LinearLayout;

public class AttractionAdapter extends ArrayAdapter<ReviewModel> {

	Context context;
	int viewResources;

	public AttractionAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.viewResources = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		row = LayoutInflater.from(context)
				.inflate(viewResources, parent, false);
		LinearLayout advanceReviews = (LinearLayout) row
				.findViewById(R.id.advanceReviews);
		LinearLayout advanceImage = (LinearLayout) row
				.findViewById(R.id.advanceImage);
//		advanceImage.setVisibility(View.GONE);
//		advanceReviews.setVisibility(View.GONE);
		Bitmap icon = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.img_avatar);
		ImageView imageAvatar = (ImageView) row.findViewById(R.id.imageItem);
		imageAvatar.setImageBitmap(getCroppedBitmap(icon));
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

}
