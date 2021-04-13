package com.example.atividade1.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atividade1.R;
import com.example.atividade1.model.Albums;
import com.example.atividade1.model.Comments;
import com.example.atividade1.model.Posts;
import com.example.atividade1.model.Users;
import com.example.atividade1.utils.Http_service;
import com.example.atividade1.utils.TarefaColecao;

import static com.example.atividade1.utils.Constantes.ALBUMS;
import static com.example.atividade1.utils.Constantes.COMMENTS;
import static com.example.atividade1.utils.Constantes.POSTS;
import static com.example.atividade1.utils.Constantes.URL;
import static com.example.atividade1.utils.Constantes.URL_ALBUMS;
import static com.example.atividade1.utils.Constantes.URL_COMMENTS;
import static com.example.atividade1.utils.Constantes.URL_POSTS;
import static com.example.atividade1.utils.Constantes.URL_USERS;
import static com.example.atividade1.utils.Constantes.USERS;

public class ColecaoActivity extends AppCompatActivity implements TarefaColecao {

    private ProgressDialog progress;
    private String idTipoColecao;
    private Button mostrarDadosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_colecao);
        mostrarDadosBtn = findViewById(R.id.btnMostrarDados);

        System.out.println("-> Coleção Activity");

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            idTipoColecao = null;
        } else {
            idTipoColecao = extras.getString("tipo");
        }
    }

    public void showData(View view) {
        populaLista(view);
        mostrarDadosBtn.setEnabled(false);
    }

    @Override
    public void populaLista(View view) {
        progress = new ProgressDialog(this);
        progress.setMessage("Carregando...");
        Http_service http_service = new Http_service(this, progress);

        switch (idTipoColecao) {
            case POSTS:
                http_service.execute(URL + URL_POSTS);
                break;
            case COMMENTS:
                http_service.execute(URL + URL_COMMENTS);
                break;
            case ALBUMS:
                http_service.execute(URL + URL_ALBUMS);
                break;
            case USERS:
                http_service.execute(URL + URL_USERS);
                break;
        }
    }

    @Override
    public void atualizaTela(String tipoDeDado) {
        LinearLayout novoLinearLayout = findViewById(R.id.layoutVerticalItens);
        switch (tipoDeDado) {
            case POSTS:
                Posts.criaBotao(getApplicationContext(), novoLinearLayout, idTipoColecao);
                break;
            case COMMENTS:
                Comments.criaBotao(getApplicationContext(), novoLinearLayout, idTipoColecao);
                break;
            case ALBUMS:
                Albums.criaBotao(getApplicationContext(), novoLinearLayout, idTipoColecao);
                break;
            case USERS:
                Users.criaBotao(getApplicationContext(), novoLinearLayout, idTipoColecao);
                break;
        }
    }
}