package mobi.mobileforce.garudamiles.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mobi.mobileforce.garudamiles.R;

/**
 * Created by Sulalah Rugaya on 8/20/2015.
 */
public class PublishActivity extends Activity {
    View view;
    TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mView = mInflater.inflate(R.layout.custom_action_bar_attraction_name, null);
        textTitle = (TextView) mView.findViewById(R.id.textTitle);
        textTitle.setText("Publish Attraction");
        actionBar.setCustomView(mView);

        ImageView btnCancel = (ImageView) mView.findViewById(R.id.btnClose);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView btnOkay = (ImageView) mView.findViewById(R.id.btnOkay);
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PublishActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        setContentView(R.layout.item_publish);

    }
}
