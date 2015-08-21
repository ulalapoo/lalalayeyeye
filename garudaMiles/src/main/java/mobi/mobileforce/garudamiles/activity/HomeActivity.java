package mobi.mobileforce.garudamiles.activity;

import java.util.ArrayList;
import java.util.Stack;

import com.capricorn.ArcMenu;
import com.capricorn.ArcMenu.specialmenuCallBack;
import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.NavDrawerListAdapter;
import mobi.mobileforce.garudamiles.model.MenuModel;
import mobi.mobileforce.garudamiles.page.HomePage;
import android.app.ActionBar;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends FragmentActivity implements
		specialmenuCallBack {
	RelativeLayout customLinearLayout = null;
	private static final int[] ITEM_DRAWABLES = { R.drawable.add_a_photo,
			R.drawable.write_a_review, R.drawable.add_attraction, };
	boolean menu_path = false;

	TextView textMenu1;
	TextView textMenu2;
	TextView textMenu3;
	ImageView imagebackroundtransparant;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private ArrayList<MenuModel> navDrawerItems;
	private String[] menu_title = new String[] { "", "Travelopedia",
			"Discover", "Me", "Edit Profile", "Setting", "Sign Out",
			"About Garuda Social Miles" };

	private int[] menu_icon = new int[] { 0, R.drawable.btn_travelopedia,
			R.drawable.btn_discover, R.drawable.btn_me,
			R.drawable.btn_edit_profile, R.drawable.btn_setting,
			R.drawable.btn_sign_out, R.drawable.btn_about };
	private NavDrawerListAdapter adapter;
	private Stack<Fragment> fragmentStack;

	// public HomeActivity() {
	// super(R.string.app_name);
	// // TODO Auto-generated constructor stub
	// }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set the Above View
		setContentView(R.layout.content_frame);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, new HomePage()).commit();
		ArcMenu arcMenu = (ArcMenu) findViewById(R.id.arc_menu);
		textMenu1 = (TextView) findViewById(R.id.textViewMenu1);
		textMenu2 = (TextView) findViewById(R.id.textViewMenu2);
		textMenu3 = (TextView) findViewById(R.id.textViewMenu3);
		imagebackroundtransparant = (ImageView) findViewById(R.id.imagebackroundtransparant);
		textMenu1.setVisibility(View.INVISIBLE);
		textMenu2.setVisibility(View.INVISIBLE);
		textMenu3.setVisibility(View.INVISIBLE);

		navDrawerItems = new ArrayList<MenuModel>();
		for (int i = 0; i < 8; i++) {
			MenuModel modelItem = new MenuModel();
			modelItem.setMenuTitle(menu_title[i]);
			modelItem.setRes(menu_icon[i]);

			if (i == 0) {
				modelItem.setTypeItem(true);
			} else {
				modelItem.setTypeItem(false);
			}
			navDrawerItems.add(modelItem);
		}

		arcMenu.setspecialmenuCallBack(this);

		// setSlidingActionBarEnabled(true);
		// setSlidingActionBarEnabled(false);
		initArcMenu(arcMenu, ITEM_DRAWABLES);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int f = metrics.densityDpi;
		switch (getResources().getDisplayMetrics().densityDpi) {
		case DisplayMetrics.DENSITY_LOW:
			Log.e("DPI ", "LDPI");
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			Log.e("DPI ", "MDPI");
			break;
		case DisplayMetrics.DENSITY_HIGH:
			Log.e("DPI ", "HDPI");
			break;
		case DisplayMetrics.DENSITY_XHIGH:
			Log.e("DPI ", "XDPI");
			break;
		case DisplayMetrics.DENSITY_XXHIGH:
			Log.e("DPI ", "XXDPI");
			break;
		}

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// getActionBar().setDisplayHomeAsUpEnabled(true);
		// getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.header_menu, // nav menu toggle iconapter
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {

				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {

				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		ImageView img = (ImageView) findViewById(android.R.id.home);
		Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.img_avatar);
		img.setImageBitmap(getCroppedBitmap(icon));

		img.setPadding(30, 0, 0, 0);
		ActionBar actionBar = getActionBar();
		// LayoutInflater mInflater = LayoutInflater.from(this);
		// View mCustomView = mInflater.inflate(R.layout.custom_actionbar,
		// null);
		// ImageView imageView1 = (ImageView) mCustomView
		// .findViewById(R.id.imageView1);
		// Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
		// R.drawable.img_avatar);
		//
		// ImageView imageAvatar = (ImageView) mCustomView
		// .findViewById(R.id.imageAvatar);
		// imageAvatar.setImageBitmap(getCroppedBitmap(icon));
		// imageView1.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		//
		// }
		// });
		// actionBar.setCustomView(mCustomView);
		//
		// // actionBar.setBackgroundDrawable(new ColorDrawable(0x0065b3));
		// actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);
		Drawable d = new BitmapDrawable(this.getResources(),
				getCroppedBitmap(icon));

		actionBar.setIcon(d);
		actionBar.setBackgroundDrawable(new ColorDrawable(0xFF0065b3));
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowHomeEnabled(true);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}

	}

	private void initArcMenu(ArcMenu menu, int[] itemDrawables) {
		final int itemCount = 3;
		for (int i = 0; i < itemCount; i++) {
			LayoutInflater layoutInflater = (LayoutInflater) HomeActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = layoutInflater.inflate(R.layout.item_menu, null);
			ImageView item = (ImageView) view.findViewById(R.id.imageItem);
			item.setImageResource(itemDrawables[i]);

			final int position = i;
			menu.addItem(view, new OnClickListener() {

				@Override
				public void onClick(View v) {
					// Toast.makeText(HomeActivity.this, "position:" + position,
					// Toast.LENGTH_SHORT).show();

					Animation animfadeAnim = AnimationUtils.loadAnimation(
							getApplicationContext(), R.anim.fadeout);

					animfadeAnim.setDuration(500);
					animfadeAnim.setFillAfter(true);
					animfadeAnim.setFillEnabled(true);

					Animation animSideDown = AnimationUtils.loadAnimation(
							getApplicationContext(), R.anim.dismiss_bounce);
					animSideDown.setDuration(500);
					animSideDown.setFillAfter(true);
					animSideDown.setFillEnabled(true);

					textMenu1.startAnimation(animSideDown);
					textMenu2.startAnimation(animSideDown);
					textMenu3.startAnimation(animSideDown);
					textMenu1.setVisibility(View.INVISIBLE);
					textMenu2.setVisibility(View.INVISIBLE);
					textMenu3.setVisibility(View.INVISIBLE);

					imagebackroundtransparant.startAnimation(animfadeAnim);
					imagebackroundtransparant.setVisibility(View.INVISIBLE);
				}
			});
		}
	}

	@Override
	public void subMenuEvent(boolean event) {
		// TODO Auto-generated method stub
		Log.e("menu = ", "" + event);
		if (!event) {
			Animation animfadeAnim = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.fadein);

			animfadeAnim.setDuration(500);
			animfadeAnim.setFillAfter(true);
			animfadeAnim.setFillEnabled(true);

			Animation animSideDown = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.zoom_bounce);
			animSideDown.setDuration(500);
			animSideDown.setFillAfter(true);
			animSideDown.setFillEnabled(true);
			textMenu1.setVisibility(View.VISIBLE);
			textMenu2.setVisibility(View.VISIBLE);
			textMenu3.setVisibility(View.VISIBLE);

			textMenu1.startAnimation(animSideDown);
			textMenu2.startAnimation(animSideDown);
			textMenu3.startAnimation(animSideDown);
			imagebackroundtransparant.setVisibility(View.VISIBLE);
			imagebackroundtransparant.startAnimation(animfadeAnim);

		} else {
			Animation animfadeAnim = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.fadeout);

			animfadeAnim.setDuration(500);
			animfadeAnim.setFillAfter(true);
			animfadeAnim.setFillEnabled(true);

			Animation animSideDown = AnimationUtils.loadAnimation(
					getApplicationContext(), R.anim.dismiss_bounce);
			animSideDown.setDuration(500);
			animSideDown.setFillAfter(true);
			animSideDown.setFillEnabled(true);

			textMenu1.startAnimation(animSideDown);
			textMenu2.startAnimation(animSideDown);
			textMenu3.startAnimation(animSideDown);
			textMenu1.setVisibility(View.INVISIBLE);
			textMenu2.setVisibility(View.INVISIBLE);
			textMenu3.setVisibility(View.INVISIBLE);

			imagebackroundtransparant.startAnimation(animfadeAnim);
			imagebackroundtransparant.setVisibility(View.INVISIBLE);

		}
	}

	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void displayView(int position) {
		// update the main content by replacing fragments
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		// menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
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
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

	}
}
