package mobi.mobileforce.garudamiles.activity;

import java.util.Stack;

import com.capricorn.ArcMenu;
import com.capricorn.ArcMenu.specialmenuCallBack;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.page.AlbumPage;
import mobi.mobileforce.garudamiles.page.AttractionListMapPage.MyOnClickAttractionListMapPage;
import mobi.mobileforce.garudamiles.page.ContributorsPage;
import mobi.mobileforce.garudamiles.page.ContributorsPage.MyOnClickContributorsPage;
import mobi.mobileforce.garudamiles.page.CulinaryPage;
import mobi.mobileforce.garudamiles.page.CulinaryPage.MyOnClickCulinaryPage;
import mobi.mobileforce.garudamiles.page.DestinationDetailPage;
import mobi.mobileforce.garudamiles.page.DestinationDetailPage.MyOnClickDestinationDetailPage;
import mobi.mobileforce.garudamiles.page.DestinationPage;
import mobi.mobileforce.garudamiles.page.DestinationPage.MyOnClickDestinationPage;
import mobi.mobileforce.garudamiles.page.AttractionListMapPage;
import mobi.mobileforce.garudamiles.page.DetailAttraction;
import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends FragmentActivity implements
		specialmenuCallBack, MyOnClickDestinationPage, MyOnClickCulinaryPage,
		MyOnClickDestinationDetailPage, MyOnClickAttractionListMapPage,MyOnClickContributorsPage, AlbumPage.MyOnClickAlbumPage {

	TextView textMenu1;
	TextView textMenu2;
	TextView textMenu3;
	ImageView imagebackroundtransparant;
	String type;

	private static final int[] ITEM_DRAWABLES = { R.drawable.add_a_photo,
			R.drawable.write_a_review, R.drawable.add_attraction, };
	TextView textTile;
	private Stack<Fragment> fragmentStack;
	private FragmentManager fragmentManager;
	private DestinationDetailPage detaiDestinationPage;
	private DestinationPage destinationPage;
	private CulinaryPage culinaryPage;
	private AttractionListMapPage attractionListMapPage;
	ArcMenu arcMenu;
	private DetailAttraction detailAttraction;
	ContributorsPage contributorsPage;
	AlbumPage albumPage;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.detail_frame);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			type = extras.getString("type", "");
		}

		ActionBar actionBar = getActionBar();
		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
		textTile = (TextView) mCustomView.findViewById(R.id.textTitle);

		actionBar.setCustomView(mCustomView);
		ImageView btnBack = (ImageView) mCustomView.findViewById(R.id.btnBack);
		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		// actionBar.setBackgroundDrawable(new ColorDrawable(0x0065b3));
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);

		arcMenu = (ArcMenu) findViewById(R.id.arc_menu);
		arcMenu.setspecialmenuCallBack(this);
		fragmentStack = new Stack<Fragment>();

		// setSlidingActionBarEnabled(true);
		// setSlidingActionBarEnabled(false);
		destinationPage = new DestinationPage();
		destinationPage.registerForListener(this);
		culinaryPage = new CulinaryPage();
		culinaryPage.registerForListener(this);

		initArcMenu(arcMenu, ITEM_DRAWABLES);

		textMenu1 = (TextView) findViewById(R.id.textViewMenu1);
		textMenu2 = (TextView) findViewById(R.id.textViewMenu2);
		textMenu3 = (TextView) findViewById(R.id.textViewMenu3);
		imagebackroundtransparant = (ImageView) findViewById(R.id.imagebackroundtransparant);
		textMenu1.setVisibility(View.INVISIBLE);
		textMenu2.setVisibility(View.INVISIBLE);
		textMenu3.setVisibility(View.INVISIBLE);

		if (type.equalsIgnoreCase("alldestination")) {

			fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(R.id.content_frame, destinationPage);
			fragmentStack.push(destinationPage);
			ft.commit();
		} else if (type.equalsIgnoreCase("culinary")) {
			textTile.setText("Culinary");
			fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(R.id.content_frame, culinaryPage);
			fragmentStack.push(culinaryPage);
			ft.commit();
		} else if (type.equalsIgnoreCase("detial_destination")) {
			// getSupportFragmentManager().beginTransaction()
			// .replace(R.id.content_frame, new NotificationPage())
			// .commit();

			fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(R.id.content_frame, detaiDestinationPage);
			fragmentStack.push(detaiDestinationPage);
			ft.commit();
		}

	}

	private void initArcMenu(ArcMenu menu, int[] itemDrawables) {
		final int itemCount = 3;
		for (int i = 0; i < itemCount; i++) {
			LayoutInflater layoutInflater = (LayoutInflater) DetailActivity.this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = layoutInflater.inflate(R.layout.item_menu, null);
			ImageView item = (ImageView) view.findViewById(R.id.imageItem);
			item.setImageResource(itemDrawables[i]);

			final int position = i;
			menu.addItem(view, new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(DetailActivity.this, "position:" + position,
							Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	@Override
	public void subMenuEvent(boolean event) {
		// TODO Auto-generated method stub
		Log.e("menu = ", "" + event);
		if (!event) {
			//nambah komen satubaris
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

	@Override
	public void onItemDestinationPage(int id) {
		// TODO Auto-generated method stub
		textTile.setText("Bali");
		FragmentTransaction ft = fragmentManager.beginTransaction();
		detaiDestinationPage = new DestinationDetailPage();
		detaiDestinationPage.registerForListener(this);
		ft.add(R.id.content_frame, detaiDestinationPage);
		fragmentStack.lastElement().onPause();
		ft.hide(fragmentStack.lastElement());
		fragmentStack.push(detaiDestinationPage);
		ft.commit();

	}

	@Override
	public void onItemCulinaryPage(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		Stack<Fragment> fragmStack = fragmentStack;
		if (fragmStack.size() != 1) {

			FragmentTransaction ft = fragmentManager.beginTransaction();
			fragmentStack.lastElement().onPause();
			ft.remove(fragmentStack.pop());
			fragmentStack.lastElement().onResume();
			ft.show(fragmentStack.lastElement());
			ft.commit();

		} else {
			super.onBackPressed();
		}

	}

	@Override
	public void onItemDestinationDetailPage(int id, boolean header_bukan) {
		// TODO Auto-generated method stub
		FragmentTransaction ft = fragmentManager.beginTransaction();
		if (header_bukan) {
			switch (id) {
				case R.id.btnAttraction:
					textTile.setText("Bali");

					attractionListMapPage = new AttractionListMapPage(arcMenu);
					attractionListMapPage.registerForListener(this);
					ft.add(R.id.content_frame, attractionListMapPage);
					fragmentStack.lastElement().onPause();
					ft.hide(fragmentStack.lastElement());
					fragmentStack.push(attractionListMapPage);
					ft.commit();

					break;
				//
				case R.id.btnPhotos:

					albumPage = new AlbumPage(AlbumPage.DARIALBUMPAGE);
					albumPage.registerForListener(this);
					ft.add(R.id.content_frame, albumPage);
					fragmentStack.lastElement().onPause();
					ft.hide(fragmentStack.lastElement());
					fragmentStack.push(albumPage);
					ft.commit();
					break;

				case R.id.btnContributors:

					contributorsPage = new ContributorsPage(arcMenu);
					contributorsPage.registerForListener(this);
					ft.add(R.id.content_frame, contributorsPage);
					fragmentStack.lastElement().onPause();
					ft.hide(fragmentStack.lastElement());
					fragmentStack.push(contributorsPage);
					ft.commit();
					break;


				default:
					break;
			}
		} else {
			//buat toyo
		}



	}

	@Override
	public void onItemAttractionListMapPage(int id) {
		// TODO Auto-generated method stub
		FragmentTransaction ft = fragmentManager.beginTransaction();
		detailAttraction = new DetailAttraction();

		ft.add(R.id.content_frame, detailAttraction);
		fragmentStack.lastElement().onPause();
		ft.hide(fragmentStack.lastElement());
		fragmentStack.push(detailAttraction);
		ft.commit();
	}

	@Override
	public void onItemContributorsPage(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onItemAlbumPage(int id) {

	}
}
