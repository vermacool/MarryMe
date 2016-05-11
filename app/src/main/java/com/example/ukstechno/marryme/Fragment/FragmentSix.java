package com.example.ukstechno.marryme.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ukstechno.marryme.MatchingProfile;
import com.example.ukstechno.marryme.R;
import com.example.ukstechno.marryme.Synchronize;
import com.example.ukstechno.marryme.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FragmentSix extends Fragment {
    /*Declaration of fragmentone String*/
    private String getfirstname;
    private String getlastname;
    private String getemail;
    private String getpassword;
    /*Declaration of fragmenttwoData*/
    private String getmobile;
    private String getdob;
    private String getheight;
    private String getweight;
    private String getgender;
    /*Declaration of String of fragment three*/
    private String getreligion;
    private String getmothertongue;
    private String getcast;
    private String getmaritalstatus;
    private String getmanglik;
    /*Declaration of fragmentfour*/
    private String geteducation;
    private String getemployedin;
    private String getoccupation;
    private String getdesignetion;
    private String getannualincome;
    /*Declaration of fragmentfive*/
    private String getcountrylivingin;
    private String getstate;
    private String getcity;
    /*Declaration of Fragmentsix*/
    private Spinner lookingfor;
    private Spinner partnerageFrom;
    private Spinner partnerageTo;
    private Spinner partnercountryLivingin;
    private Spinner partnerreligion;
    private Spinner partnetrcast;
    private Spinner partnerheightFrom;
    private Spinner partnerheightTo;
    private Spinner partnereducation;
    private Spinner partnercomplexion;
    private Spinner partnermothertongue;
    private Spinner partnerAnnualincome;
    private ImageView profile;
    private Bitmap bitmap;
    private String image;
    private int PICK_IMAGE_REQUEST = 1;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_six_layout, container, false);
        fragmentSixFindId();
        profile= (ImageView) rootView.findViewById(R.id.profileimage);
        Button btn = (Button) rootView.findViewById(R.id.Continue);

        getfragmentoneData();
        getfragmenttwo();
        getfragmentthree();
        getfragmentfour();
        fragmentFive();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.hasImage(profile, getActivity())) {

                    profileDetails();
                    fragmentsixData();
                }

            }
        });


        // TODO Auto-generated method stub
        return rootView;
    }

    private void getfragmentoneData(){
        SharedPreferences preferences=getActivity().getSharedPreferences("fragmentone", Context.MODE_PRIVATE);
        getfirstname=preferences.getString("firstname_key","");
        getlastname=preferences.getString("lastname_key","");
        getemail=preferences.getString("email_kay","");
        getpassword=preferences.getString("password_key","");
    }
    private void getfragmenttwo(){
        SharedPreferences preferences=getActivity().getSharedPreferences("FragmentTwo",Context.MODE_PRIVATE);
        getmobile=preferences.getString("mobile_key","");
        getdob=preferences.getString("dob_key","");
        getheight=preferences.getString("height_key","");
        getweight=preferences.getString("weight_key","");
        getgender=preferences.getString("gender_key","");
    }
    private void getfragmentthree(){
        SharedPreferences preferences=getActivity().getSharedPreferences("FragmentThree",Context.MODE_PRIVATE);
        getreligion=preferences.getString("religion_key","");
        getmothertongue=preferences.getString("mothertongue_key","");
        getcast=preferences.getString("cast_key","");
        getmaritalstatus=preferences.getString("maritalstatus_key","");
        getmanglik=preferences.getString("manglik_key","");
    }
    private void getfragmentfour(){
        SharedPreferences preferences=getActivity().getSharedPreferences("FragmentFour",Context.MODE_PRIVATE);
        geteducation=preferences.getString("education_key","");
        getemployedin=preferences.getString("emploedin_key","");
        getoccupation=preferences.getString("occupation_key","");
        getdesignetion=preferences.getString("designetion_key","");
        getannualincome=preferences.getString("annualincome_key","");
    }
    private void fragmentFive(){
        SharedPreferences preferences=getActivity().getSharedPreferences("FregmentFive",Context.MODE_PRIVATE);
        getcountrylivingin=preferences.getString("countryliving_key","");
        getstate=preferences.getString("state_key","");
        getcity=preferences.getString("city_key","");

    }
    public void profileDetails(){
        Utils.show(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                Synchronize.BASE_URL +"user_profile.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response ", response);
                Utils.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("status")) {
                        try {
                            if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                                Intent intent=new Intent(getActivity(),MatchingProfile.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                 image = getStringImage(bitmap);
                params.put("user_id",Utils.getUserId(getActivity()));
                params.put("firstname",getfirstname);
                params.put("lastname",getlastname);
                params.put("email",getemail);
                params.put("mobile_number",getmobile);
                params.put("dob",getdob);
                params.put("height",getheight);
                params.put("weight",getweight);
                params.put("gender",getgender);
                params.put("religion",getreligion);
                params.put("mother_tongue",getmothertongue);
                params.put("cast",getcast);
                params.put("marital_status",getmaritalstatus);
                params.put("manglik",getmanglik);
                params.put("education",geteducation);
                params.put("employed_in",getemployedin);
                params.put("occupation",getoccupation);
                params.put("designation",getdesignetion);
                params.put("annual_income",getannualincome);
                params.put("country_living",getcountrylivingin);
                params.put("state",getstate);
                params.put("city",getcity);
                params.put("looking_for",lookingfor.getSelectedItem().toString());
                params.put("partner_age",partnerageFrom.getSelectedItem().toString());
                params.put("partner_country_living_in",partnercountryLivingin.getSelectedItem().toString());
                params.put("partner_religion",partnerreligion.getSelectedItem().toString());
                params.put("partner_cast",partnetrcast.getSelectedItem().toString());
                params.put("partner_height",partnerheightFrom.getSelectedItem().toString());
                params.put("partner_education",partnereducation.getSelectedItem().toString());
                params.put("partner_complexion",partnercomplexion.getSelectedItem().toString());
                params.put("partner_mother_tongue",partnermothertongue.getSelectedItem().toString());
                params.put("partner_annual_income",partnerAnnualincome.getSelectedItem().toString());
                params.put("image_url",image);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
    private void fragmentSixFindId(){
        lookingfor= (Spinner) rootView.findViewById(R.id.spinner20);
        partnerageFrom= (Spinner) rootView.findViewById(R.id.spinner21);
        partnercountryLivingin= (Spinner) rootView.findViewById(R.id.spinner22);
        partnerreligion= (Spinner) rootView.findViewById(R.id.spinner23);
        partnetrcast= (Spinner) rootView.findViewById(R.id.spinner24);
        partnerheightFrom= (Spinner) rootView.findViewById(R.id.spinner25);
        partnereducation= (Spinner) rootView.findViewById(R.id.spinner27);
        partnercomplexion= (Spinner) rootView.findViewById(R.id.spinner28);
        partnermothertongue= (Spinner) rootView.findViewById(R.id.spinner29);
        partnerAnnualincome= (Spinner) rootView.findViewById(R.id.spinner30);
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                profile.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 10, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    public void fragmentsixData(){
        SharedPreferences preferences=getActivity().getSharedPreferences("Fragmentsix",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("image_key", getStringImage(bitmap));
        editor.putString("lookingfor_key",lookingfor.getSelectedItem().toString());
        editor.putString("partnerage_key",partnerageFrom.getSelectedItem().toString());
        editor.putString("partnercountry_key",partnercountryLivingin.getSelectedItem().toString());
        editor.putString("pertnerreligion_key",partnerreligion.getSelectedItem().toString());
        editor.putString("partnercast_key",partnetrcast.getSelectedItem().toString());
        editor.putString("partnerheight_key",partnerheightFrom.getSelectedItem().toString());
        editor.putString("partnereducation_key",partnereducation.getSelectedItem().toString());
        editor.putString("partnercomplexion_key",partnercomplexion.getSelectedItem().toString());
        editor.putString("partnermothertongue_key",partnermothertongue.getSelectedItem().toString());
        editor.putString("partnerannualincome_key",partnerAnnualincome.getSelectedItem().toString());
        editor.commit();
    }
}
