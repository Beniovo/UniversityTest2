package com.angelightmedia.universitytest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button fedUniesbt;
    Button stateUniesbt;
    Button privateUniesbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fedUniesbt = findViewById(R.id.fedUniesbt);
        stateUniesbt = findViewById(R.id.stateUnibt);
        privateUniesbt = findViewById(R.id.privateUnibt);

        fedUniesbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(), FedSchoolsActivity.class);
                startActivity(i);
            }
        });

        stateUniesbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StateSchools.class);
                startActivity(i);
            }
        });

        privateUniesbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PrivateSchools.class);
                startActivity(i);
            }
        });


    }
}