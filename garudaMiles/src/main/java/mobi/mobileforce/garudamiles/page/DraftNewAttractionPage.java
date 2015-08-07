package mobi.mobileforce.garudamiles.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.DraftNewAttractionAdapter;
import mobi.mobileforce.garudamiles.model.DraftNewAttractionModel;

/**
 * Created by Sulalah Rugaya on 8/6/2015.
 */
public class DraftNewAttractionPage extends Fragment implements AdapterView.OnItemClickListener{

    View view;
    ListView listView;
    ArrayList<DraftNewAttractionModel> dataArray = new ArrayList<DraftNewAttractionModel>();
    DraftNewAttractionAdapter myAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.draft_newattraction_page, null);

        myAdapter = new DraftNewAttractionAdapter(getActivity(), R.layout.item_draft_newattraction);

        listView = (ListView) view.findViewById(R.id.listDraftAttraction);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
        generateData();
        return view;
    }

    void generateData(){
//        dataArray.clear();
//        myAdapter.clear();

        String[] titles = {"Taman Nasional Baluran", "Raja Ampat", "Bali Zoo", "Monumen Nasional", "Batu Night Festival"};
        String[] descs = {"Jawa Timur", "Papua Barat", "Bali", "Jakarta Pusat", "Malang"};
        for (int i = 0; i < titles.length; i++){
            DraftNewAttractionModel model = new DraftNewAttractionModel();
            model.setTitle(titles[i]);
            model.setDesc(descs[i]);
            dataArray.add(model);
        }
        myAdapter.addAll(dataArray);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //String bebas = parent.;
        Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show();
    }
}
