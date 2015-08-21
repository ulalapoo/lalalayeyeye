package mobi.mobileforce.garudamiles.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.activity.ReviewActivity;
import mobi.mobileforce.garudamiles.model.AddAttractionNameModel;
import mobi.mobileforce.garudamiles.model.SearchReviewModel;

public class SearchReviewAdapter extends ArrayAdapter<SearchReviewModel> {

    Context context;
    int viewResources;
    RecordHolder holder;
    ArrayList<String> listitem;
    ArrayAdapter<String> adapter;
    EditText editText;
    RelativeLayout relativeLayout;
    TextView text1, text2, itemName, itemLocation;


    public SearchReviewAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.viewResources = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View row = convertView;
        SearchReviewModel model = getItem(position);

        if (row == null) {
            holder = new RecordHolder();
            row = LayoutInflater.from(context).inflate(viewResources, parent,
                    false);
            holder.text1 = (TextView) row.findViewById(R.id.text1);
            holder.text2 = (TextView) row.findViewById(R.id.text2);
            holder.relativeLayout = (RelativeLayout) row.findViewById(R.id.clickClick);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        model = getItem(position);

        holder.text1.setText(model.getText1());
        holder.text2.setText(model.getText2());
        final SearchReviewModel finalModel = model;
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Button Clicked", "Success");
                Intent i = new Intent(context, ReviewActivity.class);
                i.putExtra("x", finalModel.getText1());
                i.putExtra("xx", finalModel.getText2());
                context.startActivity(i);
            }
        });
        return row;

    }

    static class RecordHolder {
        TextView text1, text2;
        RelativeLayout relativeLayout;

    }
}
