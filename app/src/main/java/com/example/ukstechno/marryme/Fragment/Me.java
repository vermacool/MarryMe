package com.example.ukstechno.marryme.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ukstechno.marryme.R;

/**
 * Created by verma on 3/19/2016.
 */
public class Me extends Fragment {
    View rootview;
    /*Declaration of fragmentsix variable*/
    private ImageView imageView;
    private TextView setlookingfor;
    private TextView setPartnerAge;
    private TextView setPartnercountry;
    private TextView setPartnerreligion;
    private TextView setPartnerCast;
    private TextView setPartnerHeight;
    private TextView setPartnerEducation;
    private TextView setPartnerComplexion;
    private TextView setPartnermotherTongue;
    private TextView setPartnerAnnualincome;
    /*Declaration of fragmentfive variable*/
    private TextView setcounty;
    private TextView setstate;
    private TextView setcity;
    /*Declaration of Fragmentfour variable*/
    private TextView seteducation;
    private TextView setemployidin;
    private TextView setoccupation;
    private TextView setdesignation;
    private TextView setannualincome;
    /*Declaration of fragmentthree variable*/
    private  TextView setreligion;
    private  TextView setmothertongue;
    private  TextView setcast;
    private  TextView setmaritalstatus;
    private  TextView setmanglik;
    /*Declaration of fragmentTwoData*/
    private TextView setmobile;
    private TextView setdob;
    private TextView setheight;
    private TextView setweight;
    private TextView setgender;
    /*Declaration of fragmentone variable */
    private TextView setfirstname;
    private TextView setlastname;
    private TextView setemail;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview=
                inflater.inflate(R.layout.me,container,false);
        imageView= (ImageView) rootview.findViewById(R.id.imageset);
        findviewID();
        getfragmentsixData();
        getFragmentFiveData();
        getFragmentFourData();
        getFragmentThreeData();
        getFragmentTwoData();
        getfragmentOneData();

        return rootview;
    }
    private void getfragmentsixData(){
        SharedPreferences preferences=getActivity().getSharedPreferences("Fragmentsix", Context.MODE_PRIVATE);
        String imageview=preferences.getString("image_key", "");
        imageView.setImageBitmap(decodeBase64(imageview));
        String looking=preferences.getString("lookingfor_key","");
        setlookingfor.setText(looking);
        String partnerage=preferences.getString("partnerage_key","");
        setPartnerAge.setText(partnerage);
        String Pcountry=preferences.getString("partnercountry_key","");
        setPartnercountry.setText(Pcountry);
        String Preligion=preferences.getString("pertnerreligion_key","");
        setPartnerreligion.setText(Preligion);
        String Pcast=preferences.getString("partnercast_key","");
        setPartnerCast.setText(Pcast);
        String Pheight=preferences.getString("partnerheight_key","");
        setPartnerHeight.setText(Pheight);
        String Peducation=preferences.getString("partnereducation_key","");
        setPartnerEducation.setText(Peducation);
        String Pcomplexion=preferences.getString("partnercomplexion_key","");
        setPartnerComplexion.setText(Pcomplexion);
        String Pmothertongue=preferences.getString("partnermothertongue_key","");
        setPartnermotherTongue.setText(Pmothertongue);
        String Pannualincome=preferences.getString("partnerannualincome_key","");
        setPartnerAnnualincome.setText(Pannualincome);
    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
    private void findviewID(){
        setlookingfor= (TextView) rootview.findViewById(R.id.lookingforset);
        setPartnerAge= (TextView) rootview.findViewById(R.id.partnerageset);
        setPartnercountry= (TextView) rootview.findViewById(R.id.partnercountrylivinginset);
        setPartnerreligion= (TextView) rootview.findViewById(R.id.partnerreligionset);
        setPartnerCast= (TextView) rootview.findViewById(R.id.partnercastset);
        setPartnerHeight= (TextView) rootview.findViewById(R.id.partnerheightset);
        setPartnerEducation= (TextView) rootview.findViewById(R.id.partnereducationset);
        setPartnerComplexion= (TextView) rootview.findViewById(R.id.partnercomplexionset);
        setPartnermotherTongue= (TextView) rootview.findViewById(R.id.partnermothertongueset);
        setPartnerAnnualincome= (TextView) rootview.findViewById(R.id.partnerannualincomeset);
        setcounty= (TextView) rootview.findViewById(R.id.countrylivinginset);
        setstate= (TextView) rootview.findViewById(R.id.stateset);
        setcity= (TextView) rootview.findViewById(R.id.cityset);
        seteducation= (TextView) rootview.findViewById(R.id.educationset);
        setemployidin= (TextView) rootview.findViewById(R.id.employedinset);
        setoccupation= (TextView) rootview.findViewById(R.id.occupationset);
        setdesignation= (TextView) rootview.findViewById(R.id.designation);
        setannualincome= (TextView) rootview.findViewById(R.id.annualincomeset);
        setreligion= (TextView) rootview.findViewById(R.id.religionset);
        setmothertongue= (TextView) rootview.findViewById(R.id.mothertongueset);
        setcast= (TextView) rootview.findViewById(R.id.castset);
        setmaritalstatus= (TextView) rootview.findViewById(R.id.maritalstatusset);
        setmanglik= (TextView) rootview.findViewById(R.id.manglikset);
        setmobile= (TextView) rootview.findViewById(R.id.mobileset);
        setdob= (TextView) rootview.findViewById(R.id.dobset);
        setheight= (TextView) rootview.findViewById(R.id.heightset);
        setweight= (TextView) rootview.findViewById(R.id.weightset);
        setgender= (TextView) rootview.findViewById(R.id.genderset);
        setfirstname= (TextView) rootview.findViewById(R.id.firstnameset);
        setlastname= (TextView) rootview.findViewById(R.id.lastnameset);
        setemail= (TextView) rootview.findViewById(R.id.emailset);

    }
    private void getFragmentFiveData(){
        SharedPreferences preferences=getActivity().getSharedPreferences("FregmentFive", Context.MODE_PRIVATE);
        String country=preferences.getString("countryliving_key","");
        setcounty.setText(country);
        String state=preferences.getString("state_key","");
        setstate.setText(state);
        String city=preferences.getString("city_key","");
        setcity.setText(city);
    }
    private void getFragmentFourData(){
        SharedPreferences preferences=getActivity().getSharedPreferences("FragmentFour", Context.MODE_PRIVATE);
        String education=preferences.getString("education_key","");
        seteducation.setText(education);
        String employedin=preferences.getString("emploedin_key", "");
        setemployidin.setText(employedin);
        String occupation=preferences.getString("occupation_key","");
        setoccupation.setText(occupation);
        String designetion=preferences.getString("designetion_key","");
        setdesignation.setText(designetion);
        String annualincome=preferences.getString("annualincome_key","");
        setannualincome.setText(annualincome);
    }
    private void getFragmentThreeData(){
        SharedPreferences preferences=getActivity().getSharedPreferences("FragmentThree", Context.MODE_PRIVATE);
        String religion=preferences.getString("religion_key","");
        setreligion.setText(religion);
        String motherT=preferences.getString("mothertongue_key","");
        setmothertongue.setText(motherT);
        String cast=preferences.getString("cast_key","");
        setcast.setText(cast);
        String marital=preferences.getString("maritalstatus_key","");
        setmaritalstatus.setText(marital);
        String manglik=preferences.getString("manglik_key","");
        setmanglik.setText(manglik);
    }
    private void getFragmentTwoData(){
        SharedPreferences preferences=getActivity().getSharedPreferences("FragmentTwo", Context.MODE_PRIVATE);
        String mobile=preferences.getString("mobile_key", "");
        setmobile.setText(mobile);
        String dob=preferences.getString("dob_key","");
        setdob.setText(dob);
        String height=preferences.getString("height_key","");
        setheight.setText(height);
        String weight=preferences.getString("weight_key","");
        setweight.setText(weight);
        String gender=preferences.getString("gender_key","");
        setgender.setText(gender);
    }
    private void getfragmentOneData(){
        SharedPreferences preferences=getActivity().getSharedPreferences("fragmentone", Context.MODE_PRIVATE);
        String firstname=preferences.getString("firstname_key","");
        setfirstname.setText(firstname);
        String lastaname=preferences.getString("lastname_key","");
        setlastname.setText(lastaname);
        String email=preferences.getString("email_kay","");
        setemail.setText(email);
    }
}
