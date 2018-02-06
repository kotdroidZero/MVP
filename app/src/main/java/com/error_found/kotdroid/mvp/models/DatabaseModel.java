package com.error_found.kotdroid.mvp.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.error_found.kotdroid.mvp.models.pojos.User;


/**
 * Created by user12 on 6/2/18.
 */

public class DatabaseModel extends SQLiteOpenHelper{
    //fields
    public static final String DATABASE_NAME="USER_DB";
    public static final String TABLE_NAME="User_table";
    public static final String NAME="Name";
    public static final String UID="Uid";
    public static final int DATABASE_VERSION=1;
    public static final String EMAIL_ID="Email_id";
    public static final String CONTACT="Contact";
    public static final String PASSWORD="password";

    //queries
    public static final String CREATE_TABLE=" CREATE TABLE "+TABLE_NAME+"( "
            +UID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +NAME +" TEXT, "
            +PASSWORD +" TEXT, "
            +CONTACT +" TEXT, "
            +EMAIL_ID +" TEXT );";
    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;

    public DatabaseModel(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public long registerUser(User user) {
        SQLiteDatabase dbWrite=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        if (!alreadyRegistered(user._email_id))
        {
            contentValues.put(EMAIL_ID,user._email_id);
            contentValues.put(NAME,user._name);
            contentValues.put(CONTACT,user._contact_no);
            contentValues.put(PASSWORD,user._password);
            return dbWrite.insert(TABLE_NAME,null,contentValues);
        }
        return -100;

    }

    public User loginUser(String email,String password )
    {


        String SELECT_QUERY="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase dbRead=this.getReadableDatabase();
        Cursor cursor= dbRead.rawQuery(SELECT_QUERY,null);
        cursor.moveToFirst();
        while (cursor.moveToNext())
        {
            String email1=cursor.getString(cursor.getColumnIndex(EMAIL_ID));
            String password1=cursor.getString(cursor.getColumnIndex(PASSWORD));

            if (email1.equalsIgnoreCase(email)&&password1.equalsIgnoreCase(password))
            {
                User user=new User();
                user._email_id=email1;
                user._contact_no=cursor.getString(cursor.getColumnIndex(CONTACT));
                user._name=cursor.getString(cursor.getColumnIndex(NAME));
                return user;
            }
        }
        return null;
    }
    private boolean alreadyRegistered(String email_id) {
        String SELECT_QUERY="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase dbRead=this.getReadableDatabase();
        Cursor cursor=dbRead.rawQuery(SELECT_QUERY,null);
        cursor.moveToFirst();
        while (cursor.moveToNext())
        {
            if (cursor.getString(cursor.getColumnIndex(EMAIL_ID))
                    .equalsIgnoreCase(email_id))
                return true;
        }
        return false;
    }
}
