package com.error_found.kotdroid.mvp.models.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user12 on 6/2/18.
 */

public class User implements Parcelable {
 public  String _name;
 public  String _email_id;
 public  String _password;
 public  String _contact_no;

    public User() {
    }

    public User(String name, String email_id, String password, String contact_no) {
        this._name = name;
        this._email_id = email_id;
        this._password = password;
        this._contact_no = contact_no;
    }

    protected User(Parcel in) {
        _name = in.readString();
        _email_id = in.readString();
        _password = in.readString();
        _contact_no = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_name);
        parcel.writeString(_email_id);
        parcel.writeString(_password);
        parcel.writeString(_contact_no);
    }
}
