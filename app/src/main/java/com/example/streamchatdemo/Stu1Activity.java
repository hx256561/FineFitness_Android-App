package com.example.streamchatdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Stu1Activity extends AppCompatActivity {

    private Button aBtnToCoach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu1);
        aBtnToCoach = (Button) findViewById(R.id.btn_to_ch);
        aBtnToCoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu1Activity.this, coach1Activity.class);
                startActivity(intent);
            }
        });
    }
}