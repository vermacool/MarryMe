package com.example.ukstechno.marryme.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.ukstechno.marryme.R;
import com.example.ukstechno.marryme.UpdateProfile;
import com.example.ukstechno.marryme.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FragmentTwo extends Fragment {
    /*Declaration of Edittext*/
    private EditText mobleET;
    private EditText dobET;
    /*Declaration of spinner*/
    private Spinner heightSP;
    private Spinner weightSP;
    private Spinner genderSP;
    private SharedPreferences preferences;
    /*Declaration of calender*/
    int myear;
    int mmonth;
    int mday;
    DatePickerDialog datePickerDialog;
    Calendar calendar;
    /*Declaration of Array*/
    private ImageView skip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View rootView =
                inflater.inflate(R.layout.fragment_two_layout, container, false);
        Button next = (Button) rootView.findViewById(R.id.next1);
        mobleET = (EditText) rootView.findViewById(R.id.PhoneNumberLast);
        dobET = (EditText) rootView.findViewById(R.id.dobid);
        heightSP = (Spinner) rootView.findViewById(R.id.spinner4);
        weightSP = (Spinner) rootView.findViewById(R.id.spinner5);
        genderSP = (Spinner) rootView.findViewById(R.id.spinner6);
        skip= (ImageView) rootView.findViewById(R.id.backiconfm2);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UpdateProfile) getActivity()).goToNextFragment(2);
            }
        });
        dobET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calender();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.validateField(mobleET) &&(Utils.validateField(dobET))) {
                    fragmentTwoData();
                    ((UpdateProfile) getActivity()).goToNextFragment(2);
                }
            }
        });
        return rootView;
    }

    private void fragmentTwoData() {
        preferences = getActivity().getSharedPreferences("FragmentTwo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mobile_key", mobleET.getText().toString());
        editor.putString("dob_key", dobET.getText().toString());
        editor.putString("height_key", heightSP.getSelectedItem().toString());
        editor.putString("weight_key", weightSP.getSelectedItem().toString());
        editor.putString("gender_key", genderSP.getSelectedItem().toString());
        editor.commit();
    }

    private void calender() {
        calendar = Calendar.getInstance();
        myear = calendar.get(Calendar.YEAR);
        mmonth = calendar.get(Calendar.MONTH);
        mday = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myear = year;
                mmonth = monthOfYear;
                mday = dayOfMonth;
               // updateLabel();
                updateDisplay();
            }
        };

        datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, myear, mmonth, mday);
        datePickerDialog.show();

    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dobET.setText(sdf.format(calendar.getTime()));

    }
    private void updateDisplay() {

        dobET.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(mday).append("/").append(mmonth + 1).append("/").append(myear).append(" "));
    }
}

