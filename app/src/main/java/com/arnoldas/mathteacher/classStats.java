package com.arnoldas.mathteacher;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class classStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DatabaseHelper ctx = new DatabaseHelper(this);
        ExamDTO dto = ctx.GetAllExxamTotals();
        ((TextView)findViewById(R.id.additionAverage)).setText(GetPercentage(dto.additionAttempted,dto.additionSucceeded));
        ((TextView)findViewById(R.id.subtractionAverage)).setText(GetPercentage(dto.subtractionAttempted,dto.subtractionSucceeded));
        ((TextView)findViewById(R.id.multiplicationAverage)).setText(GetPercentage(dto.multiplicationAttempted,dto.multiplicationSucceeded));
        ((TextView)findViewById(R.id.divisionAverage)).setText(GetPercentage(dto.divisionAttempted,dto.divisionSucceeded));
        ((TextView)findViewById(R.id.overallAverage)).setText(GetPercentage(
                dto.additionAttempted + dto.subtractionAttempted + dto.multiplicationAttempted +dto.divisionAttempted,
                dto.additionSucceeded + dto.subtractionSucceeded + dto.multiplicationSucceeded +dto.divisionSucceeded
                ));
    }

    private String GetPercentage(int attempt, int succeeded)
    {
        if (attempt == 0 && succeeded == 0)
            return "N/A";

        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(1);

        return defaultFormat.format(1.0*succeeded/attempt);
    }
}
