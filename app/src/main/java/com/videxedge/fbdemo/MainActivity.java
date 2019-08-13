package com.videxedge.fbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.videxedge.fbdemo.FBref.refFlags;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refFlags.child("Dataedit").setValue(false);

    }

    public void studentin(View view) {
        Intent t=new Intent(this,StudatainAct.class);
        startActivity(t);
    }

    public void studentdis(View view) {
        Intent t=new Intent(this,StuDisAct.class);
        startActivity(t);
    }
}
