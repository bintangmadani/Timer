package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView Waktu;
    private Button Start, Stop, Reset;
    private  TimerClass timerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Waktu = findViewById(R.id.timer);
        Start = findViewById(R.id.start);
        Start.setOnClickListener(this);
        Stop = findViewById(R.id.stop);
        Stop.setOnClickListener(this);


        timerClass = new TimerClass(60000 * 3, 1000);
    }

    public class TimerClass extends CountDownTimer {

        TimerClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String waktu = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));

            Waktu.setText("Waktu " +waktu);
        }

        @Override
        public void onFinish() {
            Toast.makeText(MainActivity.this, "Waktu Telah Berakhir", Toast.LENGTH_LONG).show();
            finish();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                timerClass.start();
                Toast.makeText(MainActivity.this, "Mulai", Toast.LENGTH_LONG).show();
                break;

            case R.id.stop:
                timerClass.cancel();
                Toast.makeText(MainActivity.this, "Berhenti", Toast.LENGTH_LONG).show();
                break;


        }
    }



}