package com.example.atividade1.utils;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.atividade1.model.Albums;
import com.example.atividade1.model.Comments;
import com.example.atividade1.model.Posts;
import com.example.atividade1.model.Users;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http_service extends AsyncTask<String, String, String> {
    private TarefaColecaoActivity tarefaColecaoActivity;
    private ProgressDialog progress;

    public Http_service(TarefaColecaoActivity tarefaColecaoActivity, ProgressDialog progress) {
        this.tarefaColecaoActivity = tarefaColecaoActivity;
        this.progress = progress;
    }

    @Override
    protected void onPreExecute() {
        this.progress.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(String... strings) {
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder;
        String urlComReplace = "";

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                stringBuilder.append(linha + "\n");
            }
            JSONArray jsonArray = new JSONArray(stringBuilder.toString());

            urlComReplace = strings[0].replaceAll("https://jsonplaceholder.typicode.com/", "").toLowerCase();

            switch (urlComReplace) {
                case "posts":
                    Posts.jsonIterable(jsonArray);
                    return urlComReplace;
                case "comments":
                    Comments.jsonIterable(jsonArray);
                    return urlComReplace;
                case "albums":
                    Albums.jsonIterable(jsonArray);
                    return urlComReplace;
                case "users":
                    Users.jsonIterable(jsonArray);
                    return urlComReplace;
                default:
                    return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return urlComReplace;
        }
    }

    @Override
    protected void onPostExecute(String string) {
        if (!string.equals("")) {
            tarefaColecaoActivity.atualizaTela(string);
        }
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }
}

