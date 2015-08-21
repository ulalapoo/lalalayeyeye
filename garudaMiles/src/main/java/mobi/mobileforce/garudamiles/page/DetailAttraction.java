package mobi.mobileforce.garudamiles.page;

import java.util.ArrayList;
import java.util.Stack;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.activity.SearchReview;
import mobi.mobileforce.garudamiles.adapter.AttractionAdapter;
import mobi.mobileforce.garudamiles.adapter.DestinationDetailAdapter;
import mobi.mobileforce.garudamiles.model.ReviewModel;
import mobi.mobileforce.garudamiles.model.TravelpediaModel;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.capricorn.ArcMenu;

public class DetailAttraction extends Fragment {

    View view;
    AttractionAdapter adapter;
    ArrayList<ReviewModel> dataArray = new ArrayList<ReviewModel>();
    int[] drawable_asset = new int[]{R.drawable.trip, R.drawable.trip2,
            R.drawable.trip, R.drawable.trip2,};
    String[] type = new String[]{"2", "2", "2", "2", "2", "2",};
    ListView listView;
    MyOnClickAttractionListMapPage listener;

    public interface MyOnClickAttractionListMapPage {
        public void onItemAttractionListMapPage(int id);
    }

    public void registerForListener(MyOnClickAttractionListMapPage listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.attraction_detail, null);
        adapter = new AttractionAdapter(getActivity(), R.layout.item_review);
        listView = (ListView) view.findViewById(R.id.listAttraction);
        ViewGroup header = (ViewGroup) inflater.inflate(
                R.layout.header_attraction, listView, false);
        ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.footer_layout,
                listView, false);
        Button btnReview = (Button) footer.findViewById(R.id.btnReview);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SearchReview.class);
                startActivity(i);
            }
        });
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);
        listView.setAdapter(adapter);
        getdata();
        return view;
    }

    ArcMenu arcMenu;
    boolean maps = true;
    ImageView imageIcon;
    ImageView btnBack;
    ImageView btnGallery;
    RelativeLayout layoutMap;
    ImageView reviewRate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActivity().getActionBar();
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar, null);
        TextView textTile = (TextView) mCustomView.findViewById(R.id.textTitle);


        imageIcon = (ImageView) mCustomView.findViewById(R.id.imageIcon);
        imageIcon.setVisibility(View.GONE);
        btnBack = (ImageView) mCustomView.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //onBackPressed();
            }
        });
        btnGallery = (ImageView) mCustomView.findViewById(R.id.picture_icon);
        textTile.setText("Bali");
        actionBar.setCustomView(mCustomView);
        //gone
        btnGallery.setVisibility(View.GONE);
        btnGallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (maps) {
                    btnGallery.setImageResource(R.drawable.pin_icon);
                    maps = false;
                    listView.setVisibility(View.VISIBLE);
                    layoutMap.setVisibility(View.GONE);
                    arcMenu.setVisibility(View.VISIBLE);
                } else {
                    btnGallery.setImageResource(R.drawable.picture_icon);
                    maps = true;
                    listView.setVisibility(View.GONE);
                    layoutMap.setVisibility(View.VISIBLE);
                    arcMenu.setVisibility(View.GONE);
                }
            }
        });

//		reviewRate = (ImageView) mCustomView.findViewById(R.id.reviewRate);
//		reviewRate.setOnClickListener(new View.OnClickListener(){
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(getActivity(), "Udah kepencet berarti", Toast.LENGTH_SHORT).show();
//
//			}
//		});

        // actionBar.setBackgroundDrawable(new ColorDrawable(0x0065b3));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
    }

    public void getdata() {
        for (int i = 0; i < drawable_asset.length; i++) {
            ReviewModel item = new ReviewModel();
            dataArray.add(item);
        }

        adapter.addAll(dataArray);
        adapter.notifyDataSetChanged();
    }
}
