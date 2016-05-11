package com.example.ukstechno.marryme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ukstechno.marryme.Fragment.FragmentFive;
import com.example.ukstechno.marryme.Fragment.FragmentFour;
import com.example.ukstechno.marryme.Fragment.FragmentOne;
import com.example.ukstechno.marryme.Fragment.FragmentSix;
import com.example.ukstechno.marryme.Fragment.FragmentThree;
import com.example.ukstechno.marryme.Fragment.FragmentTwo;
import com.example.ukstechno.marryme.Fragment.MatchingFragment;
import com.example.ukstechno.marryme.Fragment.Me;

public class MatchingProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      getSupportFragmentManager().beginTransaction().replace(R.id.matchingcontainer,new MatchingFragment()).commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubutton, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.me:
                getSupportFragmentManager().beginTransaction().replace(R.id.matchingcontainer,new Me()).commit();
                break;
            case R.id.yrpartner:
                getSupportFragmentManager().beginTransaction().replace(R.id.matchingcontainer,new MatchingFragment()).commit();
                break;
            case R.id.editprofile:
                Intent intent=new Intent(MatchingProfile.this,UpdateProfile.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
