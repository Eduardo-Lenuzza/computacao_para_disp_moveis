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

public class Comments extends Objects implements Parcelable {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
    public static List<Comments> objComments;

    public Comments(int postId, int id, String name, String email, String body) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    protected Comments(Parcel in) {
        postId = in.readInt();
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        body = in.readString();
    }

    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "\npostId = " + postId +
                "\nid = " + id +
                "\nname = " + name +
                "\nemail = " + email +
                "\nbody = " + body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(postId);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(body);
    }

    public static void criaBotao(Context context, LinearLayout linearLayout, String idTipoColecao) {
        Button btn;
        for (Comments comments : Comments.objComments) {
            btn = new Button(context);
            View v = new View(context);
            btn.setText("Detalhes do objeto " + Integer.toString(comments.getId()));
            btn.setTag(comments);
            btn.setBackgroundColor(Color.parseColor("#228B22"));
            btn.setTextColor(Color.parseColor("#FFFFFF"));
            v.setMinimumHeight(10);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetalhesActivity.class);
                    Button botao = (Button) view;
                    Comments dado = (Comments) botao.getTag();

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
        objComments = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            Comments comments = new Comments(obj.getInt("postId")
                    , obj.getInt("id")
                    , obj.getString("name")
                    , obj.getString("email")
                    , obj.getString("body"));

            objComments.add(comments);
        }
    }
}
