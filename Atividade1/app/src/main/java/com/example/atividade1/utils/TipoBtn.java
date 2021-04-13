package com.example.atividade1.utils;

import android.view.View;

public class TipoBtn {

    public static String extraiTipo(View view) {
        String[] tipoDadoSemBarra = view.toString().split("/");
        String[] tipoDadoSemIfen = tipoDadoSemBarra[1].split("_");
        tipoDadoSemIfen[1] = tipoDadoSemIfen[1].replaceAll("\\}", "");
        return tipoDadoSemIfen[1];
    }
}
