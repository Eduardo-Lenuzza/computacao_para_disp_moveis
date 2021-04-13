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

public class Albums extends Objects implements Parcelable {
    private int userId;
    private int id;
    private String title;

    public static List<Albums> objAlbums;

    public Albums(int userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    protected Albums(Parcel in) {
        userId = in.readInt();
        id = in.readInt();
        title = in.readString();

    }

    public static final Creator<Albums> CREATOR = new Creator<Albums>() {
        @Override
        public Albums createFromParcel(Parcel in) {
            return new Albums(in);
        }

        @Override
        public Albums[] newArray(int size) {
            return new Albums[size];
        }
    };

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\npostId = " + userId +
                "\nid = " + id +
                "\nname = " + title;
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

    }

    public static void criaBotao(Context context, LinearLayout linearLayout, String idTipoColecao) {
        Button btn;
        for (Albums albums : Albums.objAlbums) {
            btn = new Button(context);
            View v = new View(context);
            btn.setText("Detalhes do objeto " + Integer.toString(albums.getId()));
            btn.setTag(albums);
            btn.setBackgroundColor(Color.parseColor("#228B22"));
            btn.setTextColor(Color.parseColor("#FFFFFF"));
            v.setMinimumHeight(10);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetalhesActivity.class);
                    Button botao = (Button) view;
                    Albums dado = (Albums) botao.getTag();

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
        objAlbums = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);

            Albums albums = new Albums(obj.getInt("userId")
                    , obj.getInt("id")
                    , obj.getString("title"));

            objAlbums.add(albums);
        }
    }
}


