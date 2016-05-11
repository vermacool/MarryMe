package com.example.ukstechno.marryme.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ukstechno.marryme.R;
import com.example.ukstechno.marryme.UpdateProfile;
import com.example.ukstechno.marryme.Utils;

public class FragmentOne extends Fragment {
        /*Declaration of Edittext*/
    private EditText firstnameET;
    private EditText lastnameET;
    private TextView emailET;
    private EditText passwordET;
    SharedPreferences preferences;
    private ImageView skip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_one_layout, container, false);
         firstnameET = (EditText) rootView.findViewById(R.id.FirstNameEnter);
         lastnameET = (EditText) rootView.findViewById(R.id.LastNameEnter);
         emailET = (TextView) rootView.findViewById(R.id.EmailEnter);
         passwordET = (EditText) rootView.findViewById(R.id.PasswordEnter);
        skip= (ImageView) rootView.findViewById(R.id.backicon);

        Intent intent=getActivity().getIntent();
        String email=intent.getStringExtra("email");
        emailET.setText(email);

        setEmail();
       // ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button next = (Button) rootView.findViewById(R.id.next);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UpdateProfile) getActivity()).goToNextFragment(1);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.validateField(firstnameET) &&(Utils.validateField(lastnameET))) {
                    FragmentOneData();


                    ((UpdateProfile) getActivity()).goToNextFragment(1);
                }

            }
        });
        return rootView;
    }
    private void FragmentOneData(){
        preferences=getActivity().getSharedPreferences("fragmentone", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("firstname_key",firstnameET.getText().toString());
        editor.putString("lastname_key",lastnameET.getText().toString());
        editor.putString("email_kay",emailET.getText().toString());
        editor.putString("password_key", passwordET.getText().toString());
        editor.commit();

    }
    public void setEmail(){
        SharedPreferences preferences=getActivity().getSharedPreferences("emaildetails",Context.MODE_PRIVATE);
        String email=preferences.getString("emailkey","");
        emailET.setText(email);
    }
}




