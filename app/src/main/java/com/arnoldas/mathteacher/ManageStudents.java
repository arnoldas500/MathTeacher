package com.arnoldas.mathteacher;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ManageStudents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bind();
    }

    public void bind()
    {
        DatabaseHelper db = new DatabaseHelper(this);
        List<StudentDTO> students = db.GetStudentList();
        ((ListView) findViewById(R.id.studentListView)).setAdapter(new ManageStudentsAdapter(this,students));
    }

    public void addStudent(View view) {
        DatabaseHelper db = new DatabaseHelper(this);
        StudentDTO s = new StudentDTO();
        EditText newStudentText = (EditText) findViewById(R.id.newStudentText);
        s.name = newStudentText.getText().toString();
        db.CreateStudent(s);
        newStudentText.setText("");
        bind();
    }

    public void removeStudent(View view) {
        DatabaseHelper db = new DatabaseHelper(this);

    }
}
