package com.angelightmedia.universitytest2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;


// the fragment that shows how i will filter the schools
public class FedSchActivityFilterList extends Fragment  {

    ListView lst_filteredby;
    DBHelper db;
    ArrayList<String> array_lst;
    ArrayAdapter<ArrayList<String>> arrayListArrayAdapter;
    Spinner spinner;
    int f;
    int filterFlag;


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
      lst_filteredby = v.findViewById(R.id.lst_fedAct_filterBy);
        DBHelper dbHelper = new DBHelper(getContext(), null,null, 1);
        //tv_test.setText(dbHelper.getStates());


        array_lst = dbHelper.getStates();

        // get activity into fragment
        FedSchoolsActivity fedSchoolsActivity = (FedSchoolsActivity)getActivity(); // tryigm

        // get spinner from activity
        assert fedSchoolsActivity != null;
        Spinner s = fedSchoolsActivity.spinner;

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                f = position;
                //change filtering according to value of f
                if (f == 0)  { //first position
                    ArrayList<String> array_lst1;
                    filterFlag = 0;

                    array_lst1 = dbHelper.getStates();

                    Collections.sort(array_lst1);

                    arrayListArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, array_lst1);
                    lst_filteredby.setAdapter(arrayListArrayAdapter);
                }
                else if(f==1){
                    ArrayList<String> array_lst2;
                    filterFlag = 1;
                    array_lst2 = dbHelper.getGeozones();

                    Collections.sort(array_lst2);
                    //array_lst2.remove(3); //patch work trying to remove "None"
                    arrayListArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, array_lst2);
                    lst_filteredby.setAdapter(arrayListArrayAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {  // Initialize the spinner
                ArrayList<String> array_lst1;

                array_lst1 = dbHelper.getStates();

                Collections.sort(array_lst1);
                arrayListArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, array_lst1);
                lst_filteredby.setAdapter(arrayListArrayAdapter);

            }
        });
        onClicked();





    }

    public void onClicked() {

        //arrayListArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, array_lst);
       // lst_filteredby.setAdapter(arrayListArrayAdapter);
        lst_filteredby.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //hmmmm...this gets the schools from the list to use in the next activity
                String getSchooltag = (String) parent.getItemAtPosition(position).toString();
                int sOrg = 0; // 0 means filter with state 1 is filter with geozone
                if(f == 0){
                    sOrg = 0;}
                    else if ( f== 1){
                        sOrg = 1;
                }
                Bundle bundle = new Bundle();
                bundle.putString("filter", getSchooltag);
                bundle.putInt("sorg", sOrg);
                FedschActivitySchoolsList f2 = new FedschActivitySchoolsList();
                f2.setArguments(bundle);
                getParentFragmentManager().beginTransaction().replace(
                        R.id.frag_schoolList_fedActivity, f2).commit();

            }
        });
    }


//String data=(String)arg0.getItemAtPosition(arg2);
}