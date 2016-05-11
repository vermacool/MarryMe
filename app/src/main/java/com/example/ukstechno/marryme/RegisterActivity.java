package com.example.ukstechno.marryme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 12/23/2015.
 */


public class RegisterActivity extends Activity implements View.OnClickListener {

    Spinner religion, motherT, education, cast, occupation;
    Button regiseterUser;
    private EditText  userNameTv, emailTv, mobileNoTv, passwordTv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        regiseterUser = (Button) findViewById(R.id.registerBtn);
        regiseterUser.setOnClickListener(this);
        userNameTv = (EditText) findViewById(R.id.userNameTv);
        emailTv = (EditText) findViewById(R.id.userEmailTv);
        mobileNoTv = (EditText) findViewById(R.id.TFPhone);
        passwordTv = (EditText) findViewById(R.id.TFPassword);
        religion = (Spinner) findViewById(R.id.religionSp);
        motherT = (Spinner) findViewById(R.id.motherTSp);
        education = (Spinner) findViewById(R.id.educationSp);
        cast = (Spinner) findViewById(R.id.castSp);
        occupation = (Spinner) findViewById(R.id.occupationSp);


    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.registerBtn:
                if (Utils.validateField(userNameTv) &&(Utils.validatePassword(passwordTv,true)&&(Utils.validateField(emailTv)))
                        &&(Utils.validateField(mobileNoTv)))
            register();
        }
    }
    public void register(){
        Utils.show(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                Synchronize.BASE_URL +"register.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response ", response);
                Utils.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.has("status")) {
                        try {
                            if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                                startActivity(new Intent(getApplicationContext(), MainActivityLuncher.class));
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
                params.put("name",userNameTv.getText().toString());
                params.put("email",emailTv.getText().toString());
                params.put("phone",mobileNoTv.getText().toString());
                params.put("password",passwordTv.getText().toString());
                params.put("religion",religion.getSelectedItem().toString());
                params.put("mother_tongue",motherT.getSelectedItem().toString());
                params.put("education",education.getSelectedItem().toString());
                params.put("cast",cast.getSelectedItem().toString());
                params.put("occupation",occupation.getSelectedItem().toString());
                Log.d("reg",userNameTv.getText().toString()+" "+emailTv.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    }


