package com.example.ukstechno.marryme.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ukstechno.marryme.Adapter.CardAdapter;
import com.example.ukstechno.marryme.AdapterItemClass.PartnerItem;
import com.example.ukstechno.marryme.App.Config;
import com.example.ukstechno.marryme.R;
import com.example.ukstechno.marryme.Synchronize;
import com.example.ukstechno.marryme.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by verma on 3/21/2016.
 */
public class MatchingFragment extends Fragment {
    //Creating a List of superheroes
    private ArrayList<PartnerItem> list;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private View rootview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview=
                inflater.inflate(R.layout.matchingfragment,container,false);
        recyclerView = (RecyclerView)rootview.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //Initializing our superheroes list
        list = new ArrayList<>();

        //Calling method to get data
       // getData();
        profileDetails();
        return rootview;
    }
    private void getData() {
        //Showing a progress dialog
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Loading Data", "Please wait...", false, false);

        //Creating a json array request
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST,Config.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Dismissing progress dialog
                        loading.dismiss();

                        //calling method to parse json array
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            parseData(jsonArray);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }


                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("user_id", Utils.getUserId(getActivity()));
                return params;
            }
        };


            //Creating request queue
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

            //Adding request to the queue
            requestQueue.add(jsonArrayRequest);

    }

    //This method will parse json data
    private void parseData(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            PartnerItem item = new PartnerItem();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                item.setUri(json.getString(Config.TAG_IMAGE_URL));
                item.setFirstname(json.getString(Config.TAG_FIRSTNAME));
                item.setLastname(json.getString(Config.TAG_LASTNAME));
                item.setEmail(json.getString(Config.TAG_EMAIL));
                item.setCity(json.getString(Config.TAG_CITY));
                item.setContact(json.getString(Config.TAG_CONTACT));
                list.add(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //list.add(item);
        }

        //Finally initializing our adapter
        adapter = new CardAdapter(list, getActivity());

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
    private void profileDetails() {
        Utils.show(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Config.DATA_URL, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                Log.d("PostDetails Response ", response);
                Utils.dismiss();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    getProfile(jsonArray);

                    Log.d("j", String.valueOf(jsonArray));
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
                params.put("user_id", Utils.getUserId(getActivity()));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }



    private void getProfile(JSONArray jsonArray) {
        try {

            for (int i = 0; i <= jsonArray.length(); i++) {
                PartnerItem itemList=new PartnerItem();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                itemList.setUri(jsonObject.getString(Config.TAG_IMAGE_URL));
                itemList.setFirstname(jsonObject.getString(Config.TAG_FIRSTNAME));
                itemList.setLastname(jsonObject.getString(Config.TAG_LASTNAME));
                itemList.setEmail(jsonObject.getString(Config.TAG_EMAIL));
                itemList.setCity(jsonObject.getString(Config.TAG_CITY));
                itemList.setContact(jsonObject.getString(Config.TAG_CONTACT));
                list.add(itemList);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Finally initializing our adapter
        adapter = new CardAdapter(list, getActivity());

        //Adding adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }


}
