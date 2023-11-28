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
        Button News_Button = findViewById(R.id.News_btn);
        Button Ai_Make_Button = findViewById(R.id.Ai_make_btn);
        Button YouTube_Button = findViewById(R.id.youtube_btn);
        Button Agora_Button = findViewById(R.id.agora_btn);

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

        News_Button.setOnClickListener(v -> {
            Log.d("MainActivity", "Button clicked");
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        Ai_Make_Button.setOnClickListener(v -> {
            Log.d("MainActivity", "Button clicked");
            Intent intent = new Intent(MainActivity.this, AiartActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        YouTube_Button.setOnClickListener(v -> {
            Log.d("MainActivity", "Button clicked");
            Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        Agora_Button.setOnClickListener(v -> {
            Log.d("MainActivity", "Button clicked");
            Intent intent = new Intent(MainActivity.this, AgoraActivity.class);
            startActivity(intent);
            finish();
            return;
        });

    }
}
