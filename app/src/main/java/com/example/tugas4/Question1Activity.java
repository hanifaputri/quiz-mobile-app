package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Question1Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    Button btnNext;
    RadioGroup options;
    ProgressBar progressBar;
    TextView txtProgress;

    private boolean isFilled;
    int score;
    int currNumber = 1;
    int totalNumber = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        options = (RadioGroup) findViewById(R.id.rg_answers);
        btnNext = (Button) findViewById(R.id.btn_next);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtProgress = (TextView) findViewById(R.id.percent);

        txtProgress.setText(currNumber + "/" + totalNumber);
        progressBar.setMax(totalNumber);
        progressBar.setProgress(currNumber);

        options.setOnCheckedChangeListener(this);

        // Send Score to next Intent
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFilled){
                    Toast.makeText(getApplicationContext(), "Please pick the answer first.", Toast.LENGTH_SHORT).show();
                } else {
//                Intent intent = new Intent(Question1Activity.this, ResultActivity.class);
                    Intent intent = new Intent(Question1Activity.this, Question2Activity.class);
                    intent.putExtra("PREV SCORE", score);
                    Log.d("#1 - Current Score", ""+score);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void onCheckedChanged(RadioGroup options, int checkedId){
        switch (checkedId){
            case R.id.rg_answers_1_1:
                    right();
                break;
            case R.id.rg_answers_1_2:
                    wrong();
                break;
            case R.id.rg_answers_1_3:
                    wrong();
                break;
        }
    }

    private void right(){
        this.score = 1;
        filled();
    }

    private void wrong(){
        this.score = 0;
        filled();
    }

    private void filled(){
        this.isFilled = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}