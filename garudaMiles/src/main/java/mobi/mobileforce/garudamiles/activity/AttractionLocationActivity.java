package mobi.mobileforce.garudamiles.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.Touch;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.page.AddAttraction;

/**
 * Created by Sulalah Rugaya on 8/10/2015.
 */
public class AttractionLocationActivity extends Activity implements AdapterView.OnItemClickListener {
    String[] items;
    TextView textTile;
    ArrayList<String> listitem;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    TextView tvCreate, textLocation;
    ImageView btnCancel, btnOkay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar_submit_attraction, null);
        View lala = mInflater.inflate(R.layout.submit_attraction, null);
        textLocation = (TextView) lala.findViewById(R.id.textLocation);
        textTile = (TextView) mCustomView.findViewById(R.id.textTitle);

        actionBar.setCustomView(mCustomView);
        ImageView btnCancel = (ImageView) mCustomView.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nilai", "Attraction Location");
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
        listView.setOnItemClickListener(this);
        editText = (EditText) findViewById(R.id.tvSearch);

        ViewGroup header = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.header_attaction_loc, listView, false);
        ViewGroup footer = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.footer_attraction_loc, listView, false);
        footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orig =  tvCreate.getText().toString();
                String updated = orig.replace("\"", "");
                Log.e("Button Clicked", "Success");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nilai", updated);
               //System.out.println(updated);
                setResult(Activity.RESULT_OK, resultIntent);
                editText.setText(updated);

            }
        });

        tvCreate = (TextView) footer.findViewById(R.id.textCreates);
        listView.addHeaderView(header, null, false);
        listView.addFooterView(footer, null, false);
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
                tvCreate.setText(Html.fromHtml("<b>" + "\"" + editText.getText().toString() + "\"" + "<b>"));
                btnOkay.setBackgroundResource(R.drawable.check);
            }

        });
    }


    void searchItem(String textToSearch) {
//        for(int i = 0; i<items.length;i++){
//            if (items[i].equalsIgnoreCase(textToSearch)){
//
//            }else{
//                listitem.remove(i);
//                if (listitem.size() == 0) {
//                }
//            }
//        }

        for (String item : items) {
            if (!item.contains(textToSearch)) {
                listitem.remove(item);
                if (listitem.size() == 0) {
                }
            }
        }

        adapter.notifyDataSetChanged();
    }


    public void initList() {
        items = new String[]{"Canada", "China", "Japan", "Indonesia", "Iran", "USA", "Germany"};
        listitem = new ArrayList<String>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.search_view, R.id.text1, listitem);
        listView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("nilai", listitem.get(position - 1));
        setResult(Activity.RESULT_OK, resultIntent);
        editText.setText(listitem.get(position - 1));
        //finish();


    }


}
