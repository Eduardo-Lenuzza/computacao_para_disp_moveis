package com.example.atividade1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.atividade1.R;
import com.example.atividade1.model.Albums;
import com.example.atividade1.model.Comments;
import com.example.atividade1.model.Posts;

public class DetalhesActivity extends AppCompatActivity {
    Posts posts;
    Comments comments;
    Albums albums;
    private String idTipoColecao;
    private String idTipoPosts;
    private String idTipoComments;
    private String idTipoAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detalhes);

        idTipoPosts = Integer.toString(R.id.colecaoPosts);
        idTipoComments = Integer.toString(R.id.colecaoComments);
        idTipoAlbums = Integer.toString(R.id.colecaoAlbums);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            idTipoColecao = null;
        } else {
            idTipoColecao = extras.getString("tipo");
        }
        insereTexto(idTipoColecao);
    }

    public void insereTexto(String idTipoColecao) {

        if (idTipoColecao.equals(idTipoPosts)) {
            posts = getIntent().getExtras().getParcelable("dados");
            TextView textView = findViewById(R.id.textViewDetalhes);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            textView.setBackgroundColor(Color.parseColor("#696969"));
            textView.setText("Objeto Posts:\n" + posts.toString());
        } else if (idTipoColecao.equals(idTipoComments)) {
            comments = getIntent().getExtras().getParcelable("dados");
            TextView textView = findViewById(R.id.textViewDetalhes);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            textView.setBackgroundColor(Color.parseColor("#696969"));
            textView.setText("Objeto Comments:\n" + comments.toString());
        } else if (idTipoColecao.equals(idTipoAlbums)) {
            albums = getIntent().getExtras().getParcelable("dados");
            TextView textView = findViewById(R.id.textViewDetalhes);
            textView.setTextColor(Color.parseColor("#FFFFFF"));
            textView.setBackgroundColor(Color.parseColor("#696969"));
            textView.setText("Objeto Albums:\n" + albums.toString());
        }
    }
}