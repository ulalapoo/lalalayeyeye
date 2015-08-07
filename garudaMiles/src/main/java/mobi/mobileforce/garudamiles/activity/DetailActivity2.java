package mobi.mobileforce.garudamiles.activity;

import java.util.Stack;

import com.capricorn.ArcMenu;
import com.capricorn.ArcMenu.specialmenuCallBack;
import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.page.AlbumPage;
import mobi.mobileforce.garudamiles.page.CulinaryPage;
import mobi.mobileforce.garudamiles.page.CulinaryPage.MyOnClickCulinaryPage;
import mobi.mobileforce.garudamiles.page.DestinationDetailPage;
import mobi.mobileforce.garudamiles.page.DestinationPage;
import mobi.mobileforce.garudamiles.page.DestinationPage.MyOnClickDestinationPage;
import mobi.mobileforce.garudamiles.page.DetailAttraction;
import mobi.mobileforce.garudamiles.page.DetailDiscover;
import mobi.mobileforce.garudamiles.page.DiscoverPage;
import mobi.mobileforce.garudamiles.page.DraftPage;
import mobi.mobileforce.garudamiles.page.HomePage;
import mobi.mobileforce.garudamiles.page.NotificationPage;
import mobi.mobileforce.garudamiles.page.NotificationPage.MyOnClickNotificationPage;
import android.app.ActionBar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class DetailActivity2 extends FragmentActivity implements
		MyOnClickDestinationPage, MyOnClickCulinaryPage,
		MyOnClickNotificationPage {

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
	DestinationPage destinationPage;
	CulinaryPage culinaryPage;
	NotificationPage notificationPage;
	DraftPage draftPage;
	AlbumPage albumPage;

	private DestinationDetailPage detaiDestinationPage;
	private DetailDiscover detailDiscover;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.detail_frame2);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			type = extras.getString("type", "");
		}

		fragmentStack = new Stack<Fragment>();
		destinationPage = new DestinationPage();
		destinationPage.registerForListener(this);
		culinaryPage = new CulinaryPage();
		culinaryPage.registerForListener(this);
		detaiDestinationPage = new DestinationDetailPage();
		notificationPage = new NotificationPage();
		notificationPage.registerForListener(this);
		draftPage = new DraftPage();
		//draftPage.registerForListener(this);



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

		if (type.equalsIgnoreCase("alldestination")) {
			// getSupportFragmentManager().beginTransaction()
			// .replace(R.id.content_frame, new DestinationPage())
			// .commit();
			textTile.setText("All Destination");

			fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(R.id.content_frame, destinationPage);
			fragmentStack.push(destinationPage);
			ft.commit();

		} else if (type.equalsIgnoreCase("culinary")) {
			// getSupportFragmentManager().beginTransaction()
			// .replace(R.id.content_frame, new CulinaryPage()).commit();
			textTile.setText("Culinary");
			fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(R.id.content_frame, culinaryPage);
			fragmentStack.push(culinaryPage);
			ft.commit();
		} else if (type.equalsIgnoreCase("notification")) {
			// getSupportFragmentManager().beginTransaction()
			// .replace(R.id.content_frame, new NotificationPage())
			// .commit();
			textTile.setText("Notification");
			fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(R.id.content_frame, notificationPage);
			fragmentStack.push(notificationPage);
			ft.commit();
		} else if (type.equalsIgnoreCase("detail_destination")) {
			// getSupportFragmentManager().beginTransaction()
			// .replace(R.id.content_frame, new NotificationPage())
			// .commit();
			textTile.setText("Bali");
			fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(R.id.content_frame, detaiDestinationPage);
			fragmentStack.push(detaiDestinationPage);
			ft.commit();
		} else if (type.equalsIgnoreCase("draft")) {
			// getSupportFragmentManager().beginTransaction()
			// .replace(R.id.content_frame, new NotificationPage())
			// .commit();
			textTile.setText("Draft");
			Toast.makeText(this, "INI DRAFT!", Toast.LENGTH_LONG).show();
			fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(R.id.content_frame, draftPage);
			fragmentStack.push(draftPage);
			ft.commit();
		} else if (type.equalsIgnoreCase("photoalbum")) {
			// getSupportFragmentManager().beginTransaction()
			// .replace(R.id.content_frame, new NotificationPage())
			// .commit();
			albumPage = new AlbumPage(AlbumPage.DARIALBUMPAGE);
			//albumPage.registerForListener(this);
			Toast.makeText(this, "NAH INI PHOTO ALBUM!", Toast.LENGTH_LONG).show();

			textTile.setText("Photo Album");
			fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.add(R.id.content_frame, albumPage);
			fragmentStack.push(albumPage);
			ft.commit();
		}

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
	public void onItemNotificationPage(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemCulinaryPage(int id) {
		// TODO Auto-generated method stub
		FragmentTransaction ft = fragmentManager.beginTransaction();
		detailDiscover = new DetailDiscover();
		ft.add(R.id.content_frame, detailDiscover);
		fragmentStack.lastElement().onPause();
		ft.hide(fragmentStack.lastElement());
		fragmentStack.push(detailDiscover);
		ft.commit();
	}

	@Override
	public void onItemDestinationPage(int id) {
		// TODO Auto-generated method stub

	}

}
