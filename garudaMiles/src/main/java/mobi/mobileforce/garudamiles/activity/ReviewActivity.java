package mobi.mobileforce.garudamiles.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mobi.mobileforce.garudamiles.R;

/**
 * Created by Sulalah Rugaya on 8/10/2015.
 */
public class ReviewActivity extends FragmentActivity {
    String[] items;
    TextView textTile;
    ArrayAdapter<String> adapter;
    TextView itemName, itemLocation;
    View view;
    private TextView textHitung;
    private EditText textDesc;
    String x, xx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar_attraction_name, null);
        textTile = (TextView) mCustomView.findViewById(R.id.textTitle);
        textTile.setText("Write A Review");
        actionBar.setCustomView(mCustomView);

        ImageView btnCancel = (ImageView) mCustomView.findViewById(R.id.btnClose);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageView btnOkay = (ImageView) mCustomView.findViewById(R.id.btnOkay);
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // actionBar.setBackgroundDrawable(new ColorDrawable(0x0065b3));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        setContentView(R.layout.item_write_review);
        itemName = (TextView) findViewById(R.id.itemName);
        itemLocation = (TextView) findViewById(R.id.itemLocation);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String string1 = extras.getString("x");
            itemName.setText(string1);
            String string2 = extras.getString("xx");
            itemLocation.setText(string2);
            Toast.makeText(this, "TEST", Toast.LENGTH_SHORT).show();
        } else {}

        textDesc = (EditText) findViewById(R.id.tvDesc);
        textHitung = (TextView) findViewById(R.id.tvHitung);
        textDesc.addTextChangedListener(mTextEditorWatcher);
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        boolean flag = false;

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            textHitung.setText(String.valueOf(s.length()));
        }

        public void afterTextChanged(Editable s) {
            if (s.length() == 500) {
                if (!flag) {
                    textDesc.setImeOptions(EditorInfo.IME_ACTION_DONE);
                    AlertDialog.Builder alert = new AlertDialog.Builder(ReviewActivity.this);
                    alert.setMessage("You Cross the limit of 500 Words! You Can't Typing Anymore.");
                    alert.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {

                                }
                            });

                    alert.show();
                }
                flag = true;
            } else {
                flag = false;
            }

        }
    };

}


