package com.zypher.shortnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Snake_gameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_game);

        Button btnPlayWithOther = findViewById(R.id.btnPlayWithOther);
        btnPlayWithOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Snake_gameActivity.this, PlayWithOtherActivity.class);
                startActivity(intent);
            }
        });

        Button btnPlayWithComp = findViewById(R.id.btnPlayWithComputer);
        btnPlayWithComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO.............
            }
        });

        TextView tvInstruction = findViewById(R.id.tvInstruction);
        tvInstruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO.............
            }
        });
    }
}