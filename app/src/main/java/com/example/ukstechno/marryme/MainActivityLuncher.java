package com.example.ukstechno.marryme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ukstechno.marryme.Fragment.ForgotFragment;
import com.example.ukstechno.marryme.Fragment.FragmentOne;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivityLuncher extends AppCompatActivity implements View.OnClickListener {

    Button regesterBtn, lobtn;
    Button loginBtn;
    String emailname;
    String paswordTv;
    EditText emailTv, passwordTv;
    ImageButton fb, google;
    FrameLayout frameLayout;
    Button forgotmail;
    TextView forgot;
    EditText forgotemail;
    Button forgotSendbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        regesterBtn = (Button) findViewById(R.id.registerBtn);
        loginBtn.setOnClickListener(this);
        regesterBtn.setOnClickListener(this);
        emailTv = (EditText) findViewById(R.id.emailTv);
        passwordTv = (EditText) findViewById(R.id.passwordTv);
        fb = (ImageButton) findViewById(R.id.facebook);
        frameLayout= (FrameLayout) findViewById(R.id.forgotpassword);
        forgotmail= (Button) findViewById(R.id.forgotcancel);
        forgotmail.setOnClickListener(this);
        google = (ImageButton) findViewById(R.id.imageButton3);
        forgot= (TextView) findViewById(R.id.textforgotpassword);
        forgotemail= (EditText) findViewById(R.id.forgotemail);
        forgotSendbutton= (Button) findViewById(R.id.forgotsend);
        forgotSendbutton.setOnClickListener(this);
        forgot.setOnClickListener(this);

        frameLayout.setVisibility(View.INVISIBLE);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityLuncher.this, FacebookDisplay.class);
                startActivity(intent);
            }
        });
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityLuncher.this, GoogleSignin.class);
                startActivity(intent);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.registerBtn:
                Intent intent = new Intent(MainActivityLuncher.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.loginBtn:
                if (Utils.validateField(emailTv)&&(Utils.validatePassword(passwordTv,true))){
                    login();
                    break;
                }
            case R.id.forgotcancel:
                frameLayout.setVisibility(View.INVISIBLE);
                break;
            case R.id.textforgotpassword:
                frameLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.forgotsend:
                if (Utils.isValidEmail(( forgotemail.getText().toString()))){
                    Toast.makeText(getApplicationContext(),"sned",Toast.LENGTH_LONG).show();
                }else {
                    forgotemail.setError("Please Enter Valid Email");
                }
        }

    }


    private void login() {
        Utils.show(this);
        StringRequest jsObjRequest = new StringRequest(Request.Method.POST,
                "http://ukstechno.co.in/marrige/login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response ", response);
                        Utils.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.has("status")) {
                                try {
                                    if (jsonObject.getString("status").equalsIgnoreCase("success")) {
                                        getemail();
                                        // saveToLocally();
                                        Utils.setUserCredential(MainActivityLuncher.this, jsonObject.getString("user_id"),
                                                jsonObject.getString("email"), jsonObject.getString("user_name"), true);
                                        startActivity(new Intent(getApplicationContext(), MatchingProfile.class));
                                        Toast.makeText(MainActivityLuncher.this, "Successfully logged in.", Toast.LENGTH_SHORT).show();
                                    } else if (jsonObject.getString("status").equalsIgnoreCase("No Profile")){
                                        Intent intent=new Intent(getApplicationContext(),UpdateProfile.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(MainActivityLuncher.this, "username or password is incorrect",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error: ", error.toString());
                /*Utils.dismiss();*/
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", emailTv.getText().toString());
                params.put("password", passwordTv.getText().toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsObjRequest);

    }
    public void getemail(){
        SharedPreferences preferences=getSharedPreferences("emaildetails",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("emailkey",emailTv.getText().toString());
        editor.commit();
    }
}




