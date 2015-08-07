package mobi.mobileforce.garudamiles.activity;

import java.util.Stack;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.page.AlbumPage;
import mobi.mobileforce.garudamiles.page.DestinationDetailPage;
import mobi.mobileforce.garudamiles.page.AlbumPage.MyOnClickAlbumPage;
import mobi.mobileforce.garudamiles.page.CameraPage;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.capricorn.ArcMenu;

public class GalleryActivity extends FragmentActivity implements
		MyOnClickAlbumPage, DestinationDetailPage.MyOnClickDestinationDetailPage {

	CameraPage cameraPage;
	AlbumPage albumPage;
	private Stack<Fragment> fragmentStack;
	private FragmentManager fragmentManager;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.testfragmentlayout);

		albumPage = new AlbumPage(13);
		albumPage.registerForListener(this);
		fragmentManager = getSupportFragmentManager();
		fragmentStack = new Stack<Fragment>();
		
		fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		ft.add(R.id.content_frame, albumPage);
		fragmentStack.push(albumPage);
		ft.commit();
		
//		getSupportFragmentManager().beginTransaction()
//				.replace(R.id.content_frame, albumPage).commit();

		ActionBar actionBar = getActionBar();
		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(
				R.layout.custom_action_bar_post_review, null);

		actionBar.setCustomView(mCustomView);
		ImageView btnClose = (ImageView) mCustomView
				.findViewById(R.id.btnClose);
		btnClose.setOnClickListener(new View.OnClickListener() {

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

	}

	@Override
	public void onItemAlbumPage(int id) {
		// TODO Auto-generated method stub

		FragmentTransaction ft = fragmentManager.beginTransaction();
		cameraPage = new CameraPage();
		ft.add(R.id.content_frame, cameraPage);
		fragmentStack.lastElement().onPause();
		ft.hide(fragmentStack.lastElement());
		fragmentStack.push(cameraPage);
		ft.commit();

	}


	@Override
	public void onItemDestinationDetailPage(int id, boolean header_bukan) {

	}

}
