package com.arnoldas.mathteacher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

class ManageStudentsAdapter extends BaseAdapter {

    private ManageStudents context;
    private List<StudentDTO> studentDTOs;

    private static LayoutInflater inflater=null;
    ManageStudentsAdapter(ManageStudents incomingContext, List<StudentDTO> students) {
        studentDTOs = students;
        context=incomingContext;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return studentDTOs.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.managestudententry, null);
        ((TextView) rowView.findViewById(R.id.studentName)).setText(studentDTOs.get(position).name);
        rowView.findViewById(R.id.deleteButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHelper db = new DatabaseHelper(context);
                        db.DeleteStudent(studentDTOs.get(position));
                        notifyDataSetInvalidated();
                        context.bind();
                    }
                }
        );
        return rowView;
    }

}
