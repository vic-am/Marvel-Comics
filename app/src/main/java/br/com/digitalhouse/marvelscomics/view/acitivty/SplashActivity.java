package br.com.digitalhouse.marvelscomics.view.acitivty;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import br.com.digitalhouse.marvelscomics.R;

public class SplashActivity extends AppCompatActivity {
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jump();
            }
        }, 2500);
    }

    private void jump() {
        timer.cancel();
        startActivity(new Intent(SplashActivity.this,
                MainActivity.class));
        finish();
    }
}
