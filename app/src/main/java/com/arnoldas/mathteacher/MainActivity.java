package com.arnoldas.mathteacher;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //MainActivity.java

    //hiding the student button after clicking it
    Button studentStartButton;
    Button restartButton;
    int ansLoc;
    int numCorrect = 0;
    //double score = 0.0;
    TextView textViewResult;
    //answers array list
    ArrayList<Integer> answers = new ArrayList<>();
    int numQuestions = 0;
    TextView textViewPoints;
    TextView textViewSum;
    Button button0 ;
    Button button1;
    Button button2;
    Button button3 ;
    TextView textViewTimer;
    DatabaseHelper dbHelper;
    StudentDTO sDTO;

    //left to do *generate new question, get timmer working,
    public void newQuestion(int additionLevel, int subtractionLevel, int multiplicationLevel, int divisionLevel) {


        //randomly choose i to 4 where 1 is addition and 4 is div
        //if one of them is zero then just rechoose
        //then inside choose lelvel based on level passed
        Random randInt = new Random();
        int operand = randInt.nextInt(4);
        operand +=1;
        System.out.println("operand is "+operand);

        if(operand ==1){
            //add
            if(additionLevel>0){
                int first = randInt.nextInt(10*additionLevel);
                int second = randInt.nextInt(10*additionLevel);
                System.out.println("first : "+first);
                System.out.println("second : "+second);


                textViewSum.setText(Integer.toString(first)+" + "+Integer.toString(second));
                //randomly place for correct answer
                ansLoc = randInt.nextInt(4);


                //for scenario of having the correct answer accidently randomly chosen
                int ansWrong;
                answers.clear();
                for(int a = 0 ; a<4 ; a++){
                    if(a == ansLoc)
                        answers.add(first+second);
                    else {
                        ansWrong = randInt.nextInt(10*additionLevel);
                        while(ansWrong == first+second){
                            ansWrong = randInt.nextInt(10*additionLevel);
                        }
                        answers.add(ansWrong);
                    }
                }
            }
        }else if(operand ==2){
            //sub
            if(subtractionLevel>0){
                int first = randInt.nextInt(10*subtractionLevel);
                int second = randInt.nextInt(10*subtractionLevel);
                System.out.println("first : "+first);
                System.out.println("second : "+second);


                textViewSum.setText(Integer.toString(first)+" - "+Integer.toString(second));
                //randomly place for correct answer
                ansLoc = randInt.nextInt(4);


                //for scenario of having the correct answer accidently randomly chosen
                int ansWrong;
                answers.clear();
                for(int a = 0 ; a<4 ; a++){
                    if(a == ansLoc)
                        answers.add(first-second);
                    else {
                        ansWrong = randInt.nextInt(10*subtractionLevel);
                        while(ansWrong == first-second){
                            ansWrong = randInt.nextInt(10*subtractionLevel);
                        }
                        answers.add(ansWrong);
                    }
                }
            }
        }else if(operand==3){
            //mult
            if(multiplicationLevel>0){
                int first = randInt.nextInt(5*multiplicationLevel);
                int second = randInt.nextInt(5*multiplicationLevel);
                //fix miult by zero and one
                first +=2;
                second +=2;
                System.out.println("first : "+first);
                System.out.println("second : "+second);


                textViewSum.setText(Integer.toString(first)+" * "+Integer.toString(second));
                //randomly place for correct answer
                ansLoc = randInt.nextInt(4);


                //for scenario of having the correct answer accidently randomly chosen
                int ansWrong;
                answers.clear();
                for(int a = 0 ; a<4 ; a++){
                    if(a == ansLoc)
                        answers.add(first*second);
                    else {
                        ansWrong = randInt.nextInt(5*multiplicationLevel+1);
                        while(ansWrong == first*second){
                            ansWrong = randInt.nextInt(5*multiplicationLevel+2);
                        }
                        answers.add(ansWrong);
                    }
                }
            }
        }else if (operand==4){
            //div
            if(divisionLevel>0){
                int first = randInt.nextInt(5*divisionLevel);
                int second = randInt.nextInt(5*divisionLevel);
                //fix div by zero
                first +=1;
                second +=1;
                System.out.println("first : "+first);
                System.out.println("second : "+second);


                textViewSum.setText("Round\n"+Integer.toString(first)+" / "+Integer.toString(second));
                //randomly place for correct answer
                ansLoc = randInt.nextInt(4);


                //for scenario of having the correct answer accidently randomly chosen
                int ansWrong;
                answers.clear();
                for(int a = 0 ; a<4 ; a++){
                    if(a == ansLoc)
                        answers.add(first/second);
                    else {
                        ansWrong = randInt.nextInt(5*divisionLevel+1);
                        while(ansWrong == first/second){
                            ansWrong = randInt.nextInt(5*divisionLevel+2);
                        }
                        answers.add(ansWrong);
                    }
                }
            }

        }else{
            System.out.println("error picking");
        }

/*
        //if(enum)
        int first = randInt.nextInt(11);
        int second = randInt.nextInt(11);
        System.out.println("first : "+first);
        System.out.println("second : "+second);


        textViewSum.setText(Integer.toString(first)+" + "+Integer.toString(second));
        //randomly place for correct answer
        ansLoc = randInt.nextInt(4);


        //for scenario of having the correct answer accidently randomly chosen
        int ansWrong;
        answers.clear();
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
*/



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


    //buttons now clickable and returning correct location w/o crashing
    //method to print out if answer was selected correct or not and updates score
    public void selectAns(View view){
        //printing each tag to log when clicked on in app
        Log.i("Tag", (String) view.getTag());

        if(view.getTag().toString().equals(Integer.toString(ansLoc))){
            //checking if log prints correct if select correct ans
            Log.i("correct", "correct");
            //add 1 to the number of correct choices when user selects correct answer and then generate new question
            numCorrect++;
            textViewResult.setText(R.string.correctAnswer);


        }else
            textViewResult.setText(R.string.wrongAnswer);
        numQuestions++;
        textViewPoints.setText(Integer.toString(numCorrect)+ " / "+Integer.toString(numQuestions));
//hey Chirag when you pass the student DTO update these values to reflect the selected student
        StudentDTO sDTO = new StudentDTO();
        //time be in seconds already do the conversion so just pass 30 or whaterv for what seconds you want
        int testTime = sDTO.testTime;
        int additionLevel = sDTO.additionLevel;
        int subtractionLevel = sDTO.subtractionLevel;
        int multiplicationLevel = sDTO.multiplicationLevel;
        int divisionLevel = sDTO.divisionLevel;
        System.out.println("\n****  ****");
        System.out.println("**** test timne is "+testTime+" add lvl: "+additionLevel+" mult lvl: "+ multiplicationLevel+" sub lvl "+subtractionLevel+" div lvl "+divisionLevel);

        //Chirag chnage these when you pass studentDTO later
        testTime = 2;
        additionLevel = 2;
        subtractionLevel = 1;
        multiplicationLevel = 1;
        divisionLevel = 1;
        newQuestion(additionLevel, subtractionLevel, multiplicationLevel, divisionLevel);
    }

    //Play again button
    public void restart(View view){
        //set score back to zero
        numCorrect = 0;
        //num questions back to zero
        numQuestions = 0;
        //textViewTimer.setText("30s");
        textViewResult.setText("");
        textViewPoints.setText("0/0");
        restartButton.setVisibility(View.INVISIBLE);



        //getting time set for test
        //DatabaseHelper.class.getContentValues(10);

        //List<StudentDTO> studentDTOs = dbHelper.GetStudentList();
        //System.out.println("********* "+studentDTOs.get(1));

        //hey Chirag when you pass the student DTO update these values to reflect the selected student
        StudentDTO sDTO = new StudentDTO();
        //time be in seconds already do the conversion so just pass 30 or whaterv for what seconds you want
        int testTime = sDTO.testTime;
        int additionLevel = sDTO.additionLevel;
        int subtractionLevel = sDTO.subtractionLevel;
        int multiplicationLevel = sDTO.multiplicationLevel;
        int divisionLevel = sDTO.divisionLevel;
        System.out.println("\n****  ****");
        System.out.println("**** test timne is "+testTime+" add lvl: "+additionLevel+" mult lvl: "+ multiplicationLevel+" sub lvl "+subtractionLevel+" div lvl "+divisionLevel);

        //Chirag chnage these when you pass studentDTO later
        testTime = 10;
        additionLevel = 2;
        subtractionLevel = 1;
        multiplicationLevel = 1;
        divisionLevel = 1;


        //generating new question
        newQuestion(additionLevel, subtractionLevel, multiplicationLevel, divisionLevel);

        //update timer
        new CountDownTimer(testTime*999, 999) {


            @Override
            public void onTick(long l) {
                textViewTimer.setText(String.format(getString(R.string.sec), String.valueOf(l / 1000)));
            }

            @Override
            public void onFinish() {
                restartButton.setVisibility(View.VISIBLE);
                textViewTimer.setText(R.string.done);
                double score = numQuestions == 0 ? 0.0 : (1.0* numCorrect  / numQuestions) * 100.0;
					/*
					int wrong = numQuestions - numCorrect;
					int per = 100 - (Math.round((100/numQuestions)*wrong));
					//100 - (Round((100/Questions)*Wrong))
					System.out.println("my score is " + per);
					int total = 100;
					float percentage = (float)(score * 100/ total);
					*/
                textViewResult.setText("Grade : " +Integer.toString((int) score) +"%");

            }
        }.start();

    }

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

        //find the student start button on starting/ creating app
        studentStartButton = (Button)findViewById(R.id.studentStartButton);
        textViewSum = (TextView)findViewById(R.id.textViewSum);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        textViewResult = (TextView)findViewById(R.id.textViewResult);
        textViewTimer = (TextView)findViewById(R.id.textViewTimer);
        textViewPoints = (TextView)findViewById(R.id.textViewPoints);
        restartButton  = (Button)findViewById(R.id.restartButton);

        //newQuestion();
        restart(findViewById(R.id.restartButton));
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

    public void manageStudents(View view) {
        Intent intent = new Intent(this, ManageStudents.class);
        startActivity(intent);
    }
}
