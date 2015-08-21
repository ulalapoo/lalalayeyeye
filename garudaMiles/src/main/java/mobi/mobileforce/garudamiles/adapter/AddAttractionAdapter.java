package mobi.mobileforce.garudamiles.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.model.AddAttactionModel;

/**
 * Created by Sulalah Rugaya on 8/11/2015.
 */
public class AddAttractionAdapter extends ArrayAdapter<AddAttactionModel> {
    Context context;
    int viewResources;
    RecordHolder holder;

    public AddAttractionAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.context = context;
        this.viewResources = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AddAttactionModel model = getItem(position);

        if (row == null) {
            holder = new RecordHolder();
            row = LayoutInflater.from(context).inflate(viewResources, parent,
                    false);
            holder.btnAttname = (LinearLayout) row.findViewById(R.id.btnAttname);
            holder.btnAtttype = (LinearLayout) row.findViewById(R.id.btnAtttype);
            holder.btnAttlocation = (LinearLayout) row.findViewById(R.id.btnAttlocation);


            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        return row;

    }
    static class RecordHolder {
        LinearLayout btnAttname, btnAtttype, btnAttlocation;

    }
}
