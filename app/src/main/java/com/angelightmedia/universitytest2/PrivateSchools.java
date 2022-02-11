package com.angelightmedia.universitytest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PrivateSchools extends AppCompatActivity {

    FragmentManager myfragManager;
    PrivateSchActivityFilterList privateSchActivityFilterList;
    PrivateSchActivitySchoolsList privateschActivitySchoolsList;
    Spinner spinner;
    SearchView mySearchview;
    ListView myListView;
    SimpleCursorAdapter arrayAdapter;
    ArrayList<String> schoolslist;
    Button bt_private_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_schools);
        spinner = (Spinner)findViewById(R.id.spinner_privateschActivity);
        bt_private_activity = findViewById(R.id.bt_home_PrivateSchActivity);
        myfragManager = this.getSupportFragmentManager();
        privateSchActivityFilterList = (PrivateSchActivityFilterList) myfragManager.findFragmentById(R.id.frag_sortby_privateschActivity);
        privateschActivitySchoolsList = (PrivateSchActivitySchoolsList) myfragManager.findFragmentById(R.id.frag_schoolList_privateActivity);

        myfragManager.beginTransaction().
                show(privateSchActivityFilterList).commit();
        myfragManager.beginTransaction().
                show(privateschActivitySchoolsList).commit();
        mySearchview = findViewById(R.id.bt_search_privateschActivity);
        myListView = findViewById(R.id.searchForLVprivate);
        useSearchview();
        bt_private_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

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

    public void useSearchview(){  // populate searchview

        DBHelper dbHelper = new DBHelper(this, null, null, 1);

        schoolslist = dbHelper.getallSchools(); //get all schools from db

        mySearchview = findViewById(R.id.bt_search_privateschActivity);
        mySearchview.setQueryHint("type name of school");
        final String[] from = new String[] {"schoolsName"};
        final int[] to = new int[] {android.R.id.text1};
        myListView = findViewById(R.id.searchForLV);
        arrayAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, null,
                from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        mySearchview.setSuggestionsAdapter(arrayAdapter);




        mySearchview.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {


                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                Cursor cursor = (Cursor) arrayAdapter.getItem(position);
                //String txt = cursor.getString(cursor.getColumnIndex("schoolsName"));
                //mySearchview.setQuery(txt, true);
                Intent intent = new Intent(getApplicationContext(), School_Details.class);
                //intent.putExtra("sname", txt);
                startActivity(intent);
                return true;
            }
        });

        mySearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(schoolslist.contains(query)){
                    arrayAdapter.getFilter().filter(query);
                }
                else{
                    Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                populateAdaptor(newText);
                return false;
            }
        });


    }

    public void populateAdaptor(String query) {
        final MatrixCursor c = new MatrixCursor(new String[]{BaseColumns._ID, "schoolsName"});
        for (int i = 0; i < schoolslist.size(); i++) {
            if (schoolslist.get(i).toLowerCase().startsWith(query.toLowerCase()))
                c.addRow(new Object[]{i, schoolslist.get(i)});
        }
        arrayAdapter.changeCursor(c);
    }
}