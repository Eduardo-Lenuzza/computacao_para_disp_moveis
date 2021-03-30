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
import com.example.atividade1.model.Users;

public class DetalhesActivity extends AppCompatActivity {
    Posts posts;
    Comments comments;
    Albums albums;
    Users users;
    private String idTipoColecao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detalhes);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            idTipoColecao = null;
        } else {
            idTipoColecao = extras.getString("tipo");
        }
        insereTexto(idTipoColecao);
    }

    public void insereTexto(String idTipoColecao) {

        switch (idTipoColecao) {
            case "colecaoPosts":
                posts = getIntent().getExtras().getParcelable("dados");
                criaTextView().setText("Objeto Posts:\n" + posts.toString());
                break;
            case "colecaoComments":
                comments = getIntent().getExtras().getParcelable("dados");
                criaTextView().setText("Objeto Comments:\n" + comments.toString());
                break;
            case "colecaoAlbums":
                albums = getIntent().getExtras().getParcelable("dados");
                criaTextView().setText("Objeto Albums:\n" + albums.toString());
                break;
            case "colecaoUsers":
                users = getIntent().getExtras().getParcelable("dados");
                criaTextView().setText("Objeto Users:\n" + users.toString());
                break;
        }
    }

    public TextView criaTextView() {
        TextView textView = findViewById(R.id.textViewDetalhes);
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setBackgroundColor(Color.parseColor("#696969"));
        return textView;
    }
}