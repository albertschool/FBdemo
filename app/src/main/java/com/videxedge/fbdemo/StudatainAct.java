package com.videxedge.fbdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import static com.videxedge.fbdemo.FBref.refFlags;
import static com.videxedge.fbdemo.FBref.refStudents;

/**
 * This activity enter/edit data in the students
 */

public class StudatainAct extends AppCompatActivity {

    EditText eTGradeClass, eTStuClass, eTStuName, eTStuID;
    int GradeClass, StuClass;
    String str, StuName, StuID, oldStuId;
    Student student;
    public Boolean dataed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studatain);

        eTGradeClass = (EditText) findViewById(R.id.eTGradeClass);
        eTStuClass = (EditText) findViewById(R.id.eTStuClass);
        eTStuName = (EditText) findViewById(R.id.eTStuName);
        eTStuID = (EditText) findViewById(R.id.eTStuID);

        dataed = false;

        refFlags.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                dataed = (Boolean) dS.child("Dataedit").getValue();
                if (dataed) {
                    Intent gi=getIntent();
                    eTGradeClass.setText(gi.getStringExtra("stuGradeclass"));
                    eTStuClass.setText(gi.getStringExtra("stuClass"));
                    eTStuName.setText(gi.getStringExtra("stuName"));
                    oldStuId=gi.getStringExtra("stuID");
                    eTStuID.setText(oldStuId);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void stuinsert(View view) {
        refFlags.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dS) {
                dataed = (Boolean) dS.child("Dataedit").getValue();
                if (dataed) {
                    refStudents.child(oldStuId).removeValue();
                    refFlags.child("Dataedit").setValue(false);
                }
                str=eTGradeClass.getText().toString();
                GradeClass=Integer.parseInt(str);
                str=eTStuClass.getText().toString();
                StuClass=Integer.parseInt(str);
                StuName=eTStuName.getText().toString();
                StuID=eTStuID.getText().toString();
                student=new Student(GradeClass,StuClass,StuName,StuID);
                refStudents.child(StuID).setValue(student);
                finish();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
