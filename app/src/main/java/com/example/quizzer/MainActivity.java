package com.example.quizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name;
    public static final String EXTRA_NAME = "com.example.MultiScreenApp.extra.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void openActivity(View v){

        Intent intent = new Intent(this,Questions.class);
        name = (EditText) findViewById(R.id.name);
        String data = name.getText().toString();
        if (data.trim().length() == 0){
            Toast.makeText(this,"Name cannot be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast toast = Toast.makeText(this, "And the quiz begins!", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
            intent.putExtra(EXTRA_NAME, data);
//        The above two lines are used to pass the String that is entered in EditText name.
            startActivity(intent);
        }
    }
}