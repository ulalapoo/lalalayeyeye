package mobi.mobileforce.garudamiles.page;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.activity.DetailActivity2;
import android.content.Intent;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MePage extends Fragment implements OnClickListener {

	View view;
	ImageView imageAvatar;
	LinearLayout btnNotification, btnDraft, btnPhotoAlbum;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.mepage, null);
		imageAvatar = (ImageView) view.findViewById(R.id.circleBg);

		btnNotification = (LinearLayout) view
				.findViewById(R.id.btnNotification);
		btnNotification.setOnClickListener(this);
		btnDraft = (LinearLayout) view
				.findViewById(R.id.btnDraft);
		btnDraft.setOnClickListener(this);
		btnPhotoAlbum = (LinearLayout) view
				.findViewById(R.id.btnPhotoAlbum);
		btnPhotoAlbum.setOnClickListener(this);

		Bitmap icon = BitmapFactory.decodeResource(getResources(),
				R.drawable.img_avatar);
		imageAvatar.setImageBitmap(getCroppedBitmap(icon));
		return view;
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i;
		switch (v.getId()) {
			case R.id.btnNotification:
				i = new Intent(getActivity(), DetailActivity2.class);
				i.putExtra("type", "notification");
				startActivity(i);
				break;
			case R.id.btnDraft:
				i = new Intent(getActivity(), DetailActivity2.class);
				i.putExtra("type", "draft");
				startActivity(i);
				break;
			case R.id.btnPhotoAlbum:
				i = new Intent(getActivity(), DetailActivity2.class);
				i.putExtra("type", "photoalbum");
				startActivity(i);
				break;

			default:
			break;
		}
	}
}
