package mobi.mobileforce.garudamiles.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.SearchReviewAdapter;
import mobi.mobileforce.garudamiles.model.SearchReviewModel;

/**
 * Created by Sulalah Rugaya on 8/10/2015.
 */
public class SearchReview extends Activity{
    private String[] items;
    TextView textTile;
    ArrayList<String> listitem;
    ArrayAdapter<String> adapter1;
    ListView listView;
    EditText editText;
    TextView itemName, itemLocation, tvCreate;
    ImageView btnCancel, btnOkay;
    RelativeLayout relativeLayout;
    SearchReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar_submit_attraction, null);
        textTile = (TextView) mCustomView.findViewById(R.id.textTitle);
        textTile.setText("Write A Review");

        actionBar.setCustomView(mCustomView);
        ImageView btnCancel = (ImageView) mCustomView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final ImageView btnOkay = (ImageView) mCustomView.findViewById(R.id.btnOkay);
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchReview.this, ReviewActivity.class);
                startActivity(i);
            }
        });


        // actionBar.setBackgroundDrawable(new ColorDrawable(0x0065b3));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);


        setContentView(R.layout.input_attraction_name_page);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new SearchReviewAdapter(this, R.layout.item_search_review);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("TES = ", "tes");
            }
        });


        ViewGroup header = (ViewGroup) LayoutInflater.from(this).inflate(
                R.layout.header_attaction_loc, listView, false);
        listView.addHeaderView(header, null, false);
        listView.setAdapter(adapter);
        editText = (EditText) findViewById(R.id.tvSearch);
        relativeLayout = (RelativeLayout) findViewById(R.id.clickClick);
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
               // tvCreate.setText(Html.fromHtml("<b>" + "\"" + editText.getText().toString() + "\"" + "<b>"));
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
        adapter1 = new ArrayAdapter<String>(this, R.layout.item_search_review, R.id.text1, listitem);
        listView.setAdapter(adapter);
    }

    public void getData() {
        ArrayList<SearchReviewModel> dataArray = new ArrayList<SearchReviewModel>();
        for (int i = 0; i < items.length; i++) {
            SearchReviewModel model = new SearchReviewModel();
            model.setText1(items[i]);
            model.setText2("Jakarta");
            dataArray.add(model);
        }

        adapter.addAll(dataArray);
        adapter.notifyDataSetChanged();
    }
}
