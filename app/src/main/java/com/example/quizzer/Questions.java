package com.example.quizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Questions extends AppCompatActivity {
    TextView textView;
    TextView question;
    Button a, b, c, d;
    int score = 0;
    TextView scoreCard;
    String[] questions = {"Who is the founder of Facebook?", "What is the gender neutral term for batsman and batswoman?", "Who invented Aeroplane?", "What is the capital of Afghanistan?", "Who are the Missile man and Iron man of India(respectively)?", "What is the highest score in a T20 match?", "What is the symbol of Chinese Yuan?", "1 AUD is approximately equal to __ INR.", "What is the scientific name of Ostrich?", "___ was invented by Karl Benz."};

    String[] answers = {"Mark Zuckerberg", "Batter", "Wright Brothers", "Kabul", "APJ Abdul Kalam and Vallabhai Patel", "278/3", "¥", "53", "Struthio Camelus", "Car"};
    String[] buttonA = {"Mark Zuckerberg", "Watchman", "Russo Brothers", "Morocco", "Atal Bihari Vajpayee and Rajiv Gandhi", "265/3", "€", "49", "Macaca Fascicularis", "Wheel"};
    String[] buttonB = {"Steve Jobs", "No such term exists", "Nicolas Tesla", "Kabul", "Ajit Doval and APJ Abdul Kalam", "258/6", "$", "55", "Equus africanus asinus", "Nuclear Reactor"};
    String[] buttonC = {"Linus Torvalds", "Batter", "Wright Brothers", "Kolkata", "APJ Abdul Kalam and Ajit Doval", "278/3", "£", "52", "Struthio Camelus", "Car"};
    String[] buttonD = {"Tim Buchalka", "Player", "Sir Garfield Sobers", "Kandahar", "APJ Abdul Kalam and Vallabhai Patel", "273/3", "¥", "53", "Pongo", "Television"};
    int index = 0;


    Button buttonExit;
    Button playAgain;
    Button popup;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        String name;
        textView = (TextView) findViewById(R.id.textViewName);
        Intent intent = getIntent();
        name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        textView.setText(name + "'s Quiz");
        scoreCard = (TextView) findViewById(R.id.score);
        scoreCard.setVisibility(View.INVISIBLE);
        a = (Button) findViewById(R.id.optionA);
        b = (Button) findViewById(R.id.optionB);
        c = (Button) findViewById(R.id.optionC);
        d = (Button) findViewById(R.id.optionD);
        buttonExit = (Button) findViewById(R.id.exit);
        playAgain = (Button) findViewById(R.id.playAgain);
        popup  = (Button) findViewById(R.id.buttonPopup);
        result = (TextView) findViewById(R.id.textView2);
        question = (TextView) findViewById(R.id.question);

        result.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        popup.setVisibility(View.INVISIBLE);
        buttonExit.setVisibility(View.INVISIBLE);
        a.setText(buttonA[index]);
        b.setText(buttonB[index]);
        c.setText(buttonC[index]);
        d.setText(buttonD[index]);
        question.setText(questions[index]);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                Button x = (Button) view;
                text = x.getText().toString();
                if (index<questions.length) {
                    if (text.equals(answers[index])) {
                        score++;
                        toastCorrect();
                        scoreCard.setVisibility(View.VISIBLE);
                        scoreCard.setText("Your score : "+score);
                        index++;
                    }
                    else {
                        toastWrong();
                        Toast.makeText(Questions.this, "Your score is "+score, Toast.LENGTH_SHORT).show();
                        index = questions.length;
                        result.setVisibility(View.VISIBLE);
                        playAgain.setVisibility(View.VISIBLE);
                        popup.setVisibility(View.VISIBLE);
                        buttonExit.setVisibility(View.VISIBLE);
                        result.setText("Score : " + score);

                    }
                    if (index<questions.length){
                        question.setText(questions[index]);
                        a.setText(buttonA[index]);
                        b.setText(buttonB[index]);
                        c.setText(buttonC[index]);
                        d.setText(buttonD[index]);
                        x.setBackgroundResource(R.drawable.img);
                    }
                    else{
                        Toast.makeText(Questions.this, "Your score is "+score, Toast.LENGTH_SHORT).show();
                        result.setText("Score : " + score);
                        result.setVisibility(View.VISIBLE);
                        playAgain.setVisibility(View.VISIBLE);
                        popup.setVisibility(View.VISIBLE);
                        buttonExit.setVisibility(View.VISIBLE);

                    }
                }

            }
        };

        a.setOnClickListener(listener);
        b.setOnClickListener(listener);
        c.setOnClickListener(listener);
        d.setOnClickListener(listener);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playingAgain();
            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exitApp();
            }
        });
    }
    private void toastCorrect(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_correct, (ViewGroup) findViewById(R.id.correct_linear));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    private void toastWrong(){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_wrong, (ViewGroup) findViewById(R.id.wrong_linear));
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    private void playingAgain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private void exitApp(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}