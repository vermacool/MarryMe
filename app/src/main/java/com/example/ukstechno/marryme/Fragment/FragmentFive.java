package com.example.ukstechno.marryme.Fragment;

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

public class
        FragmentFive extends Fragment {
    private Spinner countrylivinginSP;
    private Spinner stateSP;
    private Spinner citySP;
    private SharedPreferences preferences;
    private ImageView skip;
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
   Bundle savedInstanceState) {
  // TODO Auto-generated method stub
     View rootView =
             inflater.inflate(R.layout.fragment_five_layout, container, false);
     Button next = (Button) rootView.findViewById(R.id.next5);
     countrylivinginSP= (Spinner) rootView.findViewById(R.id.spinner17);
     stateSP= (Spinner) rootView.findViewById(R.id.spinner18);
     citySP= (Spinner) rootView.findViewById(R.id.spinner19);
     skip= (ImageView) rootView.findViewById(R.id.backiconfm5);
     skip.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             ((UpdateProfile) getActivity()).goToNextFragment(5);
         }
     });
     next.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             fragmentfive();
             ((UpdateProfile) getActivity()).goToNextFragment(5);
         }
     });
     return rootView;

 }
    private void fragmentfive(){
        preferences=getActivity().getSharedPreferences("FregmentFive", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("countryliving_key",countrylivinginSP.getSelectedItem().toString());
        editor.putString("state_key",stateSP.getSelectedItem().toString());
        editor.putString("city_key",citySP.getSelectedItem().toString());
        editor.commit();
    }
}