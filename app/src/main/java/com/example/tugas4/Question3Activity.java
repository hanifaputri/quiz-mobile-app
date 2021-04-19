package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Question3Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    Button btnNext;
    RadioGroup options;
    ProgressBar progressBar;
    TextView txtProgress;

    private boolean isFilled;
    int score;
    int currScore;
    int currNumber = 3;
    int totalNumber = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        options = (RadioGroup) findViewById(R.id.rg_answers_3);
        btnNext = (Button) findViewById(R.id.btn_next_3);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtProgress = (TextView) findViewById(R.id.percent);

        txtProgress.setText(currNumber + "/" + totalNumber);
        options.setOnCheckedChangeListener(this);

        // Get intent data
        Intent intent = getIntent();
        int prevScore = intent.getIntExtra("PREV SCORE",0);

        progressBar.setMax(totalNumber);
        progressBar.setProgress(currNumber);

        // Send Score to next Intent
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFilled){
                    Toast.makeText(getApplicationContext(), "Please pick the answer first.", Toast.LENGTH_SHORT).show();
                } else {
                    currScore = score + prevScore;
//                Toast.makeText(getApplicationContext(), "Curr Score "+ currScore, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Question3Activity.this, Question4Activity.class);
                    intent.putExtra("PREV SCORE", currScore);
                    Log.d("#3 - Current Score", "" + currScore);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void onCheckedChanged(RadioGroup options, int checkedId){
        switch (checkedId){
            case R.id.rg_answers_3_1:
                wrong();
                break;
            case R.id.rg_answers_3_2:
                right();
                break;
            case R.id.rg_answers_3_3:
                wrong();
                break;
        }
    }

    public void right(){
        this.score = 1;
        filled();
    }

    public void wrong(){
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