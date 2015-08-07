package mobi.mobileforce.garudamiles.page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.adapter.AttractionAdapter;
import mobi.mobileforce.garudamiles.model.AttractionModel;
import mobi.mobileforce.garudamiles.model.DraftNewAttractionModel;
import mobi.mobileforce.garudamiles.model.ReviewModel;

/**
 * Created by Sulalah Rugaya on 8/6/2015.
 */
public class DraftReviewPage extends Fragment{
    View view;
    ListView listView;
    ArrayList<ReviewModel> dataArray = new ArrayList<ReviewModel>();
    AttractionAdapter myAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.draft_review_page, null);

        myAdapter = new AttractionAdapter(getActivity(), R.layout.item_review);

        listView = (ListView) view.findViewById(R.id.listReviews);
        listView.setAdapter(myAdapter);
       // listvView.setOnItemClickListener(this);

        generateData();

        return view;
    }

    void generateData(){
//        dataArray.clear();
//        myAdapter.clear();

//        String[] titles = {"Tempat yang Romantis"};
//        String[] names = {"by Sulalah Rugaya"};
//        String[] descs={"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&apos;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."};
//        Integer[] photos={R.drawable.discover_img_1};
        for (int i = 0; i<5; i++){
//            AttractionModel model = new AttractionModel();
//            model.setTitle(titles[i]);
//            model.setCategory(names[i]);
//            model.setReviews(descs[i]);
//            model.setPhotos(photos[i]);
//            dataArray.add(model);

            ReviewModel item = new ReviewModel();
            dataArray.add(item);
        }

        myAdapter.addAll(dataArray);
        myAdapter.notifyDataSetChanged();
    }
}
