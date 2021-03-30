package com.example.atividade1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.atividade1.R;
import com.example.atividade1.model.Albums;
import com.example.atividade1.model.Comments;
import com.example.atividade1.model.Posts;
import com.example.atividade1.model.Users;
import com.example.atividade1.utils.TarefaColecaoActivity;
import com.example.atividade1.utils.Http_service;

public class ColecaoActivity extends AppCompatActivity implements TarefaColecaoActivity {

    private String url = "https://jsonplaceholder.typicode.com";
    private String urlPost = "/posts";
    private String urlComments = "/comments";
    private String urlAlbums = "/albums";
    private String urlUsers = "/users";
    private ProgressDialog progress;
    private String idTipoColecao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_colecao);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            idTipoColecao = null;
        } else {
            idTipoColecao = extras.getString("tipo");
        }
    }

    public void showData(View view) {
        progress = new ProgressDialog(this);
        progress.setMessage("Carregando...");
        Http_service http_service = new Http_service(this, progress);

        switch (idTipoColecao) {
            case "colecaoPosts":
                http_service.execute(url + urlPost);
                break;
            case "colecaoComments":
                http_service.execute(url + urlComments);
                break;
            case "colecaoAlbums":
                http_service.execute(url + urlAlbums);
                break;
            case "colecaoUsers":
                http_service.execute(url + urlUsers);
                break;
        }
    }

    @Override
    public void atualizaTela(String tipoDeDado) {
        switch (tipoDeDado) {
            case "posts":
                postAtualiza();
                break;
            case "comments":
                commentsAtualiza();
                break;
            case "albums":
                albumsAtualiza();
                break;
            case "users":
                usersAtualiza();
                break;
        }
    }

    public void postAtualiza() {
        LinearLayout linearLayout = findViewById(R.id.layoutVerticalItens);
        Button btn;
        for (Posts post : Posts.objPosts) {

            View view = new View(getApplicationContext());
            btn = new Button(getApplicationContext());
            btn.setText("Detalhes do objeto " + Integer.toString(post.getId()));
            btn.setTag(post);
            btn.setBackgroundColor(Color.parseColor("#228B22"));
            btn.setTextColor(Color.parseColor("#FFFFFF"));
            view.setMinimumHeight(10);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), DetalhesActivity.class);
                    Button botao = (Button) view;
                    Posts dado = (Posts) botao.getTag();

                    intent.putExtra("dados", dado);
                    intent.putExtra("tipo", idTipoColecao);
                    startActivity(intent);
                }
            });
            linearLayout.addView(btn);
            linearLayout.addView(view);
        }
    }

    public void commentsAtualiza() {
        LinearLayout linearLayout = findViewById(R.id.layoutVerticalItens);
        Button btn;
        for (Comments comments : Comments.objComments) {

            View view = new View(getApplicationContext());
            btn = new Button(getApplicationContext());
            btn.setText("Detalhes do objeto " + Integer.toString(comments.getId()));
            btn.setTag(comments);
            btn.setBackgroundColor(Color.parseColor("#228B22"));
            btn.setTextColor(Color.parseColor("#FFFFFF"));
            view.setMinimumHeight(10);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), DetalhesActivity.class);
                    Button botao = (Button) view;
                    Comments comments = (Comments) botao.getTag();

                    intent.putExtra("dados", comments);
                    intent.putExtra("tipo", idTipoColecao);
                    startActivity(intent);
                }
            });
            linearLayout.addView(btn);
            linearLayout.addView(view);
        }
    }

    public void albumsAtualiza() {
        LinearLayout linearLayout = findViewById(R.id.layoutVerticalItens);
        Button btn;
        for (Albums albums : Albums.objAlbums) {

            View view = new View(getApplicationContext());
            btn = new Button(getApplicationContext());
            btn.setText("Detalhes do objeto " + Integer.toString(albums.getId()));
            btn.setTag(albums);
            btn.setBackgroundColor(Color.parseColor("#228B22"));
            btn.setTextColor(Color.parseColor("#FFFFFF"));
            view.setMinimumHeight(10);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), DetalhesActivity.class);
                    Button botao = (Button) view;
                    Albums albums = (Albums) botao.getTag();

                    intent.putExtra("dados", albums);
                    intent.putExtra("tipo", idTipoColecao);
                    startActivity(intent);
                }
            });
            linearLayout.addView(btn);
            linearLayout.addView(view);
        }
    }

    public void usersAtualiza() {
        LinearLayout linearLayout = findViewById(R.id.layoutVerticalItens);
        Button btn;
        for (Users users : Users.objUsers) {

            View view = new View(getApplicationContext());
            btn = new Button(getApplicationContext());
            btn.setText("Detalhes do objeto " + Integer.toString(users.getId()));
            btn.setTag(users);
            btn.setBackgroundColor(Color.parseColor("#228B22"));
            btn.setTextColor(Color.parseColor("#FFFFFF"));
            view.setMinimumHeight(10);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), DetalhesActivity.class);
                    Button botao = (Button) view;
                    Users users = (Users) botao.getTag();

                    intent.putExtra("dados", users);
                    intent.putExtra("tipo", idTipoColecao);
                    startActivity(intent);
                }
            });
            linearLayout.addView(btn);
            linearLayout.addView(view);
        }
    }
}