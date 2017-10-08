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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action2", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        bind();
    }

    public void bind()
    {
        DatabaseHelper db = new DatabaseHelper(this);
        List<StudentDTO> students = db.GetStudentList();
        String[] studentList = new String[students.size()];
        int i=0;
        for ( StudentDTO student : students)
        {
            studentList[i++] = student.name;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, studentList);
        ListView listView = (ListView) findViewById(R.id.studentListView);
        listView.setAdapter(adapter);
    }

    public void addStudent(View view) {
        DatabaseHelper db = new DatabaseHelper(this);
        StudentDTO s = new StudentDTO();
        s.name = ((EditText)findViewById(R.id.newStudentText)).getText().toString();
        db.CreateStudent(s);
        bind();
    }
}
