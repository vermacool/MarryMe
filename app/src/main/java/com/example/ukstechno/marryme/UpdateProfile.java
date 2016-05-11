package com.example.ukstechno.marryme;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.ukstechno.marryme.Fragment.FragmentFive;
import com.example.ukstechno.marryme.Fragment.FragmentFour;
import com.example.ukstechno.marryme.Fragment.FragmentOne;
import com.example.ukstechno.marryme.Fragment.FragmentSix;
import com.example.ukstechno.marryme.Fragment.FragmentThree;
import com.example.ukstechno.marryme.Fragment.FragmentTwo;

public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // EditText editText=(EditText)findViewById(R.id.EmailEnter);

        //getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentOne()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentOne()).commit();
    }


   public void goToNextFragment(int position) {
        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentOne()).commit();
                break;

            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentTwo()).commit();

                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentThree()).commit();

                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentFour()).commit();

                break;
            case 4:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentFive()).commit();

                break;
            case 5:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new FragmentSix()).commit();

                break;
            case 6:
                break;

        }
    }






}
