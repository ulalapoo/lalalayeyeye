package mobi.mobileforce.garudamiles.page;

import java.util.ArrayList;

import com.capricorn.ArcMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.DestinationDetailAdapter;
import mobi.mobileforce.garudamiles.adapter.DistinationAdapter;
import mobi.mobileforce.garudamiles.model.AttractionModel;
import mobi.mobileforce.garudamiles.model.DiscoverModel;
import mobi.mobileforce.garudamiles.model.TravelpediaModel;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AttractionListMapPage extends Fragment implements
		OnMarkerClickListener, OnItemClickListener {

	View view;
	private GoogleMap googleMap;
	LatLng[] latLong = new LatLng[10];
	ArrayList<AttractionModel> dataArray = new ArrayList<AttractionModel>();
	ArcMenu arcMenu;
	ImageView imageAttraction;
	TextView titleTrip;
	int[] res = new int[] { R.drawable.trip, R.drawable.trip2, R.drawable.bali };
	boolean maps = true;
	MyOnClickAttractionListMapPage listener;

	DestinationDetailAdapter adapter;
	ArrayList<TravelpediaModel> dataArrayList = new ArrayList<TravelpediaModel>();
	int[] drawable_asset = new int[] { R.drawable.trip, R.drawable.trip2,
			R.drawable.trip, R.drawable.trip2, };
	String[] type = new String[] { "2", "2", "2", "2", "2", "2", };
	private ListView listView;

	ImageView imageIcon;
	ImageView btnBack;
	ImageView btnGallery;
	RelativeLayout layoutMap;

	public interface MyOnClickAttractionListMapPage {
		public void onItemAttractionListMapPage(int id);
	}

	public void registerForListener(MyOnClickAttractionListMapPage listener) {
		this.listener = listener;
	}

	public AttractionListMapPage(ArcMenu arcMenu) {
		// TODO Auto-generated constructor stub
		this.arcMenu = arcMenu;
		this.arcMenu.setVisibility(View.GONE);
	}

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
		btnGallery = (ImageView) mCustomView.findViewById(R.id.picture_icon);
		textTile.setText("Bali");
		actionBar.setCustomView(mCustomView);
		btnGallery.setVisibility(View.VISIBLE);
		btnBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// onBackPressed();
			}
		});
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

		// actionBar.setBackgroundDrawable(new ColorDrawable(0x0065b3));
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.list_attraction_type_map, null);
		imageAttraction = (ImageView) view.findViewById(R.id.imageAttraction);
		titleTrip = (TextView) view.findViewById(R.id.titleTrip);
		listView = (ListView) view.findViewById(R.id.listDestination);
		layoutMap = (RelativeLayout) view.findViewById(R.id.layoutMap);
		adapter = new DestinationDetailAdapter(getActivity(),
				R.layout.item_list_travelpedia_type2);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		getData();

		try {
			initilizeMap();
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			googleMap.setOnMarkerClickListener(this);
			// Enable / Disable zooming controls
			googleMap.getUiSettings().setZoomControlsEnabled(false);

			// Enable / Disable my location button
			googleMap.getUiSettings().setMyLocationButtonEnabled(true);

			// Enable / Disable Compass icon
			googleMap.getUiSettings().setCompassEnabled(true);

			// Enable / Disable Rotate gesture
			googleMap.getUiSettings().setRotateGesturesEnabled(true);

			// Enable / Disable zooming functionality
			googleMap.getUiSettings().setZoomGesturesEnabled(true);

			latLong[0] = new LatLng(-8.82917, 115.084928);
			latLong[1] = new LatLng(-8.828643, 115.086730);
			latLong[2] = new LatLng(-8.828279, 115.089340);

			// lets place some 10 random markers
			for (int i = 0; i < 3; i++) {

				// Adding a marker
				MarkerOptions marker = new MarkerOptions().position(latLong[i])
						.title("Hello Maps " + i);
				AttractionModel model = new AttractionModel();
				model.setCategory("Bali");
				model.setRating(4);
				model.setTitle("Ulawatu Kecak Dance " + i);
				model.setResource(res[i]);
				// changing marker color
				if (i == 0)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				if (i == 1)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				if (i == 2)
					marker.icon(BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_RED));

				googleMap.addMarker(marker);

				CameraPosition cameraPosition = new CameraPosition.Builder()
						.target(latLong[0]).zoom(17).build();

				googleMap.animateCamera(CameraUpdateFactory
						.newCameraPosition(cameraPosition));
				dataArray.add(model);
			}

			// MarkerOptions marker = new MarkerOptions().position(
			// new LatLng(latitude, longitude)).title("Tes");
			//
			// googleMap.addMarker(marker);
			// CameraPosition cameraPosition = new CameraPosition.Builder()
			// .target(new LatLng(latitude, longitude)).zoom(15).build();
			//
			// googleMap.animateCamera(CameraUpdateFactory
			// .newCameraPosition(cameraPosition));

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("msg  = ", e.getMessage());
		}

		view.setFocusableInTouchMode(true);
		view.requestFocus();
		view.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {

						return true;
					}
				}
				return false;
			}
		});

		return view;
	}

	private void initilizeMap() {

		// if (googleMap != null) {
		// ViewGroup parent = (ViewGroup) googleMap.getParent();
		// if (parent != null)
		// parent.removeView(googleMap);
		// }
		// try {
		//
		// } catch (InflateException e) {
		// /* map is already there, just return view as it is */
		// }
		if (googleMap == null) {
			googleMap = ((SupportMapFragment) getChildFragmentManager()
					.findFragmentById(R.id.map)).getMap();
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getActivity(), "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void getData() {
		for (int i = 0; i < drawable_asset.length; i++) {
			TravelpediaModel item = new TravelpediaModel();
			item.setDefault_drawable(drawable_asset[i]);
			item.setType(type[i]);
			dataArrayList.add(item);
		}

		adapter.addAll(dataArrayList);
		adapter.notifyDataSetChanged();

	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		// TODO Auto-generated method stub
		Log.e("Marker = ", arg0.getId());

		titleTrip.setText(dataArray.get(
				Integer.parseInt(arg0.getId().substring(1,
						arg0.getId().length()))).getTitle());
		imageAttraction.setImageResource(dataArray.get(
				Integer.parseInt(arg0.getId().substring(1,
						arg0.getId().length()))).getResource());
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		listener.onItemAttractionListMapPage(0);

	}
}
