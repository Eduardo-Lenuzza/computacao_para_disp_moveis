package com.example.atividade1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.atividade1.R;
import com.example.atividade1.model.Albums;
import com.example.atividade1.model.Comments;
import com.example.atividade1.model.Posts;
import com.example.atividade1.model.Users;
import com.example.atividade1.utils.CustomAdapterDetalhes;
import com.example.atividade1.utils.Http_service;
import com.example.atividade1.utils.Objects;
import com.example.atividade1.utils.TarefaColecao;

import java.util.List;

import static com.example.atividade1.utils.Constantes.ALBUMS;
import static com.example.atividade1.utils.Constantes.COMMENTS;
import static com.example.atividade1.utils.Constantes.POSTS;
import static com.example.atividade1.utils.Constantes.URL;
import static com.example.atividade1.utils.Constantes.URL_ALBUMS;
import static com.example.atividade1.utils.Constantes.URL_COMMENTS;
import static com.example.atividade1.utils.Constantes.URL_POSTS;
import static com.example.atividade1.utils.Constantes.URL_USERS;
import static com.example.atividade1.utils.Constantes.USERS;
import static com.example.atividade1.utils.TipoBtn.extraiTipo;

public class RecyclerActivity extends AppCompatActivity implements TarefaColecao {

    private ProgressDialog progress;
    private RecyclerView recyclerView;
    private TextView recyclerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recycler);

        System.out.println("-> Recycler Activity");

        recyclerView = (RecyclerView) findViewById(R.id.showRecycler);
        recyclerTitle = (TextView) findViewById(R.id.recyclerTitle);
    }

    public void mostraDetalhesPosts(View view) {
        populaLista(view);
    }

    @Override
    public void populaLista(View view) {
        String tipoBtn = extraiTipo(view);
        System.out.println("--> " + tipoBtn);
        progress = new ProgressDialog(this);
        progress.setMessage("Carregando...");
        Http_service http_service = new Http_service(this, progress);

        switch (tipoBtn) {
            case POSTS:
                recyclerTitle.setText(getString(R.string.tituloPosts));
                http_service.execute(URL + URL_POSTS);
                break;
            case COMMENTS:
                recyclerTitle.setText(getString(R.string.tituloComments));
                http_service.execute(URL + URL_COMMENTS);
                break;
            case ALBUMS:
                recyclerTitle.setText(getString(R.string.tituloAlbums));
                http_service.execute(URL + URL_ALBUMS);
                break;
            case USERS:
                recyclerTitle.setText(getString(R.string.tituloUsers));
                http_service.execute(URL + URL_USERS);
                break;
        }

    }

    @Override
    public void atualizaTela(String dado) {
        switch (dado) {
            case POSTS:
                customAdapter(Posts.objPosts);
                break;
            case COMMENTS:
                customAdapter(Comments.objComments);
                break;
            case ALBUMS:
                customAdapter(Albums.objAlbums);
                break;
            case USERS:
                customAdapter(Users.objUsers);
                break;
        }
    }

    public void customAdapter(List<? extends Objects> list) {
        CustomAdapterDetalhes customAdapterDetalhes = new CustomAdapterDetalhes(getApplicationContext(),
                list, R.layout.activity_recycler);
        //RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setAdapter(customAdapterDetalhes);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
}