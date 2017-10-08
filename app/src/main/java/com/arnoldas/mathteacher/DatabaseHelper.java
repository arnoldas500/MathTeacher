package com.arnoldas.mathteacher;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public  class DatabaseHelper {
    public DatabaseHelper(Context c)
    {
        db = new Database(c).getWritableDatabase();
    }

    private SQLiteDatabase db;

    public List<StudentDTO> GetStudentList() {
        Cursor cursor = db.rawQuery("SELECT id,name,additionLevel,subtractionLevel,multiplicationLevel,divisionLevel FROM student;", null);
        List<StudentDTO> retVal = new LinkedList<StudentDTO>();
        while (cursor.moveToNext())
        {
            StudentDTO dto = new StudentDTO();
            dto.id = cursor.getInt(0);
            dto.name = cursor.getString(1);
            dto.additionLevel = cursor.getInt(2);
            dto.subtractionLevel = cursor.getInt(3);
            dto.multiplicationLevel = cursor.getInt(4);
            dto.divisionLevel = cursor.getInt(5);
            retVal.add(dto);
        }
        cursor.close();
        return retVal;
    }

    public List<ExamDTO> GetExamList(int studentId) {
        Cursor cursor = db.rawQuery("SELECT id, date ," +
                "additionAttempted, additionSucceeded, additionLevel ," +
                "subtractionAttempted , subtractionSucceeded , subtractionLevel ," +
                "multiplicationAttempted , multiplicationSucceeded , multiplicationLevel ," +
                "divisionAttempted , divisionSucceeded , divisionLevel FROM exams WHERE studentId=" + studentId + ";", null);
        List<ExamDTO> retVal = new LinkedList<ExamDTO>();
        while (cursor.moveToNext())
        {
            ExamDTO dto = new ExamDTO();
            dto.id = cursor.getInt(0);
            dto.date = new Date(1000*cursor.getInt(1));
            dto.studentId = studentId;

            dto.additionAttempted = cursor.getInt(2);
            dto.additionSucceeded = cursor.getInt(3);
            dto.additionLevel = cursor.getInt(4);

            dto.subtractionAttempted = cursor.getInt(5);
            dto.subtractionSucceeded = cursor.getInt(6);
            dto.subtractionLevel = cursor.getInt(7);

            dto.multiplicationAttempted = cursor.getInt(8);
            dto.multiplicationSucceeded = cursor.getInt(9);
            dto.multiplicationLevel = cursor.getInt(10);

            dto.divisionAttempted = cursor.getInt(11);
            dto.divisionSucceeded = cursor.getInt(12);
            dto.divisionLevel = cursor.getInt(13);

            retVal.add(dto);
        }
        cursor.close();
        return retVal;
    }

    public ExamDTO CreateExam(ExamDTO in)
    {
        ContentValues values = new ContentValues();
        values.putNull("id" );
        values.put("date", in.date.getTime()/1000 );
        values.put("additionAttempted", in.additionAttempted );
        values.put("additionSucceeded", in.additionSucceeded );
        values.put("additionLevel", in.additionLevel );
        values.put("subtractionAttempted", in.subtractionAttempted );
        values.put("subtractionSucceeded", in.subtractionSucceeded );
        values.put("subtractionLevel", in.subtractionLevel );
        values.put("multiplicationAttempted", in.multiplicationAttempted );
        values.put("multiplicationSucceeded", in.multiplicationSucceeded );
        values.put("multiplicationLevel", in.multiplicationLevel );
        values.put("divisionAttempted", in.divisionAttempted );
        values.put("divisionSucceeded", in.divisionSucceeded );
        values.put("divisionLevel", in.divisionLevel );

        long newRowId = db.insert("exams", null, values);
        in.id = (int)newRowId;
        return in;
    }

    public StudentDTO CreateStudent(StudentDTO in)
    {
        ContentValues values = new ContentValues();
        values.putNull("id" );
        values.put("name", in.name );
        values.put("additionLevel", in.additionLevel );
        values.put("subtractionLevel", in.subtractionLevel );
        values.put("multiplicationLevel", in.multiplicationLevel );
        values.put("divisionLevel", in.divisionLevel );

        long newRowId = db.insert("student", null, values);
        in.id = (int)newRowId;
        return in;
    }

}
