package mobi.mobileforce.garudamiles.adapter;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.DestinationDetailAdapter.RecordHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class ImageAdapter extends BaseAdapter {

	ArrayList<String> mList;
	LayoutInflater mInflater;
	Context mContext;
	SparseBooleanArray mSparseBooleanArray;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;
	private int mItemHeight = 0;
	private int mNumColumns = 0;
	private RelativeLayout.LayoutParams mImageViewLayoutParams;
	RecordHolder holder = null;
	int darihalaman;
	int DARIALBUMFOTO = 0;

	public ImageAdapter(Context context, ArrayList<String> imageList, int darihalaman) {
		// TODO Auto-generated constructor stub
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
		mSparseBooleanArray = new SparseBooleanArray();
		mList = new ArrayList<String>();
		this.mList = imageList;
		mImageViewLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.darihalaman = darihalaman;
	}

	public ArrayList<String> getCheckedItems() {
		ArrayList<String> mTempArry = new ArrayList<String>();

		for (int i = 0; i < mList.size(); i++) {
			if (mSparseBooleanArray.get(i)) {
				mTempArry.add(mList.get(i));
			}
		}

		return mTempArry;
	}

	// set numcols
	public void setNumColumns(int numColumns) {
		mNumColumns = numColumns;
	}

	public int getNumColumns() {
		return mNumColumns;
	}

	// set photo item height
	public void setItemHeight(int height) {
		if (height == mItemHeight) {
			return;
		}
		mItemHeight = height;
		mImageViewLayoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, mItemHeight);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.stub_image)
				.showImageForEmptyUri(R.drawable.image_for_empty_url)
				.cacheInMemory().cacheOnDisc().build();
		if (row == null) {
			holder = new RecordHolder();
			row = mInflater.inflate(R.layout.row_multiphoto_item, null);
			holder.mCheckBox = (CheckBox) row.findViewById(R.id.checkBox1);
			holder.imageView = (ImageView) row.findViewById(R.id.imageView1);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		holder.imageView.setLayoutParams(mImageViewLayoutParams);
		// Check the height matches our calculated column width
		if (holder.imageView.getLayoutParams().height != mItemHeight) {
			holder.imageView.setLayoutParams(mImageViewLayoutParams);
		}

		imageLoader.displayImage("file://" + mList.get(position),
				holder.imageView, options, new SimpleImageLoadingListener() {
					@Override
					public void onLoadingComplete(Bitmap loadedImage) {
						Animation anim = AnimationUtils.loadAnimation(mContext,
								R.anim.fade_in);
						holder.imageView.setAnimation(anim);
						anim.start();
					}
				});

		// ini adalah kondisi untuk menghilangkan tanda checked ketika foto di klik
		if (darihalaman == DARIALBUMFOTO) {
			holder.mCheckBox.setVisibility(View.GONE);

		} else {
			holder.mCheckBox.setVisibility(View.VISIBLE);

		}

			holder.mCheckBox.setTag(position);
			holder.mCheckBox.setChecked(mSparseBooleanArray.get(position));
			holder.mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);


		return row;
	}

	OnCheckedChangeListener mCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);
		}
	};

	static class RecordHolder {
		ImageView imageView;
		CheckBox mCheckBox;
	}
}
