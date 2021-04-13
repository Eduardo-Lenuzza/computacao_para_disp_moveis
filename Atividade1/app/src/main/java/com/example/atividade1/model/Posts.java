package com.example.atividade1.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.atividade1.utils.Objects;
import com.example.atividade1.view.DetalhesActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class Posts extends Objects implements Parcelable {
    private int userId;
    private int id;
    private String title;
    private String body;
    public static List<Posts> objPosts;

    public Posts(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    protected Posts(Parcel in) {
        userId = in.readInt();
        id = in.readInt();
        title = in.readString();
        body = in.readString();
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel in) {
            return new Posts(in);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\nuserId = " + userId +
                "\nid = " + id +
                "\ntitle = " + title +
                "\nbody = " + body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(body);
    }

    public static void criaBotao(Context context, LinearLayout linearLayout, String idTipoColecao) {
        Button btn;
        for (Posts post : Posts.objPosts) {
            btn = new Button(context);
            View v = new View(context);
            btn.setText("Detalhes do objeto " + Integer.toString(post.getId()));
            btn.setTag(post);
            btn.setBackgroundColor(Color.parseColor("#228B22"));
            btn.setTextColor(Color.parseColor("#FFFFFF"));
            v.setMinimumHeight(10);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetalhesActivity.class);
                    Button botao = (Button) view;
                    Posts dado = (Posts) botao.getTag();

                    intent.putExtra("dados", dado);
                    intent.putExtra("tipo", idTipoColecao);
                    startActivity(view.getContext(), intent, null);
                }
            });
            linearLayout.addView(btn);
            linearLayout.addView(v);
        }
    }

    public static void jsonIterable(JSONArray jsonArray) throws Exception {
        objPosts = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            Posts post = new Posts(obj.getInt("userId")
                    , obj.getInt("id")
                    , obj.getString("title")
                    , obj.getString("body"));

            objPosts.add(post);
        }
    }
}
