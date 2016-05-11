package com.example.ukstechno.marryme.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ukstechno.marryme.MatchingProfile;
import com.example.ukstechno.marryme.R;
import com.example.ukstechno.marryme.UpdateProfile;
import com.example.ukstechno.marryme.Utils;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by verma on 3/12/2016.
 */
public class facebookfragment extends Fragment {
    private CallbackManager callbackManager;
    private TextView textView;
    private TextView id;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();
            displayMessage(profile);
            Intent intent=new Intent(getActivity(),UpdateProfile.class);
            startActivity(intent);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    public facebookfragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                displayMessage(newProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.facebookfragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        textView = (TextView) view.findViewById(R.id.textView);
        id= (TextView) view.findViewById(R.id.textView1);

        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, callback);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void displayMessage(Profile profile){
        if(profile != null){
            textView.setText(profile.getName());
            id.setText(profile.getId());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        displayMessage(profile);
    }
    private void loginViaGoogle() {
        Utils.show(getActivity());
        StringRequest jsObjRequest = new StringRequest(Request.Method.POST,
                "",
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
                                        // saveToLocally();
                                        Utils.setUserCredential(getActivity(), jsonObject.getString("user_id"),
                                                jsonObject.getString("email"), jsonObject.getString("user_name"), true);
                                        startActivity(new Intent(getActivity(), UpdateProfile.class));
                                        Toast.makeText(getActivity(), "Successfully logged in.", Toast.LENGTH_SHORT).show();
                                    } else if (jsonObject.getString("status").equalsIgnoreCase("complete")){
                                        Intent intent=new Intent(getActivity(),MatchingProfile.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(getActivity(), "username or password is incorrect",
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
                Utils.dismiss();
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_type","facebook");
                params.put("username", textView.getText().toString());
                params.put("email", id.getText().toString());
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(jsObjRequest);

    }
}
