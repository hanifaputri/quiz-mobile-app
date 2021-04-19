package com.example.tugas4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    Button btnResult, btnReattempt;
    TextView tvScore, tvResultSummary;

    int totalNumber = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        btnResult = findViewById(R.id.btn_shareResult);
        btnReattempt = findViewById(R.id.btn_reattempt);
        tvScore = findViewById(R.id.tv_score);
        tvResultSummary = findViewById(R.id.tv_text2);

        // Get intent data
        Intent intent = getIntent();
        int rightAnswer = intent.getIntExtra("PREV SCORE",0);
        int totalScore = rightAnswer * 100 / totalNumber;

        tvScore.setText(String.valueOf(totalScore));
        tvResultSummary.setText("You answered "+ rightAnswer +" out of "+ totalNumber +" right questions.");

        // Send Email
btnResult.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"rahimahanifa@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, "Nilai Quiz");
        email.putExtra(Intent.EXTRA_TEXT, "Nilai quiznya adalah " + totalScore);

        try {
            startActivity(Intent.createChooser(email, "Send mail..."));
            Log.i("Status", "Sending message");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ResultActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
});

        btnReattempt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}