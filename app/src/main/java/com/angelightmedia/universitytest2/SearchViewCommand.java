package com.angelightmedia.universitytest2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.provider.BaseColumns;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchViewCommand extends AppCompatActivity {

    DBHelper dbHelper;
    ArrayList<String> schoolslist;
    SimpleCursorAdapter arrayAdapter;


    public void useSearchView(SearchView mySearchview){  // dont think I used this class at all o
        dbHelper = new DBHelper(this, null, null, 1);

        schoolslist = dbHelper.getallSchools(); //get all schools from db

        //mySearchview = findViewById(R.id.bt_search_fedschActivity);
        mySearchview.setQueryHint("type name of school");
        final String[] from = new String[] {"schoolsName"};
        final int[] to = new int[] {android.R.id.text1};
        //myListView = findViewById(R.id.searchForLV);
        arrayAdapter = new SimpleCursorAdapter(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, null,
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
                @SuppressLint("Range") String txt = cursor.getString(cursor.getColumnIndex("schoolsName"));
                mySearchview.setQuery(txt, true);
                Intent intent = new Intent(getApplicationContext(), School_Details.class);
                intent.putExtra("sname", txt);
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
        final MatrixCursor c = new MatrixCursor(new String[]{ BaseColumns._ID, "schoolsName" });
        for (int i=0; i<schoolslist.size(); i++) {
            if (schoolslist.get(i).toLowerCase().startsWith(query.toLowerCase()))
                c.addRow(new Object[] {i, schoolslist.get(i)});
        }
        arrayAdapter.changeCursor(c);
    }



}
