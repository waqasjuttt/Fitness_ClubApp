package com.example.waqasjutt.fitness_club;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class SplashActivity extends AppCompatActivity {

    private VideoView videoView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity);
        getSupportActionBar().hide();
        videoView = (VideoView) findViewById(R.id.videoView);

        linearLayout = (LinearLayout) findViewById(R.id.Splash_LinearLayout);
        getBG();

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sequencexxxx);
        videoView.setVideoURI(video);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (isFinishing())
                    return;
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        });
        videoView.start();
    }

    public void getBG() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        if (Build.VERSION.SDK_INT >= 16) {
            Drawable d = new BitmapDrawable(getResources(),
                    new OutOfMemoryfixer().decodeSampledBitmapFromResource(getResources(),
                            R.drawable.gym,
                            700, 700));
            linearLayout.setBackground(d);
        }
    }
}
