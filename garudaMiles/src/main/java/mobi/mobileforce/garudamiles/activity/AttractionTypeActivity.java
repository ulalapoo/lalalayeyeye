package mobi.mobileforce.garudamiles.activity;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.NavDrawerListAdapter;
import mobi.mobileforce.garudamiles.model.MenuModel;

public class AttractionTypeActivity extends FragmentActivity {
    RelativeLayout customLinearLayout = null;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private ArrayList<MenuModel> navDrawerItems;
    private String[] menu_title = new String[]{"", "Accomodation",
            "Sightseeing", "Sport", "Lifestyle", "Culinary", "Shopping",
            "Events"};

    private int[] menu_icon = new int[]{0, R.drawable.btn_travelopedia,
            R.drawable.btn_discover, R.drawable.btn_me,
            R.drawable.btn_edit_profile, R.drawable.btn_setting,
            R.drawable.btn_sign_out, R.drawable.btn_about};

    //    private int[] radio_btn = new int[]{0, R.drawable.circle_blankk,
//            R.drawable.circle_checked, R.drawable.circle_checked,
//            R.drawable.circle_checked, R.drawable.circle_checked,
//            R.drawable.circle_checked, R.drawable.circle_checked};
    private NavDrawerListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the Above View
        setContentView(R.layout.content_frame);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);


        navDrawerItems = new ArrayList<MenuModel>();
        for (int i = 0; i < 8; i++) {
            MenuModel modelItem = new MenuModel();
            modelItem.setMenuTitle(menu_title[i]);
            modelItem.setRes(menu_icon[i]);
            // modelItem.setRes(radio_btn[i]);

            if (i == 0) {
                modelItem.setTypeItem(true);
            } else {
                modelItem.setTypeItem(false);
            }
            navDrawerItems.add(modelItem);
        }

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // getActionBar().setDisplayHomeAsUpEnabled(true);
        // getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.header_menu, // nav menu toggle icon
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


        //img.setImageBitmap(getCroppedBitmap(icon));
        ActionBar actionBar = getActionBar();

        actionBar.setDisplayUseLogoEnabled(false);
        //Drawable d = new BitmapDrawable(this.getResources(),
        //		getCroppedBitmap(icon));
        //actionBar.setIcon(d);
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFF0065b3));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
    }


   // @Override
    public void subMenuEvent(boolean event) {
//        // TODO Auto-generated method stub
//        Log.e("menu = ", "" + event);
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

//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        // Sync the toggle state after onRestoreInstanceState has occurred.
//        mDrawerToggle.syncState();
//    }
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        // Pass any configuration change to the drawer toggls
//        mDrawerToggle.onConfigurationChanged(newConfig);
//    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // if nav drawer is opened, hide the action items
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        // menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }


    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();

    }
}
