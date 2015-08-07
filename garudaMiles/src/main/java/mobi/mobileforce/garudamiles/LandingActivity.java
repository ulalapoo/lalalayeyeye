package mobi.mobileforce.garudamiles;

import mobi.mobileforce.garudamiles.activity.DetailActivity2;
import mobi.mobileforce.garudamiles.activity.HomeActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class LandingActivity extends Activity implements OnClickListener {
	ImageButton buttonLoginGarudaMiles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_landing);
		buttonLoginGarudaMiles = (ImageButton) findViewById(R.id.buttonLoginGarudaMiles);
		buttonLoginGarudaMiles.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.landing, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonLoginGarudaMiles:
			// Intent i = new Intent(LandingActivity.this,
			// GalleryActivity.class);
			Intent i = new Intent(LandingActivity.this, HomeActivity.class);
			startActivity(i);
			this.finish();
			break;

		default:
			break;
		}
	}
}
