package com.zypher.shortnotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*개발 진행중*/
        /*Button Chat_Button = findViewById(R.id.Chat_btn);*/
        /*Button Ai_Make_Button = findViewById(R.id.Ai_make_btn);*/
        /*Button Chat_Gpt_Ai_Button = findViewById(R.id.chat_gpt_ai_btn);*/

        /*이미지 버튼*/
        ImageView Note_Image_View = findViewById(R.id.Note_View);
        ImageView Calculator_Image_View = findViewById(R.id.calculator_View);
        ImageView News_Image_View = findViewById(R.id.news_View);
        ImageView YouTube_Image_View = findViewById(R.id.youtube_View);
        ImageView Agora_Image_View = findViewById(R.id.agora_View);
        ImageView Google_Navigation_View = findViewById(R.id.google_navigation_View);


        Note_Image_View.setOnClickListener(v -> {
            Log.d("MainActivity", "Image clicked");
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        Calculator_Image_View.setOnClickListener(v -> {
            Log.d("MainActivity", "Image clicked");
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        News_Image_View.setOnClickListener(v -> {
            Log.d("MainActivity", "Image clicked");
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        YouTube_Image_View.setOnClickListener(v -> {
            Log.d("MainActivity", "Image clicked");
            Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        Agora_Image_View.setOnClickListener(v -> {
            Log.d("MainActivity", "Image clicked");
            Intent intent = new Intent(MainActivity.this, AgoraActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        Google_Navigation_View.setOnClickListener(v -> {
            Log.d("MainActivity", "Image clicked");
            Intent intent = new Intent(MainActivity.this, MapNavigationActivity.class);
            startActivity(intent);
            finish();
            return;
        });

        /*Chat_Button.setOnClickListener(v -> {
            Log.d("MainActivity", "Button clicked");
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
            finish();
            return;
        });*/

        /*Ai_Make_Button.setOnClickListener(v -> {
            Log.d("MainActivity", "Button clicked");
            Intent intent = new Intent(MainActivity.this, AiartActivity.class);
            startActivity(intent);
            finish();
            return;
        });*/

        /*Chat_Gpt_Ai_Button.setOnClickListener(v -> {
            Log.d("MainActivity", "Button clicked");
            Intent intent = new Intent(MainActivity.this, ChatgptActivity.class);
            startActivity(intent);
            finish();
            return;
        });*/

    }
}
