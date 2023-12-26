package com.zypher.shortnotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Main_gameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        ImageView Rock_Image_View = findViewById(R.id.Rock_View);

        Rock_Image_View.setOnClickListener(v -> {
            Log.d("Main_gameActivity", "Image clicked");
            Intent intent = new Intent(Main_gameActivity.this, Snake_gameActivity.class);
            startActivity(intent);
            finish();
            return;
        });
    }
}