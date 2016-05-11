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

public class FragmentFour extends Fragment {
    /*Declaration of spinner*/
    private Spinner educationSP;
    private Spinner employedinSP;
    private Spinner occupationSP;
    private Spinner designetionSP;
    private Spinner annualincomeSp;
    private SharedPreferences preferences;
    private ImageView skip;

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
   Bundle savedInstanceState) {
  // TODO Auto-generated method stub
     View rootView =
             inflater.inflate(R.layout.fragment_four_layout, container, false);
     Button next = (Button) rootView.findViewById(R.id.next4);
      educationSP=(Spinner) rootView.findViewById(R.id.spinner12);
      occupationSP=(Spinner) rootView.findViewById(R.id.spinner14);
     employedinSP= (Spinner) rootView.findViewById(R.id.spinner13);
     designetionSP= (Spinner) rootView.findViewById(R.id.spinner15);
     annualincomeSp= (Spinner) rootView.findViewById(R.id.spinner16);
     skip= (ImageView) rootView.findViewById(R.id.backiconfm4);
     skip.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             ((UpdateProfile) getActivity()).goToNextFragment(4);
         }
     });

     next.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             fragmentFourData();
             ((UpdateProfile) getActivity()).goToNextFragment(4);
         }
     });
     return rootView;
 }
    private void fragmentFourData(){
        preferences=getActivity().getSharedPreferences("FragmentFour",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("education_key",educationSP.getSelectedItem().toString());
        editor.putString("emploedin_key",employedinSP.getSelectedItem().toString());
        editor.putString("occupation_key",occupationSP.getSelectedItem().toString());
        editor.putString("designetion_key",designetionSP.getSelectedItem().toString());
        editor.putString("annualincome_key",annualincomeSp.getSelectedItem().toString());
        editor.commit();

    }

}