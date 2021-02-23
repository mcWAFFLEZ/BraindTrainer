package com.example.braindtrainer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridLayout myGridLayout;
    Button goButton;
    Button playAgainButton;
    TextView timeTextView;
    TextView scoreTextView;
    TextView questionTextView;
    TextView notifyTextView;
    Random rand = new Random();
    int numberOfQuestions = 15;
    int questionsAnsweredRight = 0;
    int timeOutSeconds;
    int num1, num2;
    int realAns;


    public void goClick(View view){

        timeOutSeconds = 30;

        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeOutSeconds--;

                timeTextView.setText(String.valueOf(timeOutSeconds+" s"));
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);

                timeTextView.setVisibility(View.INVISIBLE);

                questionTextView.setVisibility(View.INVISIBLE);

                myGridLayout.setVisibility(View.INVISIBLE);

                scoreTextView.setVisibility(View.INVISIBLE);

                notifyTextView.setText("You answered "+questionsAnsweredRight+"/"+numberOfQuestions+" questions!");

                Log.i("Timer", "Done.");
            }
        }.start();

        Log.i("goButton","Pressed.");

        timeTextView.setVisibility(View.VISIBLE);

        timeTextView.setText(timeOutSeconds+" s");

        scoreTextView.setVisibility(View.VISIBLE);

        scoreTextView.setText(questionsAnsweredRight+"/"+numberOfQuestions);

        questionTextView.setVisibility(View.VISIBLE);

        randNums();

        randPlaces();

        notifyTextView.setVisibility(View.VISIBLE);

        notifyTextView.setText("");

        goButton.setVisibility(View.INVISIBLE);

        myGridLayout.setVisibility(View.VISIBLE);
    }

    public void childClick(View view){

        TextView child = (TextView) view;

        int childNum = Integer.valueOf(String.valueOf(child.getText()));

        if (childNum == realAns){

            questionsAnsweredRight++;

            scoreTextView.setText(questionsAnsweredRight+"/"+numberOfQuestions);

            notifyTextView.setText("Correct!!!");
        }else{

            notifyTextView.setText("Wrong!!!");
        }

        randNums();

        randPlaces();

        Log.i("childClick", "Child number "+child.getTag()+" pressed.");
    }

    public void randNums(){

        num1 = rand.nextInt(49);

        num2 = rand.nextInt(50);

        questionTextView.setText(num1+"+"+num2);

        realAns = num1 + num2;
    }

    public void randPlaces(){

        int gridChildren = myGridLayout.getChildCount();

        int[] gridState = new int[gridChildren];

        int randomPick = rand.nextInt(4);

        gridState[randomPick] = 1;

        TextView child = (TextView) myGridLayout.getChildAt(randomPick);

        child.setText(String.valueOf(realAns));

        for (int i = 0 ; i < gridChildren ; i++){

            if(gridState[i] != 1){
                child = (TextView) myGridLayout.getChildAt(i);
                child.setText(String.valueOf(rand.nextInt(99)));
                gridState[i] = 1;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainButton = findViewById(R.id.playAgainButton);
        myGridLayout = findViewById(R.id.myGridLayout);
        goButton = findViewById(R.id.goButton);
        timeTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        questionTextView = findViewById(R.id.questionTextView);
        notifyTextView = findViewById(R.id.notifyTextView);
    }
}
