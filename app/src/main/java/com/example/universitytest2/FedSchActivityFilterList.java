package com.example.universitytest2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

// the fragment that shows how i will filter the scools
public class FedSchActivityFilterList extends Fragment {

    ListView lst_filteredby;
    DBHelper db;
    ArrayList<String> array_lst;
    ArrayAdapter<ArrayList<String>> arrayListArrayAdapter;
    View v;

    public FedSchActivityFilterList() {
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
        v = inflater.inflate(R.layout.fragment_fed_sch_activity_filter_list, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {  //attaches the filter to list?
        super.onActivityCreated(savedInstanceState);
      lst_filteredby = v.findViewById(R.id.lst_fedAct_filteredBy);
        DBHelper dbHelper = new DBHelper(getContext(), null,null, 1);
        //tv_test.setText(dbHelper.getStates());

        array_lst = dbHelper.getStates();
        arrayListArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, array_lst);
        lst_filteredby.setAdapter(arrayListArrayAdapter);
    }


}