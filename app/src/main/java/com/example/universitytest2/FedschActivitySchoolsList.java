package com.example.universitytest2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FedschActivitySchoolsList extends Fragment {
    ListView lst_schoolList;
    DBHelper db;
    ArrayList<String> array_lst;
    ArrayAdapter<ArrayList<String>> arrayListArrayAdapter;
    View v;




    public FedschActivitySchoolsList() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_fedsch_activity_schools_list, container, false);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {  //attaches the filter to list?
        super.onActivityCreated(savedInstanceState);
        lst_schoolList = v.findViewById(R.id.lst_fedAct_schoolList);
        DBHelper dbHelper = new DBHelper(getContext(), null,null, 1);
        //tv_test.setText(dbHelper.getStates());

        array_lst = dbHelper.getGeozones();
        arrayListArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, array_lst);
        lst_schoolList.setAdapter(arrayListArrayAdapter);
    }
}