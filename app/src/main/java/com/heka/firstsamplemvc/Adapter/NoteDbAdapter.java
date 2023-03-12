package com.heka.firstsamplemvc.Adapter;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.heka.firstsamplemvc.Model.DbConstants;
import com.heka.firstsamplemvc.Model.NoteDbHelper;

import java.util.ArrayList;

public class NoteDbAdapter {
    NoteDbHelper noteDbHelper;
    public NoteDbAdapter(Context context){
        noteDbHelper = new NoteDbHelper(context);
    }
    public void createDb(){
        SQLiteDatabase dbb = noteDbHelper.getWritableDatabase();
        noteDbHelper.onCreate(dbb);
    }



    public long insertNote(String note)
    {
        SQLiteDatabase dbb = noteDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.NOTE, note);
        long id = dbb.insert(DbConstants.TABLE_NAME, null , contentValues);
        return id;
    }

    public ArrayList<String> getNotes()
    {
        SQLiteDatabase db = noteDbHelper.getWritableDatabase();
        String[] columns = {DbConstants.NID,DbConstants.NOTE};
        Cursor cursor =db.query(DbConstants.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        ArrayList<String> noteList = new ArrayList<>();
        while (cursor.moveToNext())
        {
           int cid =cursor.getInt(cursor.getColumnIndex(DbConstants.NID));
           String name =cursor.getString(cursor.getColumnIndex(DbConstants.NOTE));
            noteList.add(name);
        }
        return noteList;
    }

    public  int deleteNote(String note)
    {
        SQLiteDatabase db = noteDbHelper.getWritableDatabase();
        String[] whereArgs ={note};

        int count =db.delete(DbConstants.TABLE_NAME ,DbConstants.NOTE+" = ?",whereArgs);
        return  count;
    }

    public int updateNote(String oldNote , String newNote)
    {
        SQLiteDatabase db = noteDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.NOTE,newNote);
        String[] whereArgs= {oldNote};
        int count =db.update(DbConstants.TABLE_NAME,contentValues, DbConstants.NOTE+" = ?",whereArgs );
        return count;
    }
}
