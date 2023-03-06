package com.heka.firstsamplemvc.Model;

import static com.heka.firstsamplemvc.Model.DbConstants.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.heka.firstsamplemvc.util.Message;

public class NoteDbHelper extends SQLiteOpenHelper {

    private Context context;

    public NoteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Message.message(context,""+e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Message.message(context,"OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e) {
            Message.message(context,""+e);
        }
    }
}
