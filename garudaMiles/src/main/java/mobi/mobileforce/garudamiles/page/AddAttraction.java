package mobi.mobileforce.garudamiles.page;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import mobi.mobileforce.garudamiles.R;
import mobi.mobileforce.garudamiles.activity.AttractionLocationActivity;
import mobi.mobileforce.garudamiles.activity.AttractionTypeActivity;
import mobi.mobileforce.garudamiles.activity.DetailActivity2;
import mobi.mobileforce.garudamiles.activity.PublishActivity;
import mobi.mobileforce.garudamiles.activity.ReviewActivity;
import mobi.mobileforce.garudamiles.activity.SearchReview;

/**
 * Created by Sulalah Rugaya on 8/9/2015.
 */
public class AddAttraction extends Fragment implements View.OnClickListener {
    private static final String MY_CUSTOM_FRAGMENT_KEY = "MY_CUSTOM";
    View view;
    LinearLayout btnAttname, btnAtttype, btnAttlocation, btnDiscard, btnSave, btnClose;
    MyOnClickAddAttraction listener;
    String test;
    TextView textLocation, textName;
    ListView listview;
    String nilai, nilai1;
    DetailActivity2 act;
    ImageView imageView;
    private TextView textHitung, textTile;
    private EditText textDesc;
    Button slideHandleButton;
    SlidingDrawer slidingDrawer;

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
        textLocation.setText(nilai);
    }

    public String getNilai1() {
        return nilai1;
    }

    public void setNilai1(String nilai1) {
        this.nilai1 = nilai1;
        textName.setText(nilai1);
    }

    public interface MyOnClickAddAttraction {
        public void onItemAddAttraction(int id);
    }

    public void registerForListener(MyOnClickAddAttraction listener) {

        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        view = inflater.inflate(R.layout.submit_attraction, null);
        super.onCreate(savedInstanceState);
        act = (DetailActivity2) getActivity();
        nilai = act.getNilai();
        ActionBar actionBar = getActivity().getActionBar();
        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.custom_action_bar_submit_attraction, null);
        // View xxx = mInflater.inflate(R.layout.menu_slide_up, null);

        slideHandleButton = (Button) view.findViewById(R.id.slideHandleButton);
        slidingDrawer = (SlidingDrawer) view.findViewById(R.id.SlidingDrawer);
        actionBar.setCustomView(mCustomView);
        ImageView btnCancel = (ImageView) mCustomView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Log.e("Cancel", "cancel");
                slidingDrawer.close();
                act.finish();
            }
        });
        ImageView btnOkay = (ImageView) mCustomView.findViewById(R.id.btnOkay);
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Okey", "okey");
                slidingDrawer.open();
            }
        });

        textLocation = (TextView) view.findViewById(R.id.textLocation);
        textLocation.setText(nilai);

        textName = (TextView) view.findViewById(R.id.textName);
        textName.setText(nilai1);

        btnAttname = (LinearLayout) view.findViewById(R.id.btnAttname);
        btnAttname.setOnClickListener(this);

        btnAtttype = (LinearLayout) view.findViewById(R.id.btnAtttype);
        btnAtttype.setOnClickListener(this);

        btnAttlocation = (LinearLayout) view.findViewById(R.id.btnAttlocation);
        btnAttlocation.setOnClickListener(this);

        btnDiscard = (LinearLayout) view.findViewById(R.id.btnDiscard);
        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textLocation.setText("");
                textName.setText("");
            }
        });

        btnSave = (LinearLayout) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PublishActivity.class);
                startActivity(i);
            }
        });

        btnClose = (LinearLayout) view.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.finish();
            }
        });

        textDesc = (EditText) view.findViewById(R.id.textDesc);
        textHitung = (TextView) view.findViewById(R.id.textHitung);
        textDesc.addTextChangedListener(mTextEditorWatcher);
        imageView = (ImageView) view.findViewById(R.id.imageIcon);
        imageView.setOnClickListener(this);
        return view;
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
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

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

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.btnAttname:
                i = new Intent(getActivity(), AddAttractionName.class);
                getActivity().startActivityForResult(i, 1001);
                break;
            case R.id.btnAtttype:
//                i = new Intent(getActivity(), AttractionTypeActivity.class);
//                //i.putExtra("type", "AttractionType");
//                startActivity(i);
                break;
            case R.id.btnAttlocation:
                //listener.onItemAddAttraction(R.id.btnAttlocation);
                i = new Intent(getActivity(), AttractionLocationActivity.class);
                getActivity().startActivityForResult(i, 1000);

                break;
            case R.id.imageIcon:
                i = new Intent(getActivity(), DetailActivity2.class);
                i.putExtra("type", "imageIcon");
                startActivity(i);

                break;

            default:
                break;
        }


    }


}
