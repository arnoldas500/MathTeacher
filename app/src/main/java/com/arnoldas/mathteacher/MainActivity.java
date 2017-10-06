package com.arnoldas.mathteacher;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //hiding the student button after clicking it
    Button studentStartButton;
    int ansLoc;
    //answers array list
    ArrayList<Integer> answers = new ArrayList<Integer>();

    //student start button
    public void studentStart(View view){
        studentStartButton.setVisibility(View.INVISIBLE);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //find the student start button on starting/ creating app
        studentStartButton = (Button)findViewById(R.id.studentStartButton);
        TextView resultTextView = (TextView)findViewById(R.id.resultTextView);

        Button button0 = (Button)findViewById(R.id.button0);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);

        Random randInt = new Random();

        int first = randInt.nextInt(11);
        int second = randInt.nextInt(11);
        System.out.println("first : "+first);
        System.out.println("second : "+second);

        resultTextView.setText(Integer.toString(first)+" + "+Integer.toString(second));
        //randomly place for correct answer
        ansLoc = randInt.nextInt(4);

        //for scenario of having the correct answer accidently randomly chosen
        int ansWrong;
        for(int a = 0 ; a<4 ; a++){
            if(a == ansLoc)
                answers.add(first+second);
            else {
                ansWrong = randInt.nextInt(31);
                while(ansWrong == first+second){
                    ansWrong = randInt.nextInt(31);
                }
                answers.add(ansWrong);
            }
        }
        //updating answer buttons with numbers from above
        System.out.println("0 : "+answers.get(0));
        System.out.println("1 : "+answers.get(1));
        System.out.println("2 : "+answers.get(2));
        System.out.println("3 : "+answers.get(3));

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
