package mobi.mobileforce.garudamiles.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.model.DraftNewAttractionModel;

/**
 * Created by Sulalah Rugaya on 8/6/2015.
 */
public class DraftNewAttractionAdapter extends ArrayAdapter<DraftNewAttractionModel> {

    Context context;
    int resourceID;
    RecordHolder holder = null;
    DraftNewAttractionModel model;

    public DraftNewAttractionAdapter(Context context, int resourceID) {
        super(context, resourceID);
        this.context = context;
        this.resourceID = resourceID;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View row = convertView;
        if (row == null) {
            row = LayoutInflater.from(context).inflate(R.layout.item_draft_newattraction, parent, false);
            holder = new RecordHolder();
            holder.tvTitle = (TextView) row.findViewById(R.id.tvTitle);
            holder.tvDesc = (TextView) row.findViewById(R.id.tvDesc);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }
        model = getItem(position);
        holder.tvTitle.setText(model.getTitle());
        holder.tvDesc .setText(model.getDesc());

        return row;

    }

    static class RecordHolder{
        TextView tvTitle, tvDesc;
    }


}
