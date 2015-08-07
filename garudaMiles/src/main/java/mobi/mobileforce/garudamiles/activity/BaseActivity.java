package mobi.mobileforce.garudamiles.activity;

import java.util.ArrayList;
import java.util.List;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.menu.MenuFragment;
import mobi.mobileforce.garudamiles.utils.PixelUtil;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

@SuppressLint("NewApi")
public class BaseActivity extends SlidingFragmentActivity {

	private int mTitleRes;
	protected MenuFragment mFrag;

	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(mTitleRes);

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager()
					.beginTransaction();
			mFrag = new MenuFragment();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = new MenuFragment();
		}

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setBehindWidth(PixelUtil.dp2px(250));
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

		ActionBar actionBar = getActionBar();
		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		ImageView imageView1 = (ImageView) mCustomView
				.findViewById(R.id.imageView1);
		Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.img_avatar);

		ImageView imageAvatar = (ImageView) mCustomView
				.findViewById(R.id.imageAvatar);
		imageAvatar.setImageBitmap(getCroppedBitmap(icon));
		imageView1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				toggle();
			}
		});

		actionBar.setCustomView(mCustomView);

		// actionBar.setBackgroundDrawable(new ColorDrawable(0x0065b3));
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, android.view.MenuItem item) {
		// TODO Auto-generated method stub

		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			toggle();
			return true;
		case R.id.imageView1:
			toggle();
			break;
		}

		return true;
	}

	public Bitmap getCroppedBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
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
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
