package mobi.mobileforce.garudamiles.page;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.TabsPagerAdapter;
import mobi.mobileforce.garudamiles.adapter.TabsPagerDraftAdapter;

/**
 * Created by Sulalah Rugaya on 8/6/2015.
 */
public class DraftPage extends android.support.v4.app.Fragment implements View.OnClickListener {

    View view;
    ViewPager viewPager;
    TabsPagerDraftAdapter mAdapter;
    TextView btnDraft, btnReview;
    View lineDraft, lineReview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.draft_page, null);
        btnDraft = (TextView) view.findViewById(R.id.btnDraft);
        btnReview = (TextView) view.findViewById(R.id.btnReview);
        btnDraft.setOnClickListener(this);
        btnReview.setOnClickListener(this);

        lineDraft = (View) view.findViewById(R.id.lineDraft);
        lineReview = (View) view.findViewById(R.id.lineReview);

        viewPager = (ViewPager) view.findViewById(R.id.pager);
        mAdapter = new TabsPagerDraftAdapter(getFragmentManager());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                Log.e("position = ", "" + position);
                changeNavigationTab(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(mAdapter);
        return view;
    }

    public void changeNavigationTab(int position) {
            //kondisi untuk menu ketika di swipe
            switch (position) {
            case 0:
                btnDraft.setTextColor(0xFF0065b3);
                btnReview.setTextColor(0xFFaaaaaa);
                lineDraft.setBackgroundColor(0xFF0065b3);
                lineReview.setBackgroundColor(0xFFaaaaaa);
                break;
            case 1:
                btnDraft.setTextColor(0xFFaaaaaa);
                btnReview.setTextColor(0xFF0065b3);
                lineDraft.setBackgroundColor(0xFFaaaaaa);
                lineReview.setBackgroundColor(0xFF0065b3);
                break;

            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDraft:
                changeNavigationTab(0);
                viewPager.setCurrentItem(0);
                break;
            case R.id.btnReview:
                changeNavigationTab(1);
                viewPager.setCurrentItem(1);
                break;

            default:
                break;
        }
    }
}