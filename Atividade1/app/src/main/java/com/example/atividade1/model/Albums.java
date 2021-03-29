package com.example.atividade1.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Albums implements Parcelable {
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

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
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


