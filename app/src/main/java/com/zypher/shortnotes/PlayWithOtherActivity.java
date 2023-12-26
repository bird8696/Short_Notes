package com.zypher.shortnotes;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import com.zypher.shortnotes.databinding.ActivityPlayWithOtherBinding;

public class PlayWithOtherActivity extends AppCompatActivity {
    private ActivityPlayWithOtherBinding binding;
    private AnimationDrawable animation1;
    private AnimationDrawable animation2;
    private CountDownTimer setTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayWithOtherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void playAnimation() {
        binding.ivIconP1.setBackgroundResource(R.drawable.animation_rpc);
        animation1 = (AnimationDrawable) binding.ivIconP1.getBackground();

        binding.ivIconP2.setBackgroundResource(R.drawable.animation_rpc);
        animation2 = (AnimationDrawable) binding.ivIconP2.getBackground();

        setTime = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO: Implementation for onTick
            }

            @Override
            public void onFinish() {
                // TODO: Implementation for onFinish
            }
        };
    }
}