package com.example.atividade1.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Users implements Parcelable {
    private int id;
    private String name;
    private String username;
    private String email;
    //private String address;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    //private String geo;
    private String lat;
    private String lng;
    private String phone;
    private String website;
    //private String company;
    private String name2;
    private String catchPhrase;
    private String bs;
    public static List<Users> objUsers;

    public Users(int id, String name, String username, String email,
            /*String address,*/ String street, String suite, String city,
                 String zipcode, /*String geo,*/ String lat, String lng, String phone,
                 String website, /*String company,*/ String name2, String catchPhrase, String bs) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        //this.address = address;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        //this.geo = geo;
        this.lat = lat;
        this.lng = lng;
        this.phone = phone;
        this.website = website;
        //this.company = company;
        this.name2 = name2;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    protected Users(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
        email = in.readString();
        //address = in.readString();
        street = in.readString();
        suite = in.readString();
        city = in.readString();
        zipcode = in.readString();
        //geo = in.readString();
        lat = in.readString();
        lng = in.readString();
        phone = in.readString();
        website = in.readString();
        //company = in.readString();
        name2 = in.readString();
        catchPhrase = in.readString();
        bs = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    /*public String getAddress() {
        return address;
    }*/

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

   /*public String getGeo() {
        return geo;
    }*/

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    /*public String getCompany() {
        return company;
    }*/

    public String getName2() {
        return name2;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public static List<Users> getObjUsers() {
        return objUsers;
    }

    @Override
    public String toString() {
        return "\nid = " + id +
                "\nname = " + name +
                "\nusername = " + username +
                "\nemail = " + email +
                "\naddress = {" +
                "\nstreet = " + street +
                "\nsuite = " + suite +
                "\ncity = " + city +
                "\nzipcode = " + zipcode +
                "\n}" +
                "\ngeo = {" +
                "\nlat = " + lat +
                "\nlng = " + lng +
                "\n}" +
                "\ncompany = {" +
                "\nname = " + name +
                "\ncatchPhrase = " + catchPhrase +
                "\nbs = " + bs +
                "\n}";
    }

    public static void jsonIterable(JSONArray jsonArray) throws Exception {
        objUsers = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            JSONObject address = obj.getJSONObject("address");
            JSONObject geo = address.getJSONObject("geo");
            JSONObject company = obj.getJSONObject("company");

            Users users = new Users(obj.getInt("id")
                    , obj.getString("name")
                    , obj.getString("username")
                    , obj.getString("email")
                    //, address
                    , address.getString("street")
                    , address.getString("suite")
                    , address.getString("city")
                    , address.getString("zipcode")
                    //, geo
                    , geo.getString("lat")
                    , geo.getString("lng")
                    , obj.getString("phone")
                    , obj.getString("website")
                    //, company
                    , company.getString("name")
                    , company.getString("catchPhrase")
                    , company.getString("bs")
            );
            objUsers.add(users);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(email);
        //dest.writeString(address);
        dest.writeString(street);
        dest.writeString(suite);
        dest.writeString(city);
        dest.writeString(zipcode);
        //dest.writeString(geo);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeString(phone);
        dest.writeString(website);
        //dest.writeString(company);
        dest.writeString(name2);
        dest.writeString(catchPhrase);
        dest.writeString(bs);
    }
}
