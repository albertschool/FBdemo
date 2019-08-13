package com.videxedge.fbdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import static com.videxedge.fbdemo.FBref.refFlags;
import static com.videxedge.fbdemo.FBref.refStudents;

public class StuDisAct extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String str1, str2,strtmp;
    ListView lv;
    Student stuTmp;
    ArrayList<String> stuList = new ArrayList<String>();
    ArrayList<Student> stuValues = new ArrayList<Student>();
    ArrayAdapter<String> adp;
    ValueEventListener stuListener;
    AlertDialog.Builder adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_dis);

        lv = (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ValueEventListener stuListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : dS.getChildren()) {
                    str1 = (String) data.getKey();
                    Student stuTmp = data.getValue(Student.class);
                    stuValues.add(stuTmp);
                    str2 = stuTmp.getstuName();
                    stuList.add(str1+" "+str2);
                }
                adp = new ArrayAdapter<String>(StuDisAct.this,R.layout.support_simple_spinner_dropdown_item, stuList);
                lv.setAdapter(adp);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        };
        refStudents.addValueEventListener(stuListener);


    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long id) {
        adb = new AlertDialog.Builder(this);
        adb.setTitle(stuValues.get(pos).getstuName());
        final String strstuID=stuValues.get(pos).getstuID();
        strtmp="Name: "+stuValues.get(pos).getstuName()+"\n";
        strtmp+="ID: "+stuValues.get(pos).getstuID()+"\n";
        strtmp+="Class: "+String.valueOf(stuValues.get(pos).getgradeClass())+" ";
        strtmp+=String.valueOf(stuValues.get(pos).getstuClass());
        adb.setMessage(strtmp);
        adb.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog.Builder adb1 = new AlertDialog.Builder(StuDisAct.this);
                adb1.setTitle("Are you sure to delete:");
                adb1.setMessage(strtmp);
                adb1.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        refStudents.child(strstuID).removeValue();
                    }
                });
                adb1.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog ad1 = adb1.create();
                ad1.show();
            }
        });
        adb.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                refFlags.child("Dataedit").setValue(true);
                Intent t= new Intent(StuDisAct.this,StudatainAct.class);
                t.putExtra("stuName",stuValues.get(pos).getstuName());
                t.putExtra("stuID",stuValues.get(pos).getstuID());
                t.putExtra("stuGradeclass",String.valueOf(stuValues.get(pos).getgradeClass()));
                t.putExtra("stuClass",String.valueOf(stuValues.get(pos).getstuClass()));
                EndAct();
                dialogInterface.cancel();
                startActivity(t);
            }
        });
        adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    public void Back(View view) {
        EndAct();
        finish();
    }

    public void EndAct () {
        if (stuListener!=null) {
            refStudents.removeEventListener(stuListener);
        }
    }
}
