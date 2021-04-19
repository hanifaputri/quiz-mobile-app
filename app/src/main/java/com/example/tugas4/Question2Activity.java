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

public class Question2Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    Button btnNext;
    RadioGroup options;
    ProgressBar progressBar;
    TextView txtProgress;

    private boolean isFilled;
    int score;
    int currScore;
    int currNumber = 2;
    int totalNumber = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        options = (RadioGroup) findViewById(R.id.rg_answers_2);
        btnNext = (Button) findViewById(R.id.btn_next_2);
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
                    Toast.makeText(getApplicationContext(), "Please pick the answer first.", Toast.LENGTH_LONG).show();
                } else {
                    currScore = score + prevScore;
                    Intent intent = new Intent(Question2Activity.this, Question3Activity.class);
                    intent.putExtra("PREV SCORE", currScore);
                    Log.d("#2 - Current Score", ""+currScore);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void onCheckedChanged(RadioGroup options, int checkedId){
        switch (checkedId){
            case R.id.rg_answers_2_1:
                wrong();
                break;
            case R.id.rg_answers_2_2:
                wrong();
                break;
            case R.id.rg_answers_2_3:
                right();
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