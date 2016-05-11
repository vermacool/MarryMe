package com.example.ukstechno.marryme.Fragment;

/**
 * Created by user on 1/8/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.ukstechno.marryme.R;
import com.example.ukstechno.marryme.UpdateProfile;

public class FragmentThree extends Fragment {
    /*Declaration of spinner*/
    private Spinner religion;
    private Spinner mothertongue;
    private Spinner cast;
    private Spinner maritalstatus;
    private Spinner manglik;
    private SharedPreferences preferences;
    private ImageView skip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // TODO Auto-generated method stub

        View rootView =
                inflater.inflate(R.layout.fragment_three_layout, container, false);
        Button next = (Button) rootView.findViewById(R.id.next3);
        religion = (Spinner) rootView.findViewById(R.id.spinner7);
        mothertongue = (Spinner) rootView.findViewById(R.id.spinner8);
        cast = (Spinner) rootView.findViewById(R.id.spinner9);
        maritalstatus= (Spinner) rootView.findViewById(R.id.spinner10);
        manglik= (Spinner) rootView.findViewById(R.id.spinner11);
        skip= (ImageView) rootView.findViewById(R.id.backiconfm3);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((UpdateProfile) getActivity()).goToNextFragment(3);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentThreedata();
                ((UpdateProfile) getActivity()).goToNextFragment(3);
            }
        });
        return rootView;
    }
    private void fragmentThreedata(){
        preferences=getActivity().getSharedPreferences("FragmentThree",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("religion_key",religion.getSelectedItem().toString());
        editor.putString("mothertongue_key",mothertongue.getSelectedItem().toString());
        editor.putString("cast_key",cast.getSelectedItem().toString());
        editor.putString("maritalstatus_key",maritalstatus.getSelectedItem().toString());
        editor.putString("manglik_key",manglik.getSelectedItem().toString());
        editor.commit();

    }
}

