package com.angelightmedia.universitytest2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PrivateSchActivitySchoolsList extends Fragment {

    ListView lst_schoolList;
    DBHelper db;
    ArrayList<String> array_lst;
    String tagP;
    int filterTagP;
    ArrayAdapter<ArrayList<String>> arrayListArrayAdapter;
    View v;

    public PrivateSchActivitySchoolsList() {
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
        v =  inflater.inflate(R.layout.fragment_private_sch_activity_schools_list, container, false);
        // get data from filter frag
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            tagP = bundle.getString("filterP", "LAGOS STATE");
            filterTagP = bundle.getInt("sorgP", 0);
        }
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {  //attaches the filter to list?
        super.onActivityCreated(savedInstanceState);
        lst_schoolList = v.findViewById(R.id.lst_PrivateAct_schoolList);
        DBHelper dbHelper = new DBHelper(getContext(), null,null, 1);
        //tv_test.setText(dbHelper.getStates());
        //
        // i used 'filter' and 'tag' as variables for the getSchools method..brilliant
        array_lst = dbHelper.getSchools(filterTagP, tagP, "Private");
        arrayListArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, array_lst);
        lst_schoolList.setAdapter(arrayListArrayAdapter);

        //switch to schools detail activity
        lst_schoolList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String schoolname = (String)parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(getActivity(), School_Details.class);
                intent.putExtra("sname", schoolname);
                startActivity(intent);


            }
        });
    }
}