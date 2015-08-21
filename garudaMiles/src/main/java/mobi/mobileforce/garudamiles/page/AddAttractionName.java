package mobi.mobileforce.garudamiles.page;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.internal.it;

import java.util.ArrayList;
import java.util.Arrays;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.AddAttractionNameAdapter;
import mobi.mobileforce.garudamiles.model.AddAttractionNameModel;

/**
 * Created by Sulalah Rugaya on 8/10/2015.
 */
public class AddAttractionName extends Activity {
    TextView textTile;
    ArrayList<String> listitem;
    ArrayAdapter<String> adapter1;
    ListView listView;
    EditText editText;
    TextView itemName, itemLocation, tvCreate;
    ImageView btnCancel, btnOkay;
    RelativeLayout relativeLayout;
    private String[] items;
    AddAttractionNameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar_attraction_name, null);
        textTile = (TextView) mCustomView.findViewById(R.id.textTitle);
        actionBar.setCustomView(mCustomView);
        ImageView btnCancel = (ImageView) mCustomView.findViewById(R.id.btnClose);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nilai1", "Attraction Name");
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        final ImageView btnOkay = (ImageView) mCustomView.findViewById(R.id.btnOkay);

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

        setContentView(R.layout.input_attraction_name_page);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new AddAttractionNameAdapter(this, R.layout.input_attraction_name_page2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TES = ", "tes");
            }
        });
        ViewGroup header = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.input_attraction_name_page4, listView, false);
        ViewGroup footer = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.input_attraction_name_page3, listView, false);

        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orig =  tvCreate.getText().toString();
                String updated = orig.replace("\"", "");
                Log.e("Button Clicked", "Success");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nilai1", updated);
                setResult(Activity.RESULT_OK, resultIntent);
                editText.setText(updated);
                //finish();

            }
        });
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);
        listView.setAdapter(adapter);
        editText = (EditText) findViewById(R.id.tvSearch);
        tvCreate = (TextView) footer.findViewById(R.id.textCreate);
        relativeLayout = (RelativeLayout) findViewById(R.id.btnSearch);
        initList();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().equals("")) {
                    initList();
                } else {
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Log.e("coba search", "ini before");
                tvCreate.setText("\"" + editText.getText().toString() + "\"");
                btnOkay.setBackgroundResource(R.drawable.check);
            }
        });
        getData();
    }
    void searchItem(String textToSearch) {
        for (String item : items) {
            if (!item.contains(textToSearch)) {

                listitem.remove(item);

                if (listitem.size() == 0) {


                }

            }
        }
        adapter1.notifyDataSetChanged();
    }
    public void initList() {
        items = new String[]{"Canada", "China", "Japan", "Indonesia", "Iran", "USA", "Germany"};
        listitem = new ArrayList<String>(Arrays.asList(items));
        adapter1 = new ArrayAdapter<String>(this, R.layout.input_attraction_name_page2, R.id.text1, listitem);
        listView.setAdapter(adapter);
    }

    public void getData() {
        ArrayList<AddAttractionNameModel> dataArray = new ArrayList<AddAttractionNameModel>();
        for (int i = 0; i < items.length; i++) {
            AddAttractionNameModel model = new AddAttractionNameModel();
            model.setText1(items[i]);
            model.setText2("Jakarta");
            dataArray.add(model);
        }

        adapter.addAll(dataArray);
        adapter.notifyDataSetChanged();
    }


}
