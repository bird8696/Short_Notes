package com.zypher.shortnotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Note_Button = findViewById(R.id.Note_btn);
        Button Chat_Button = findViewById(R.id.Chat_btn);
        Button Calculator_Button = findViewById(R.id.Cal_btn);

        Note_Button.setOnClickListener(v -> {
            Log.d("MainActivity", "Button clicked");
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        Chat_Button.setOnClickListener(v -> {
            Log.d("MainActivity", "Button clicked");
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        Calculator_Button.setOnClickListener(v -> {
            Log.d("MainActivity", "Button clicked");
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
            finish();
            return;
        });



    }
}
