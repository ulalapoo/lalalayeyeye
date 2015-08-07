package mobi.mobileforce.garudamiles;

import java.util.ArrayList;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import mobi.mobileforce.garudamiles.constant.Constant;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

public class SampleApplication extends Application {
	static SampleApplication instance;
	public static Context gContext;

	SharedPreferences preferences;
	static boolean newNotification = false;
	static String nextUrlNotification;

	public static String getNextUrlNotification() {
		return nextUrlNotification;
	}

	public static void setNextUrlNotification(String nextUrlNotification) {
		SampleApplication.nextUrlNotification = nextUrlNotification;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		gContext = this;
		instance = this;
		preferences = getSharedPreferences(Constant._USER_CREDIT, MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();

		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.threadPoolSize(3)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCacheSize(4000000)
				// 1.5 Mb
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.enableLogging() // Not necessary in common
				.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);

	}

	public static SampleApplication getApplication(Context context) {
		return (SampleApplication) context.getApplicationContext();
	}

	public static SampleApplication getInstance() {
		return instance;
	}

}
