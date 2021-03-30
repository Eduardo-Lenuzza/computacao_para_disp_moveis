package com.example.atividade1.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.atividade1.R;
import com.example.atividade1.view.MainActivity;
import com.example.atividade1.view.SplashScreenActivity;

public class HomeScreenActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_screen);
    }

    public void voltarLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void voltarSplash(View view) {
        Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
        startActivity(intent);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void colecaoApi(View view) {
        Intent intent = new Intent(getApplicationContext(), ColecaoActivity.class);
        String[] tipoDado = view.toString().split("/");
        tipoDado[1] = tipoDado[1].replaceAll("\\}", "");
        intent.putExtra("tipo", tipoDado[1]);
        startActivity(intent);
    }
}
