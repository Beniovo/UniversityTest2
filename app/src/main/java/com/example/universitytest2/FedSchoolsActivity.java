package com.example.universitytest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

//activity showing both fragments

public class FedSchoolsActivity extends AppCompatActivity {
    FragmentManager myfragManager;
    FedSchActivityFilterList fedSchActivityFilterList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fed_schools);

        myfragManager = this.getSupportFragmentManager();
        fedSchActivityFilterList = (FedSchActivityFilterList) myfragManager.findFragmentById(R.id.frag_sortby_fedschActivity);

        myfragManager.beginTransaction().
                show(fedSchActivityFilterList).commit();
    }
}