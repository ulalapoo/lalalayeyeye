package mobi.mobileforce.garudamiles.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import mobi.mobileforce.garudamiles.page.DiscoverPage;
import mobi.mobileforce.garudamiles.page.DraftNewAttractionPage;
import mobi.mobileforce.garudamiles.page.DraftReviewPage;
import mobi.mobileforce.garudamiles.page.MePage;
import mobi.mobileforce.garudamiles.page.TravelopediaPage;

public class TabsPagerDraftAdapter extends FragmentPagerAdapter {

	public TabsPagerDraftAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			// Top Rated fragment activity
			return new DraftNewAttractionPage();
		case 1:
			// Games fragment activity
			return new DraftReviewPage();

		}

		return null;
	}

	@Override
	public int getCount() {
		// get item count - equal to number of tabs
		return 2;
	}

}
