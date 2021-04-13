package com.example.atividade1.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.atividade1.R;

import static com.example.atividade1.utils.Constantes.RECYCLER;
import static com.example.atividade1.utils.TipoBtn.extraiTipo;

public class HomeScreenActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_screen);

        System.out.println("-> Home screen Activity");
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
        String tipoDado = extraiTipo(view);
        if (tipoDado.equals(RECYCLER)) {
            Intent intent = new Intent(getApplicationContext(), RecyclerActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), ColecaoActivity.class);
            intent.putExtra("tipo", tipoDado);
            startActivity(intent);
        }
    }
}
