package mobi.mobileforce.garudamiles.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.activity.ReviewActivity;
import mobi.mobileforce.garudamiles.model.AddAttractionNameModel;
import mobi.mobileforce.garudamiles.model.NotificationModel;
import mobi.mobileforce.garudamiles.page.AddAttraction;
import mobi.mobileforce.garudamiles.page.AddAttractionName;

public class AddAttractionNameAdapter extends ArrayAdapter<AddAttractionNameModel> {

    Context context;
    int viewResources;
    RecordHolder holder;
    ArrayList<String> listitem;
    ArrayAdapter<String> adapter;
    EditText editText;
    TextView text1, text2, itemName, itemLocation;


    public AddAttractionNameAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.viewResources = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View row = convertView;
        AddAttractionNameModel model = getItem(position);

        if (row == null) {
            holder = new RecordHolder();
            row = LayoutInflater.from(context).inflate(viewResources, parent,
                    false);
            holder.text1 = (TextView) row.findViewById(R.id.text1);
            holder.text2 = (TextView) row.findViewById(R.id.text2);
          //  holder.itemName = (TextView) row.findViewById(R.id.itemName);
            //holder.itemLocation = (TextView) row.findViewById(R.id.itemLocation);
            holder.btnKlik = (Button) row.findViewById(R.id.btnKlik);
            holder.createNew = (Button) row.findViewById(R.id.createNew);


            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        model = getItem(position);

        holder.text1.setText(model.getText1());
        holder.text2.setText(model.getText2());
        final AddAttractionNameModel finalModel = model;
        holder.btnKlik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Button Clicked", "Success");
                Intent i = new Intent(context, ReviewActivity.class);
                i.putExtra("x", finalModel.getText1());
                i.putExtra("xx", finalModel.getText2());
                context.startActivity(i);
            }
        });

//        holder.createNew.setOnClickListener(new View.OT1nClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("Button Clicked", "Success");
////                Intent resultIntent = new Intent();
////                resultIntent.putExtra("nilai", listitem.get(position - 1));
////                setResult(Activity.RESULT_OK, resultIntent);
////                editText.setText(listitem.get(position - 1));
////                //finish();
//
//            }
//        });

        return row;

    }

    private void setResult(int resultOk, Intent resultIntent) {
    }

    static class RecordHolder {
        Button btnKlik, createNew, itemName, itemLocation;
        TextView text1, text2;

    }
}
