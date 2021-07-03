package com.example.universitytest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

//activity showing both fragments

public class FedSchoolsActivity extends AppCompatActivity {
    FragmentManager myfragManager;
    FedSchActivityFilterList fedSchActivityFilterList;
    FedschActivitySchoolsList fedschActivitySchoolsList;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fed_schools);
        spinner = (Spinner)findViewById(R.id.spinner_fedschActivity);

        myfragManager = this.getSupportFragmentManager();
        fedSchActivityFilterList = (FedSchActivityFilterList) myfragManager.findFragmentById(R.id.frag_sortby_fedschActivity);
        fedschActivitySchoolsList = (FedschActivitySchoolsList) myfragManager.findFragmentById(R.id.frag_schoolList_fedActivity);
        getfilter();
        myfragManager.beginTransaction().
                show(fedSchActivityFilterList).commit();
        myfragManager.beginTransaction().
                show(fedschActivitySchoolsList).commit();
    }

    private void getfilter(){
        ArrayAdapter<String> filteradapter = new ArrayAdapter<>(this, android.R.layout
                .simple_spinner_item, getResources().getStringArray(R.array.filter));
        filteradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(filteradapter);
    }

    public  String getText(){
        return spinner.getSelectedItem().toString();
    }
}